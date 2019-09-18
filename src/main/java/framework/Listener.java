package framework;

import annotations.AutomatedTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import framework.guidewire.ErrorMessageOnScreenException;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listener implements ISuiteListener, ITestListener{

    private ExtentReports extentReports;
    public static Logger logger;
    public boolean writeToDatabase;


    // Fires at the beginning of each suite
    @Override
    public void onStart(ISuite iSuite) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String suiteName = iSuite.getName();
        logger = LogManager.getLogger("RegressionLogs-"+timeStamp);
        this.extentReports = ReportManager.initiate(suiteName+"_"+timeStamp);
        writeToDatabase = !suiteName.equalsIgnoreCase("Default Suite");
    }


    // Fires at the beginning of each test class
    @Override
    public void onStart(ITestContext iTestContext) {
        // do nothing
//        System.out.println("In Test Start");
    }

    // Fires at the beginning of each test
    @Override
    public void onTestStart(ITestResult iTestResult) {
//        System.out.println("In onTestStart");
        String testName = iTestResult.getMethod().getConstructorOrMethod().getMethod().getName();
        String className = iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName();

//        System.out.println("Starting Test - "+ testName);
        Test[] testAnnotations = iTestResult.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotationsByType(Test.class);
        ExtentTest testLogger = ReportManager.recordTest(testName, className, testAnnotations.length > 0? testAnnotations[0].description() : null);
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
        if(writeToDatabase){
            ReportManager.recordTestResult(iTestResult, "Success");
        }

    }

    // fires when a test fails
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ExtentTest testNode = ReportManager.getTest(iTestResult.getName());
        try {
            testNode.addScreenCaptureFromPath(this.captureScreenshot(iTestResult));
        } catch (Exception e) {
            testNode.fail(e);
        }

        testNode.log(Status.FAIL, iTestResult.getName() + ": Failed");
        testNode.fail(iTestResult.getThrowable());

        // Special Guidewire check - will be moved at a later date to the DOM listener functionality
        if(GuidewireInteract.hasErrorMessageOnScreen()){
            String errorMessageFromScreen = GuidewireInteract.getErrorMessageFromScreen();
            testNode.log(Status.FATAL, iTestResult.getName()+" Failed with critical system failure");
            testNode.fatal(errorMessageFromScreen);
            iTestResult.setThrowable(new ErrorMessageOnScreenException(errorMessageFromScreen));
        }

        if(writeToDatabase){
            ReportManager.recordTestResult(iTestResult, "Failure");
        }

        BrowserFactory.closeCurrentBrowser();
    }

    // fires when a test is skipped
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ExtentTest extentLogger = ReportManager.getTest(iTestResult.getName());
        if(extentLogger == null){
            extentLogger = ReportManager.getClass(iTestResult.getTestClass().getRealClass().getSimpleName());
        }
        extentLogger.skip(iTestResult.getName() + ": Skipped");
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotationsByType(AutomatedTest.class).length > 0) {
            extentLogger.log(Status.SKIP, "Test is not marked with @AutomationTest annotation: Skipping Test");
            if(writeToDatabase){
                ReportManager.recordTestResult(iTestResult, "Skipped");
            }
        }
    }

    // fires when the test fails, but passes a certain test coverage percentage.
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    // Fires on Finishing a test class
    @Override
    public void onFinish(ITestContext iTestContext) {
//        System.out.println("After Test Test");
    }

    // Fires at the end of each suite.
    @Override
    public void onFinish(ISuite iSuite) {
//        EMailWriter.writeNewEMail().sendRegressionReport(, "http://qa.idfbins.com/regression_logs/"+ReportManager.REPORT_FILE_NAME+"/"+ReportManager.REPORT_FILE_NAME+".html");
        this.extentReports.flush();
        ReportManager.recordSuiteResults(iSuite);
    }

    @SuppressWarnings("Duplicates")
    private String captureScreenshot(ITestResult iTestResult) {
        WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationFilePath = ReportManager.REPORT_DIRECTORY_LOCATION + "\\" + iTestResult.getName() + ".png";
        try {
            File destFile = new File(destinationFilePath);
            FileUtils.moveFile(scrFile, destFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return destinationFilePath;
    }


}
