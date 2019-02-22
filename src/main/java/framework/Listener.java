package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import framework.annotations.AutomatedTest;
import framework.webdriver.BrowserFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class Listener implements ISuiteListener, ITestListener, IExecutionListener {

    private ExtentReports extentReports;

    // Fires before any suite starts
    @Override
    public void onExecutionStart() {
        this.extentReports = ReportManager.initiate();
    }

    // Fires at the beginning of each suite
    @Override
    public void onStart(ISuite iSuite) {
        ReportManager.recordSuite(iSuite.getName());
    }


    // Fires at the beginning of each test class
    @Override
    public void onStart(ITestContext iTestContext) {
        String className = iTestContext.getClass().getCanonicalName();
        ReportManager.recordClass(className, iTestContext);
    }

    // Fires at the beginning of each test
    @Override
    public void onTestStart(ITestResult iTestResult) {
        ExtentTest testLogger = ReportManager.recordTest(iTestResult);
        AutomatedTest[] annotations = iTestResult.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotationsByType(AutomatedTest.class);
        if (annotations.length == 0) {
            testLogger.fatal("Fatal Error: @AutomatedTest annotation not found.");
            iTestResult.setStatus(ITestResult.SKIP);
            throw new SkipException("Fatal Error: @AutomatedTest annotation not found.");
        } else {
            AutomatedTest automatedTest = annotations[0];
            testLogger.assignAuthor(automatedTest.Author());
            testLogger.assignCategory(automatedTest.FeatureNumber(), automatedTest.Iteration(), automatedTest.PI(), automatedTest.StoryOrDefectNumber(), automatedTest.Team());
            for (String s : automatedTest.Centers()) {
                testLogger.assignCategory(s);
            }
            for (String s : automatedTest.Themes()) {
                testLogger.assignCategory(s);
            }
        }
    }


    // fires when a test is successful
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ReportManager.getTest(iTestResult.getName()).pass(iTestResult.getName() + ": Passed");
        ReportManager.recordTestResult(iTestResult, "Success");
    }

    // fires when a test fails
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ExtentTest testNode = ReportManager.getTest(iTestResult.getName());
        try {
            testNode.addScreenCaptureFromPath(this.captureScreenshot(iTestResult));
        } catch (Exception e) {
            testNode.warning(e);
        }
        testNode.log(Status.FAIL, iTestResult.getName() + ": Failed");
        testNode.fail(iTestResult.getThrowable());
        ReportManager.recordTestResult(iTestResult, "Failure");
    }

    // fires when a test is skipped
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ReportManager.getTest(iTestResult.getName()).skip(iTestResult.getName() + ": Skipped");
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotationsByType(AutomatedTest.class).length > 0) {
            ReportManager.recordTestResult(iTestResult, "Skipped");
        }
    }

    // fires when the test fails, but passes a certain test coverage percentage.
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    // Fires on Finishing a test class
    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("After Test Test");
    }

    // Fires at the end of each suite.
    @Override
    public void onFinish(ISuite iSuite) {
    }

    // Fires at the end of all suites.
    @Override
    public void onExecutionFinish() {
        this.extentReports.flush();
    }

    private String captureScreenshot(ITestResult iTestResult) {
        WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationFilePath = ReportManager.REPORT_DIRECTORY_LOCATION + "\\" + iTestResult.getName() + ".png";
        try {
            File destFile = new File(destinationFilePath);
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return destinationFilePath;
    }


}
