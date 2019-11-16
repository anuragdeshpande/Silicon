package framework;

import annotations.AutomatedTest;
import com.aventstack.extentreports.ExtentReports;
import framework.logger.RegressionLogger;
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
        // Making sure there is only one thread at any given point of time
        Thread.currentThread().setName("Thread"+xmlTestName+Thread.currentThread().getId());
        RegressionLogger.getXMLTestLogger().info("Running Test in: "+Thread.currentThread().getName()+"- ID: "+Thread.currentThread().getId());
        ReportManager.recordXMLTest(xmlTestName, xmlTest.getSuite().getName());
    }

    @BeforeClass(description = "BeforeClass")
    public void beforeClass(XmlTest xmlTest, ITestContext iTestContext) {
        String testClassName = iTestContext.getClass().getSimpleName();
        if (!testClassName.equalsIgnoreCase("TestRunner")) {
            ReportManager.recordClass(testClassName, xmlTest.getName());
        }
    }

    @BeforeMethod(description = "BeforeMethod")
    public void beforeMethod(Method method, ITestResult iTestResult) {
        String testName = iTestResult.getMethod().getConstructorOrMethod().getMethod().getName();
        String className = iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName();

        if (!className.equalsIgnoreCase("TestRunner")) {
            String xmlTestName = iTestResult.getMethod().getTestClass().getXmlTest().getName();
            ReportManager.recordClass(className, xmlTestName);
        }

        Test[] testAnnotations = iTestResult.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotationsByType(Test.class);
        ReportManager.recordTest(testName, className, testAnnotations.length > 0? testAnnotations[0].description() : null);
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomatedTest.class).length == 0 && testAnnotations.length > 0) {
            iTestResult.setStatus(ITestResult.SKIP);
            throw new SkipException("Skipping Test : " + testName + " : No @AutomatedTest annotation found.");
        }
    }

    @AfterMethod(description = "AfterMethod")
    public void afterMethod(ITestResult iTestResult) {
    }

    @AfterClass(description = "AfterClass")
    public void afterClass(ITestContext context, XmlTest xmlTest) {
    }

    @AfterTest(description = "AfterTest")
    public void afterTest(ITestContext context, XmlTest xmlTest) {
    }

    @AfterSuite(description = "AfterSuite")
    public void afterSuite(XmlTest xmlTest, ITestContext context){
        BrowserFactory.closeAllWindows();
    }
}
