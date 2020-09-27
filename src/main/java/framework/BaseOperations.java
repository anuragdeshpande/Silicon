package framework;

import annotations.AutomatedTest;
import annotations.PostResetScript;
import annotations.PreResetScript;
import com.aventstack.extentreports.ExtentReports;
import framework.logger.RegressionLogger;
import framework.reports.models.TestDetailsDTO;
import framework.webdriver.BrowserFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;

public class BaseOperations {
    protected ExtentReports reports;

    @BeforeSuite
    public void beforeSuite(XmlTest xmlTest, ITestContext context) {
        String suiteName = context.getSuite().getName();
        if (!ReportManager.isInitiated()) {
            this.reports = ReportManager.initiate("BO Init_"+ suiteName);
        }
    }


    @BeforeTest(description = "BeforeTest")
    public void beforeTest(ITestContext context, XmlTest xmlTest) {
        String xmlTestName = xmlTest.getName();
        TestDetailsDTO dto = new TestDetailsDTO();
        dto.setXmlTestName(xmlTestName);
        // Making sure there is only one thread per test at any given point of time
//        Thread.currentThread().setName("Thread"+xmlTestName+Thread.currentThread().getId());
        ReportManager.recordXMLTest(dto);
        RegressionLogger.getXMLTestLogger().info("Running Test in: "+Thread.currentThread().getName()+"- ID: "+Thread.currentThread().getId());
        RegressionLogger.getXMLTestLogger().info("Test was part of the suite: "+context.getSuite().getName());
        BrowserFactory.getCurrentBrowser().withDOM().injectInfoMessage("Base Operations: In Before Test Method, saving xml test name to cache");
    }

    @BeforeClass(description = "BeforeClass")
    public void beforeClass(XmlTest xmlTest, ITestContext iTestContext) {
        TestDetailsDTO dto = new TestDetailsDTO();
        dto.setClassName(this.getClass().getSimpleName());
        dto.setXmlTestName(xmlTest.getName());
        if (!dto.getClassName().equalsIgnoreCase("TestRunner")) {
            ReportManager.recordClass(dto);
            BrowserFactory.getCurrentBrowser().withDOM().injectInfoMessage("Base Operations: In Before Test Method, saving class name to cache");
        }
    }

    @BeforeMethod(description = "BeforeMethod")
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

    @AfterMethod(description = "AfterMethod")
    public void afterMethod(ITestResult iTestResult) {
    }

    @AfterClass(description = "AfterClass")
    public void afterClass(ITestContext context, XmlTest xmlTest) {
    }

    @AfterTest(description = "AfterTest")
    public void afterTest(ITestContext context, XmlTest xmlTest) {
        RegressionLogger.getXMLTestLogger().info("Closing Browser");
        BrowserFactory.closeCurrentBrowser();
    }

    @AfterSuite(description = "AfterSuite")
    public void afterSuite(XmlTest xmlTest, ITestContext context){
        BrowserFactory.closeAllWindows();
    }
}
