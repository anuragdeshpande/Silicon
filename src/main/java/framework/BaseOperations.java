package framework;

import annotations.AutomatedTest;
import annotations.PostResetScript;
import annotations.PreResetScript;
import annotations.TestCase;
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
    public void beforeSuite(XmlTest xmlTest, ITestContext context) {
        String suiteName = context.getSuite().getName();
        if (!ReportManager.isInitiated()) {
            this.suiteName = "BO Init_"+ suiteName;
            this.reports = ReportManager.initiate(this.suiteName);
        }
    }

    @BeforeTest(description = "BeforeTest", alwaysRun = true)
    public void beforeTest(ITestContext context, XmlTest xmlTest) {
        String xmlTestName = xmlTest.getName();
        TestDetailsDTO dto = new TestDetailsDTO();
        dto.setXmlTestName(xmlTestName);
        ReportManager.recordXMLTest(dto);
        RegressionLogger.getXMLTestLogger().info("Running Test in: "+Thread.currentThread().getName()+"- ID: "+ ThreadFactory.getID());
        RegressionLogger.getXMLTestLogger().info("Test was part of the suite: "+context.getSuite().getName());
        BrowserFactory.getCurrentBrowser().withDOM().injectInfoMessage("Base Operations: In Before Test Method, saving xml test name to cache");
    }

    @BeforeClass(description = "BeforeClass", alwaysRun = true)
    public void beforeClass(XmlTest xmlTest, ITestContext iTestContext) {
        TestDetailsDTO dto = new TestDetailsDTO();
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
    public void beforeMethod(Method method, ITestResult iTestResult) {
        BrowserFactory.getCurrentBrowser().withDOM().injectInfoMessage("Base Operations: In Before Test Method, saving method name to cache");
        Method testMethod = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        TestDetailsDTO dto = new TestDetailsDTO();
        dto.setTestName(testMethod.getName());
        dto.setClassName(iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName());

        if (!dto.getClassName().equalsIgnoreCase("TestRunner")) {
            dto.setXmlTestName(iTestResult.getMethod().getTestClass().getXmlTest().getName());
            ReportManager.recordClass(dto);
        }

        Test[] testAnnotations = testMethod.getDeclaredAnnotationsByType(Test.class);
        ReportManager.recordTest(dto, testAnnotations.length > 0? testAnnotations[0].description() : null);
        boolean hasNoAutomatedTestAnnotation = testMethod.getAnnotationsByType(AutomatedTest.class).length == 0;
        boolean hasNoPostResetScriptAnnotation = testMethod.getAnnotationsByType(PreResetScript.class).length == 0;
        boolean hasNoPreResetScriptAnnotation = testMethod.getAnnotationsByType(PostResetScript.class).length == 0;
        if ((hasNoAutomatedTestAnnotation && (hasNoPostResetScriptAnnotation && hasNoPreResetScriptAnnotation)) && testAnnotations.length > 0) {
            BrowserFactory.getCurrentBrowser().withDOM().injectInfoMessage("Base Operations: Skipping test as annotation requirements are not met");
            iTestResult.setStatus(ITestResult.SKIP);
            throw new SkipException("Skipping Test : " + dto.getTestName() + " : No @AutomatedTest annotation found.");
        }
        BrowserFactory.getCurrentBrowser().withDOM().clearBannerMessage();
    }

    @AfterMethod(description = "AfterMethod", alwaysRun = true)
    public void afterMethod(ITestResult iTestResult) {
        // update test case if the method is tracked
        Method testMethod = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        TestCase testCaseAnnotation = testMethod.getAnnotation(TestCase.class);
        AutomatedTest automatedTest = testMethod.getAnnotation(AutomatedTest.class);
        if (testCaseAnnotation != null){
            RallyTestCase rallyTestCase = RallyTestCase.getTestCaseByID(testCaseAnnotation.id(), null);
            if(rallyTestCase.getRelatedUserStory().isStoryTracked()){
                GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
                interact.withDOM().injectInfoMessage("Uploading results to rally test case");

                Optional<Users.User> user = Users.get(automatedTest.Author());
                String userReference = null;
                if(user.isPresent()){
                    userReference = RallyUserReference.getUserReference(user.get().getRallyUserEmail());
                }


                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                String buildTag = System.getProperty("jenkinsBuildNumber") == null ? "Local: "+ timestamp: "Regression Build " + System.getProperty("jenkinsBuildNumber");
                switch (iTestResult.getStatus()){
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
    public void afterClass(ITestContext context, XmlTest xmlTest) {
        if(ThreadFactory.getInstance().getDriver() != null){
            RegressionLogger.getTestClassLogger().info("Closing browser");
            GuidewireInteract.clearCookiesToForceLogout();
            try{
                BrowserFactory.closeCurrentBrowser();
            } catch (UnhandledAlertException e){
                BrowserFactory.getCurrentBrowser().getDriver().switchTo().alert().accept();
                BrowserFactory.closeCurrentBrowser();
            }
        }

    }

    @AfterTest(description = "AfterTest", alwaysRun = true)
    public void afterTest(ITestContext context, XmlTest xmlTest) {

    }

    @AfterSuite(description = "AfterSuite", alwaysRun = true)
    public void afterSuite(XmlTest xmlTest, ITestContext context){
        BrowserFactory.closeAllWindows();
        if(this.suiteName != null && this.suiteName.startsWith("BO Init_")){
            reports.flush();
        }
    }
}
