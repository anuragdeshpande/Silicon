package framework;

import annotations.*;
import com.aventstack.extentreports.ExtentReports;
import constants.Users;
import framework.guidewire.GuidewireInteract;
import framework.integrations.rally.RallyTestCase;
import framework.integrations.rally.RallyUserReference;
import framework.integrations.rally.dtos.RallyTestCaseDTO;
import framework.logger.RegressionLogger;
import framework.reports.models.TestDetailsDTO;
import framework.webdriver.BrowserFactory;
import framework.webdriver.ThreadFactory;
import framework.webdriver.utils.BrowserStorageAccess;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class BaseOperations {

    protected ExtentReports reports;
    private String suiteName;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(final XmlTest xmlTest, final ITestContext context) {
        final String suiteName = context.getSuite().getName();
        if (!ReportManager.isInitiated()) {
            this.suiteName = "BO Init_" + suiteName;
            this.reports = ReportManager.initiate(this.suiteName);
        }
    }

    @BeforeTest(description = "BeforeTest", alwaysRun = true)
    public void beforeTest(final ITestContext context, final XmlTest xmlTest) {
        final String xmlTestName = xmlTest.getName();
        final TestDetailsDTO dto = new TestDetailsDTO();
        dto.setXmlTestName(xmlTestName);
        ReportManager.recordXMLTest(dto);
        RegressionLogger.getXMLTestLogger().info("Running Test in: " + Thread.currentThread().getName() + "- ID: " + ThreadFactory.getID());
        RegressionLogger.getXMLTestLogger().info("Test was part of the suite: " + context.getSuite().getName());
        BrowserFactory.getCurrentBrowser().withDOM().injectInfoMessage("Base Operations: In Before Test Method, saving xml test name to cache");
    }

    @BeforeClass(description = "BeforeClass", alwaysRun = true)
    public void beforeClass(final XmlTest xmlTest, final ITestContext iTestContext) {
        final TestDetailsDTO dto = new TestDetailsDTO();
        dto.setClassName(this.getClass().getSimpleName());
        dto.setXmlTestName(xmlTest.getName());
        dto.setPackageName(this.getClass().getPackage().getName());
        dto.setSuiteName(iTestContext.getSuite().getName());
        if (!dto.getClassName().equalsIgnoreCase("TestRunner")) {
            ReportManager.recordClass(dto);
            BrowserFactory.getCurrentBrowser().withDOM().injectInfoMessage("Base Operations: In Before Test Method, saving class name to cache");
        }
    }

    @BeforeMethod(description = "BeforeMethod", alwaysRun = true)
    public void beforeMethod(final Method method, final ITestResult iTestResult) {
        BrowserFactory.getCurrentBrowser().withDOM().injectInfoMessage("Base Operations: In Before Test Method, saving method name to cache");
        final Method testMethod = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        final TestDetailsDTO dto = new TestDetailsDTO();
        dto.setTestName(testMethod.getName());
        dto.setClassName(iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName());

        if (!dto.getClassName().equalsIgnoreCase("TestRunner")) {
            dto.setXmlTestName(iTestResult.getMethod().getTestClass().getXmlTest().getName());
            ReportManager.recordClass(dto);
        }

        final Test[] testAnnotations = testMethod.getDeclaredAnnotationsByType(Test.class);
        ReportManager.recordTest(dto, testAnnotations.length > 0 ? testAnnotations[0].description() : null);
        final boolean hasNoAutomatedTestAnnotation = testMethod.getAnnotationsByType(AutomatedTest.class).length == 0;
        final boolean hasNoPostResetScriptAnnotation = testMethod.getAnnotationsByType(PreResetScript.class).length == 0;
        final boolean hasNoPreResetScriptAnnotation = testMethod.getAnnotationsByType(PostResetScript.class).length == 0;
        final boolean hasNoCPPTestAnnotation = testMethod.getDeclaringClass().getAnnotationsByType(CPPTest.class).length == 0;
        if (hasNoCPPTestAnnotation) {
            BrowserStorageAccess.getInstance().store("isCPP", "false");
        } else {
            BrowserStorageAccess.getInstance().store("isCPP", "true");
        }

        if ((hasNoAutomatedTestAnnotation && (hasNoPostResetScriptAnnotation && hasNoPreResetScriptAnnotation)) && testAnnotations.length > 0) {
            BrowserFactory.getCurrentBrowser().withDOM().injectInfoMessage("Base Operations: Skipping test as annotation requirements are not met");
            iTestResult.setStatus(ITestResult.SKIP);
            throw new SkipException("Skipping Test : " + dto.getTestName() + " : No @AutomatedTest annotation found.");
        }
        BrowserFactory.getCurrentBrowser().withDOM().clearBannerMessage();
    }

    @AfterMethod(description = "AfterMethod", alwaysRun = true)
    public void afterMethod(final ITestResult iTestResult) {
        // update test case if the method is tracked
        final Method testMethod = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        final TestCase testCaseAnnotation = testMethod.getAnnotation(TestCase.class);
        final AutomatedTest automatedTest = testMethod.getAnnotation(AutomatedTest.class);
        if (testCaseAnnotation != null) {
            final RallyTestCase rallyTestCase = RallyTestCase.getTestCaseByID(testCaseAnnotation.id(), null);
            if (rallyTestCase.getRelatedUserStory().isStoryTracked()) {
                final GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
                interact.withDOM().injectInfoMessage("Uploading results to rally test case");

                final Optional<Users.User> user = Users.get(automatedTest.Author());
                String userReference = null;
                if (user.isPresent()) {
                    userReference = RallyUserReference.getUserReference(user.get().getRallyUserEmail());
                }


                final String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                final String buildTag = System.getProperty("jenkinsBuildNumber") == null ? "Local: " + timestamp : "Regression Build " + System.getProperty("jenkinsBuildNumber");
                switch (iTestResult.getStatus()) {
                    case ITestResult.SUCCESS:
                        rallyTestCase.recordTestResult(RallyTestCaseDTO.getInstance(true, iTestResult.getMethod().getDescription(), userReference, buildTag));
                        break;
                    case ITestResult.FAILURE:
                        rallyTestCase.recordTestResult(RallyTestCaseDTO.getInstance(false, ExceptionUtils.getStackTrace(iTestResult.getThrowable()), userReference, buildTag));
                        break;
                    default:
                        rallyTestCase.recordTestResult(RallyTestCaseDTO.getInstance(false, iTestResult.getMethod().getDescription(), userReference, buildTag));
                        break;
                }

                interact.withDOM().clearBannerMessage();
            }
        }
    }

    @AfterClass(description = "AfterClass", alwaysRun = true)
    public void afterClass(final ITestContext context, final XmlTest xmlTest) {
        if (ThreadFactory.getInstance().getDriver() != null) {
            RegressionLogger.getTestClassLogger().info("Closing browser");
            GuidewireInteract.clearCookiesToForceLogout();
            try {
                BrowserFactory.closeCurrentBrowser();
            } catch (final UnhandledAlertException e) {
                BrowserFactory.getCurrentBrowser().getDriver().switchTo().alert().accept();
                BrowserFactory.closeCurrentBrowser();
            }
        }

    }

    @AfterTest(description = "AfterTest", alwaysRun = true)
    public void afterTest(final ITestContext context, final XmlTest xmlTest) {

    }

    @AfterSuite(description = "AfterSuite", alwaysRun = true)
    public void afterSuite(final XmlTest xmlTest, final ITestContext context) {
        BrowserFactory.closeAllWindows();
        if (this.suiteName != null && this.suiteName.startsWith("BO Init_")) {
            reports.flush();
        }
    }
}
