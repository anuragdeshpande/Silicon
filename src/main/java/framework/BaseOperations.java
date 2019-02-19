package framework;

import com.aventstack.extentreports.ExtentTest;
import framework.webdriver.BrowserFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class BaseOperations {

    private ExtentTest logger;
    String className;

    @BeforeClass
    public void beforeClass() {
        this.className = this.getClass().getName();
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

    void setLogger(ExtentTest logger) {
        this.logger = logger;
    }

    public ExtentTest logger() {
        return this.logger;
    }
}
