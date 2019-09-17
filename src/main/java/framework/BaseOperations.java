package framework;

import annotations.AutomatedTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import framework.constants.StringConstants;
import framework.logger.RegressionLogger;
import framework.webdriver.BrowserFactory;
import framework.webdriver.utils.BrowserStorageAccess;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;

public class BaseOperations {
    protected RegressionLogger logger;
    protected ExtentReports reports;

    /**
     * @deprecated  this variable will be no longer supported in the next release.
     * Refactor the code to use the storage object from the application instance
     */
    @Deprecated protected HashMap<String, String> storage = new HashMap<>();
    /**
     * @deprecated  this variable will be no longer supported in the next release.
     * Refactor the code to use the faker object from the application instance
     */
    @Deprecated protected Faker faker = new Faker(Locale.US);

    @BeforeSuite
    public void beforeSuite(XmlTest xmlTest, ITestContext context) {
        if (!ReportManager.isInitiated()) {
            this.reports = ReportManager.initiate("BO Init_"+context.getSuite().getName());
        }
        String suiteName = xmlTest.getSuite().getName();
        BrowserStorageAccess.getInstance().store(StringConstants.SUITE_NAME, suiteName);
        logger = new RegressionLogger(Listener.logger, ReportManager.recordSuite(suiteName), true);
        logger.setTestName(suiteName);
        logger.setTestClassName(suiteName);
    }


    @BeforeTest(description = "BeforeTest")
    public void beforeTest(ITestContext context, XmlTest xmlTest) {
        String xmlTestName = xmlTest.getName();
        ExtentTest extentLogger = ReportManager.recordXMLTest(xmlTestName, xmlTest.getSuite().getName());
        BrowserStorageAccess.getInstance().store(StringConstants.XML_TEST_NAME, xmlTestName);
        logger = new RegressionLogger(Listener.logger, extentLogger, true);
        logger.setTestClassName(xmlTestName);
        logger.setTestName(xmlTestName);
    }

    @BeforeClass(description = "BeforeClass")
    public void beforeClass(XmlTest xmlTest, ITestContext iTestContext) {
        String testClassName = iTestContext.getClass().getSimpleName();
        if (!testClassName.equalsIgnoreCase("TestRunner")) {
            BrowserStorageAccess.getInstance().store(StringConstants.TEST_CLASS_NAME, testClassName);
            ExtentTest extentLogger = ReportManager.recordClass(testClassName, xmlTest.getName());
            logger = new RegressionLogger(Listener.logger, extentLogger, true);
            logger.setTestName(testClassName);
            logger.setTestClassName(testClassName);
        }
    }

    @BeforeMethod(description = "BeforeMethod")
    public void beforeMethod(Method method, ITestResult iTestResult) {
        String testName = iTestResult.getMethod().getConstructorOrMethod().getMethod().getName();
        String className = iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName();

        if (!className.equalsIgnoreCase("TestRunner")) {
            String xmlTestName = iTestResult.getMethod().getTestClass().getXmlTest().getName();
            ExtentTest extentLogger = ReportManager.recordClass(className, xmlTestName);
            logger = new RegressionLogger(Listener.logger, extentLogger, true);
            logger.setTestClassName(className);
            logger.setTestName(testName);
        }

        Test[] testAnnotations = iTestResult.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotationsByType(Test.class);
        ReportManager.recordTest(testName, className, testAnnotations.length > 0? testAnnotations[0].description() : null);
        String methodName = iTestResult.getMethod().getMethodName();
        this.logger = new RegressionLogger(Listener.logger, ReportManager.getTest(methodName), true);
        BrowserStorageAccess.getInstance().store(StringConstants.TEST_NAME, methodName);
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomatedTest.class).length == 0 && testAnnotations.length > 0) {
            iTestResult.setStatus(ITestResult.SKIP);
            throw new SkipException("Skipping Test : " + methodName + " : No @AutomatedTest annotation found.");
        }
    }

    @AfterMethod(description = "AfterMethod")
    public void afterMethod(ITestResult iTestResult) {
        String testName = iTestResult.getMethod().getConstructorOrMethod().getMethod().getName();
        logger = new RegressionLogger(Listener.logger, ReportManager.getTest(testName), true);
    }

    @AfterClass(description = "AfterClass")
    public void afterClass(ITestContext context, XmlTest xmlTest) {
        String testName = context.getClass().getSimpleName();
        logger = new RegressionLogger(Listener.logger, ReportManager.getClass(testName), true);
    }

    @AfterTest(description = "AfterTest")
    public void afterTest(ITestContext context, XmlTest xmlTest) {
        logger = new RegressionLogger(Listener.logger, ReportManager.getXMLTest(xmlTest.getName()), true);
    }

    @AfterSuite(description = "AfterSuite")
    public void afterSuite(XmlTest xmlTest, ITestContext context){
        logger = new RegressionLogger(Listener.logger, ReportManager.getSuite(xmlTest.getSuite().getName()), true);
        BrowserFactory.closeAllWindows();
    }
}
