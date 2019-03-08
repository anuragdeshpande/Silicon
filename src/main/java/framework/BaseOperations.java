package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import framework.annotations.AutomatedTest;
import framework.logger.RegressionLogger;
import framework.webdriver.BrowserFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;

public class BaseOperations {
    protected RegressionLogger logger;
    protected ExtentReports reports;

    @BeforeSuite
    public void beforeSuite(XmlTest xmlTest, ITestContext context) {
        if (!ReportManager.isInitiated()) {
            this.reports = ReportManager.initiate();
        }

        logger = new RegressionLogger(Listener.logger, ReportManager.recordSuite(xmlTest.getSuite().getName()), true);
    }


    @BeforeTest(description = "BeforeTest")
    public void beforeTest(ITestContext context, XmlTest xmlTest) {
        ExtentTest extentLogger = ReportManager.recordXMLTest(xmlTest.getName(), xmlTest.getSuite().getName());
        logger = new RegressionLogger(Listener.logger, extentLogger, true);
    }

    @BeforeClass(description = "BeforeClass")
    public void beforeClass(XmlTest xmlTest, ITestContext iTestContext) {
        if(!iTestContext.getClass().getSimpleName().equalsIgnoreCase("TestRunner")){
            ExtentTest extentLogger = ReportManager.recordClass(iTestContext.getClass().getSimpleName(), xmlTest.getName());
            logger = new RegressionLogger(Listener.logger, extentLogger, true);
        }
    }

    @BeforeMethod(description = "BeforeMethod")
    public void beforeMethod(Method method, ITestResult iTestResult) {
        String testName = iTestResult.getMethod().getConstructorOrMethod().getMethod().getName();
        String className = iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName();

        if(!className.equalsIgnoreCase("TestRunner")){
            String xmlTestName = iTestResult.getMethod().getTestClass().getXmlTest().getName();
            ExtentTest extentLogger = ReportManager.recordClass(className, xmlTestName);
            logger = new RegressionLogger(Listener.logger, extentLogger, true);
        }

        ReportManager.recordTest(testName, className);
        this.logger = new RegressionLogger(Listener.logger, ReportManager.getTest(iTestResult.getMethod().getMethodName()), true);
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomatedTest.class).length == 0) {
            iTestResult.setStatus(ITestResult.SKIP);
            throw new SkipException("Skipping Test : " + iTestResult.getMethod().getMethodName() + " : No @AutomatedTest annotation found.");
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult iTestResult) {
        System.out.println("Test Name: " + iTestResult.getName());
    }

    @AfterClass
    public void afterClass() {
        BrowserFactory.closeCurrentBrowser();
    }

    @AfterTest
    public void afterTest() {

    }

    @AfterSuite
    public void flushReports(){
        if(System.getProperty("jenkinsBuildNumber") == null){
            this.reports.flush();
        }
    }




}
