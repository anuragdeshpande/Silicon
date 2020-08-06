package framework;

import annotations.AutomatedTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.idfbins.driver.BaseTest;
import framework.guidewire.ErrorMessageOnScreenException;
import framework.guidewire.GuidewireInteract;
import framework.reports.models.TestDetailsDTO;
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
        System.setProperty("SuiteStartTime", String.valueOf(System.currentTimeMillis()));
        String suiteName = iSuite.getName();
        logger = LogManager.getLogger("RegressionLogs-"+timeStamp);
        this.extentReports = ReportManager.initiate(suiteName+"_"+timeStamp);
        writeToDatabase = !suiteName.equalsIgnoreCase("Default Suite") && System.getProperty("MarkAsTestBuild", "true").equalsIgnoreCase("false");
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
        Test[] testAnnotations = iTestResult.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotationsByType(Test.class);
        ExtentTest testLogger = ReportManager.recordTest(buildTestDetailsDTO(iTestResult), testAnnotations.length > 0? testAnnotations[0].description() : null);
        AutomatedTest[] annotations = iTestResult.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotationsByType(AutomatedTest.class);
        if (annotations.length == 0) {
            testLogger.fail("Fatal Error: @AutomatedTest annotation not found.");
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
        ExtentTest test = ReportManager.getTest(buildTestDetailsDTO(iTestResult));
        test.getModel().setStartTime(new Date(iTestResult.getStartMillis()));
        test.getModel().setEndTime(new Date(iTestResult.getEndMillis()));
        test.pass(iTestResult.getName() + ": Passed");
        if(writeToDatabase){
            ReportManager.recordTestResult(iTestResult, Status.PASS.toString());
        }

    }

    // fires when a test fails
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ExtentTest testNode = ReportManager.getTest(buildTestDetailsDTO(iTestResult));
        testNode.getModel().setStartTime(new Date(iTestResult.getStartMillis()));
        testNode.getModel().setEndTime(new Date(iTestResult.getEndMillis()));

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
            testNode.log(Status.FAIL, iTestResult.getName()+" Failed with critical system failure");
            testNode.fail(errorMessageFromScreen);

            iTestResult.setThrowable(new ErrorMessageOnScreenException(errorMessageFromScreen));
        }

        if(writeToDatabase){
            ReportManager.recordTestResult(iTestResult, testNode.getStatus().toString());
        }
    }

    // fires when a test is skipped
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ExtentTest extentLogger = ReportManager.getTest(buildTestDetailsDTO(iTestResult));
        extentLogger.getModel().setStartTime(new Date(iTestResult.getStartMillis()));
        extentLogger.getModel().setEndTime(new Date(iTestResult.getEndMillis()));
        extentLogger.skip(iTestResult.getName() + ": Skipped");
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotationsByType(AutomatedTest.class).length > 0) {
            extentLogger.log(Status.SKIP, "Test is not marked with @AutomationTest annotation: Skipping Test");
            if(writeToDatabase){
                ReportManager.recordTestResult(iTestResult, Status.SKIP.toString());
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
    }

    // Fires at the end of each suite.
    @Override
    public void onFinish(ISuite iSuite) {
        System.setProperty("SuiteEndTime", String.valueOf(System.currentTimeMillis()));
//        EMailWriter.writeNewEMail().sendRegressionReport(, "http://qa.idfbins.com/regression_logs/"+ReportManager.REPORT_FILE_NAME+"/"+ReportManager.REPORT_FILE_NAME+".html");
        this.extentReports.flush();
        ReportManager.recordSuiteResults(iSuite);
    }

    @SuppressWarnings("Duplicates")
    private String captureScreenshot(ITestResult iTestResult) {
        WebDriver driver;
        if(System.getProperty("LithiumSafe", "false").equalsIgnoreCase("true")){
            driver = BrowserFactory.getCurrentBrowser().getDriver();
        } else {
            driver = ((BaseTest) iTestResult.getInstance()).getBaseTestDriverForListener();
        }
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

    private TestDetailsDTO buildTestDetailsDTO(ITestResult iTestResult){
        TestDetailsDTO dto = new TestDetailsDTO();
        dto.setTestName(iTestResult.getMethod().getConstructorOrMethod().getMethod().getName());
        dto.setClassName(iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName());

        return dto;
    }


}
