package framework;

import annotations.AutomatedTest;
import annotations.PostResetScript;
import annotations.PreResetScript;
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
        // Making sure there is only one thread per test at any given point of time
//        Thread.currentThread().setName("Thread"+xmlTestName+Thread.currentThread().getId());
        ReportManager.recordXMLTest(xmlTestName, xmlTest.getSuite().getName());
        RegressionLogger.getXMLTestLogger().info("Running Test in: "+Thread.currentThread().getName()+"- ID: "+Thread.currentThread().getId());
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
        Method testMethod = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        String testName = testMethod.getName();
        String className = iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName();

        if (!className.equalsIgnoreCase("TestRunner")) {
            String xmlTestName = iTestResult.getMethod().getTestClass().getXmlTest().getName();
            ReportManager.recordClass(className, xmlTestName);
        }

        Test[] testAnnotations = testMethod.getDeclaredAnnotationsByType(Test.class);
        ReportManager.recordTest(testName, className, testAnnotations.length > 0? testAnnotations[0].description() : null);
        boolean hasNoAutomatedTestAnnotation = testMethod.getAnnotationsByType(AutomatedTest.class).length == 0;
        boolean hasNoPostResetScriptAnnotation = testMethod.getAnnotationsByType(PreResetScript.class).length == 0;
        boolean hasNoPreResetScriptAnnotation = testMethod.getAnnotationsByType(PostResetScript.class).length == 0;
        if ((hasNoAutomatedTestAnnotation || ( hasNoPostResetScriptAnnotation||hasNoPreResetScriptAnnotation)) && testAnnotations.length > 0) {
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
        RegressionLogger.getXMLTestLogger().info("Closing Browser");
        BrowserFactory.closeCurrentBrowser();
    }

    @AfterSuite(description = "AfterSuite")
    public void afterSuite(XmlTest xmlTest, ITestContext context){
        BrowserFactory.closeAllWindows();
    }
}
