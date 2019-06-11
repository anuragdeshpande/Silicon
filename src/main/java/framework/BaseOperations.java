package framework;

import annotations.AutomatedTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import framework.logger.RegressionLogger;
import framework.webdriver.BrowserFactory;
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
    protected HashMap<String, String> storage = new HashMap<>();
    protected Faker faker = new Faker(new Locale("en-us"));

    @BeforeSuite
    public void beforeSuite(XmlTest xmlTest, ITestContext context) {
        if (!ReportManager.isInitiated()) {
            this.reports = ReportManager.initiate("BO Init_"+context.getSuite().getName());
        }
        String suiteName = xmlTest.getSuite().getName();
        logger = new RegressionLogger(Listener.logger, ReportManager.recordSuite(suiteName), true);
        logger.setTestName(suiteName);
        logger.setTestClassName(suiteName);
    }


    @BeforeTest(description = "BeforeTest")
    public void beforeTest(ITestContext context, XmlTest xmlTest) {
        String xmlTestName = xmlTest.getName();
        ExtentTest extentLogger = ReportManager.recordXMLTest(xmlTestName, xmlTest.getSuite().getName());
        logger = new RegressionLogger(Listener.logger, extentLogger, true);
        logger.setTestClassName(xmlTestName);
        logger.setTestName(xmlTestName);
    }

    @BeforeClass(description = "BeforeClass")
    public void beforeClass(XmlTest xmlTest, ITestContext iTestContext) {
        String testClassName = iTestContext.getClass().getSimpleName();
        if (!testClassName.equalsIgnoreCase("TestRunner")) {
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

        ReportManager.recordTest(testName, className);
        this.logger = new RegressionLogger(Listener.logger, ReportManager.getTest(iTestResult.getMethod().getMethodName()), true);
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomatedTest.class).length == 0) {
            iTestResult.setStatus(ITestResult.SKIP);
            throw new SkipException("Skipping Test : " + iTestResult.getMethod().getMethodName() + " : No @AutomatedTest annotation found.");
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
        BrowserFactory.closeCurrentBrowser();
    }

    @AfterTest(description = "AfterTest")
    public void afterTest(ITestContext context, XmlTest xmlTest) {
        logger = new RegressionLogger(Listener.logger, ReportManager.getXMLTest(xmlTest.getName()), true);
    }

    @AfterSuite(description = "AfterSuite")
    public void afterSuite(XmlTest xmlTest, ITestContext context){
        logger = new RegressionLogger(Listener.logger, ReportManager.getSuite(xmlTest.getSuite().getName()), true);
    }
}
