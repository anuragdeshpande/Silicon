package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import framework.webdriver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class Listener implements ISuiteListener, ITestListener {

    private ExtentReports extentReports;



    @Override
    public void onStart(ISuite iSuite) {
        this.extentReports = ReportManager.initiate();
    }

    @Override
    public void onFinish(ISuite iSuite) {
        this.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ExtentTest testLogger = ReportManager.recordTest(iTestResult);
        ((BaseOperations) iTestResult.getInstance()).setLogger(testLogger);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Success");
        ReportManager.getTest(iTestResult.getName()).pass(iTestResult.getName() + ": Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Failure");
        ExtentTest testNode = ReportManager.getTest(iTestResult.getName());
        try {
            testNode.addScreenCaptureFromPath(this.captureScreenshot(iTestResult));
        } catch (Exception e) {
            testNode.warning(e);
        }
        testNode.log(Status.FAIL, iTestResult.getName() + ": Failed");
        testNode.fail(iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Skipped");
        ReportManager.getTest(iTestResult.getName()).skip(iTestResult.getName() + ": Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    // This method handles on start for classes
    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("After Test Test");
    }

    private String captureScreenshot(ITestResult iTestResult) {
        WebDriver driver = ((BaseOperations) iTestResult.getInstance()).driver;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(ReportManager.REPORT_DIRECTORY_LOCATION + "\\" + iTestResult.getName() + ".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
