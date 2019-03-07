package framework;

import framework.annotations.AutomatedTest;
import framework.logger.RegressionLogger;
import framework.webdriver.BrowserFactory;
import framework.webdriver.Interact;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;

public class BaseOperations {
    protected RegressionLogger logger;

    @BeforeClass
    public void initialize(){

    }

    @BeforeMethod(description = "BeforeMethod")
    public void beforeMethod(Method method, ITestResult iTestResult){
        if(!iTestResult.getMethod().getXmlTest().getSuite().getName().equalsIgnoreCase("Default Suite")){
            if(ReportManager.getTest(iTestResult.getMethod().getMethodName()) == null){
                ReportManager.recordTest(iTestResult);
            }

            this.logger = new RegressionLogger(Listener.logger, ReportManager.getTest(iTestResult.getMethod().getMethodName()), true);
        } else {
            this.logger = new RegressionLogger(null, null, false);
        }

        if(iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomatedTest.class).length == 0){
            iTestResult.setStatus(ITestResult.SKIP);
            throw new SkipException("Skipping Test : "+ iTestResult.getMethod().getMethodName() + " : No @AutomatedTest annotation found.");
        }
    }

    @BeforeClass
    public void beforeClass() {
    }

    @AfterTest
    public void afterTest() {

    }

    @AfterMethod
    public void afterMethod(ITestResult iTestResult) {
        System.out.println("Test Name: " + iTestResult.getName());
    }

    @AfterClass
    public void afterClass() {
        BrowserFactory.closeCurrentBrowser();
    }
}
