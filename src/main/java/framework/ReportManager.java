package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

import java.util.HashMap;

class ReportManager {

    // Network Storage Location
    private static String REPORT_FILE_NAME = System.getProperty("reportFileName");
    static String REPORT_DIRECTORY_LOCATION = "\\\\qa\\regression_logs\\" + REPORT_FILE_NAME;

    // Reporting Indices
    private static HashMap<String, ExtentTest> classMap;
    private static HashMap<String, ExtentTest> testMap;


    private ExtentTest classLogger;
    private ExtentTest testLogger;
    private String currentClassName;
    private String currentTestName;

    private static ExtentReports extentReports;

    private ReportManager() {

    }

    static ExtentReports initiate() {
        extentReports = new ExtentReports();
        ExtentHtmlReporter extentReporter;

        classMap = new HashMap<>();
        testMap = new HashMap<>();

        extentReporter = new ExtentHtmlReporter(REPORT_DIRECTORY_LOCATION + "\\" + REPORT_FILE_NAME + ".html");

        // Configurations
        extentReporter.config().enableTimeline(true);
        extentReporter.config().setAutoCreateRelativePathMedia(true);
        extentReporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(extentReporter);
        return extentReports;
    }

    private static ExtentTest recordClass(ITestResult testClass) {

        String ID = testClass.getTestClass().getRealClass().getSimpleName();

        if (!classMap.containsKey(ID)) {
            ExtentTest extentTestClass = extentReports.createTest(ID);
            classMap.put(ID, extentTestClass);
        }
        return classMap.get(ID);
    }

    public static ExtentTest recordTest(ITestResult test) {

        ExtentTest testClass = recordClass(test);
        ExtentTest testNode = testClass.createNode(test.getName());
        testMap.put(test.getName(), testNode);

        return testNode;
    }

    public static ExtentTest getTest(String ID) {
        return testMap.get(ID);
    }
}
