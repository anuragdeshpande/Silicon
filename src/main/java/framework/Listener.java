package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import framework.webdriver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Listener implements ISuiteListener, ITestListener {

    private static String directoryName;
    private ExtentReports extentReports;
    private ExtentHtmlReporter extentReporter;
    private ExtentTest suite;
    private ExtentTest classLogger;
    private String className;
    private ExtentTest testLogger;
    private HashMap<String, ExtentTest> classLevel;
    private HashMap<String, ExtentTest> testLevel;
    private String currentTestName;

    public Listener() {
        extentReports = new ExtentReports();
        // Set path to report

        // Create new directory and files
        String rootPath = "\\\\qa\\regression_logs\\";
        String resultsFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy_HHmmssA"));
        directoryName = rootPath + resultsFileName;

        if (new File(directoryName).mkdir()) {
            extentReporter = new ExtentHtmlReporter(directoryName + "\\" + resultsFileName + ".html");
        } else {
            extentReporter = new ExtentHtmlReporter(rootPath + resultsFileName + ".html");
        }
        // Configurations
        extentReporter.config().enableTimeline(true);
        extentReporter.config().setAutoCreateRelativePathMedia(true);
        extentReporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(extentReporter);

    }

    @Override
    public void onStart(ISuite iSuite) {
        // Create the suite name
        this.classLevel = new HashMap<>();
        this.testLevel = new HashMap<>();
        //suite = this.extentReports.createTest(iSuite.getName());
    }

    @Override
    public void onFinish(ISuite iSuite) {
        this.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("***  On Test Start  ***");
        String simpleClassName = iTestResult.getTestClass().getRealClass().getSimpleName();
        String testName = iTestResult.getName();

        if (!simpleClassName.equalsIgnoreCase(this.className) && !classLevel.containsKey(simpleClassName)) {
            this.className = simpleClassName;
            this.classLogger = this.extentReports.createTest(this.className);
            classLevel.put(simpleClassName, this.classLogger);
        }

        if (!testName.equalsIgnoreCase(this.currentTestName) && !testLevel.containsKey(testName)) {
            this.currentTestName = testName;
            this.testLogger = classLevel.get(simpleClassName).createNode(iTestResult.getName());
            testLevel.put(testName, this.testLogger);
        }

        this.testLogger = testLevel.get(testName);
        ((BaseOperations) iTestResult.getInstance()).setLogger(this.testLogger);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Success");
        testLevel.get(iTestResult.getName()).pass(iTestResult.getName() + ": Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Failure");
        try {
            testLevel.get(iTestResult.getName()).addScreenCaptureFromPath(this.captureScreenshot(iTestResult));
        } catch (Exception e) {
            testLevel.get(iTestResult.getName()).warning(e);
        }
        testLevel.get(iTestResult.getName()).log(Status.FAIL, iTestResult.getName() + ": Failed");
        testLevel.get(iTestResult.getName()).fail(iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Skipped");
        testLevel.get(iTestResult.getName()).skip(iTestResult.getName() + ": Skipped");
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
            FileUtils.copyFile(scrFile, new File(directoryName + "\\" + iTestResult.getName() + ".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
