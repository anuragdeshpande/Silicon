package framework;

import com.aventstack.extentreports.AnalysisStrategy;
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

    private ExtentReports extentReports;
    private ExtentHtmlReporter extentReporter;
    private ExtentTest suite;
    private ExtentTest classLogger;
    private String className;
    private ExtentTest logger;
    private String directoryName;
    private HashMap<String, ExtentTest> loggers;

    public Listener() {
        this.loggers = new HashMap<>();
        extentReports = new ExtentReports();
        extentReports.setAnalysisStrategy(AnalysisStrategy.SUITE);

        // Create new directory and files
        String rootPath = "\\\\qa\\regression_logs\\";
        String resultsFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy_HHmmssA"));
        this.directoryName = rootPath + resultsFileName;

        // Set path to report
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
        suite = this.extentReports.createTest(iSuite.getName());
    }

    @Override
    public void onFinish(ISuite iSuite) {
        this.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("***  On Test Start  ***");
        String simpleName = iTestResult.getTestClass().getRealClass().getSimpleName();

        if (!simpleName.equalsIgnoreCase(this.className) && !loggers.containsKey(simpleName)) {
            this.className = simpleName;
            this.classLogger = suite.createNode(this.className);
            loggers.put(simpleName, this.classLogger);
        }
        this.logger = loggers.get(simpleName).createNode(iTestResult.getName());
        ((BaseOperations) iTestResult.getInstance()).setLogger(this.logger);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Success");
        this.logger.pass(iTestResult.getName() + ": Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Failure");
        try {
            this.logger.addScreenCaptureFromPath(this.captureScreenshot(iTestResult));
        } catch (Exception e) {
            this.logger.warning(e);
        }
        this.logger.log(Status.FAIL, iTestResult.getName() + ": Failed");
        this.logger.fail(iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Skipped");
        this.logger.skip(iTestResult.getName() + ": Skipped");
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
            FileUtils.copyFile(scrFile, new File(this.directoryName + "\\" + iTestResult.getName() + ".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
