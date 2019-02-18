package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import framework.annotations.AutomatedTest;
import framework.annotations.AutomationHistory;
import framework.database.ConnectionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Arrays;
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

    public static boolean recordTestResult(ITestResult iTestResult, String status) {

        AutomatedTest automatedAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomatedTest.class)[0];
        AutomationHistory[] historyAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomationHistory.class);
        Test[] testAnnotation = iTestResult.getClass().getAnnotationsByType(Test.class);

        Timestamp startDate = new Timestamp(iTestResult.getStartMillis());
        Timestamp endDate = new Timestamp(iTestResult.getEndMillis());
        String failureImageURL = null;
        String failureReason = null;
        boolean clockMove = Arrays.stream(iTestResult.getTestContext().getIncludedGroups()).anyMatch(s -> s.equalsIgnoreCase("ClockMove"));
        String testCreator = automatedAnnotation.Author();
        String testName = iTestResult.getMethod().getMethodName();
        String buildNumber = System.getProperty("jenkinsBuildNumber");
        String suiteName = iTestResult.getTestContext().getSuite().getName();
        String testRunSource = System.getProperty("startedByUser");
        String clockMoveTimeUnit = null;
        int clockMoveAmount = 0;
        String tags = flattenTags(automatedAnnotation, historyAnnotation);

        if (status.equalsIgnoreCase("Failure")) {
            failureImageURL = REPORT_DIRECTORY_LOCATION + "\\" + iTestResult.getName() + ".png";
            failureReason = iTestResult.getThrowable().getLocalizedMessage();
        }

        QueryRunner regressionDB = ConnectionManager.getRegressionDB();

        try {
            return regressionDB
                    .update("INSERT INTO TestResults" +
                                    "(ClockMove, TestCreator, TestName, StartTime, " +
                                    "EndTime, FailureImageURL, TestStatus, FailureReason, ClockMoveTimeUnit, ClockMoveAmount, " +
                                    "BuildNumber, SuiteName, TestRunSource, Tags) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
            clockMove, testCreator, testName, startDate, endDate, failureImageURL, status, failureReason, clockMoveTimeUnit,
                            clockMoveAmount, buildNumber, suiteName, testRunSource, tags) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String flattenTags(AutomatedTest automatedAnnotation, AutomationHistory[] historyAnnotations) {
        StringBuilder tags = new StringBuilder();
        tags.append("|").append(automatedAnnotation.Author()).append("|");
        tags.append(automatedAnnotation.Team()).append("|");
        tags.append(automatedAnnotation.PI()).append("|");
        tags.append(automatedAnnotation.FeatureNumber()).append("|");
        tags.append(automatedAnnotation.Iteration()).append("|");
        tags.append(automatedAnnotation.StoryOrDefectNumber()).append("|");
        for (String center : automatedAnnotation.Centers()) {
            tags.append(center).append("|");
        }
        for (String theme : automatedAnnotation.Themes()) {
            tags.append(theme).append("|");
        }

        if (historyAnnotations.length > 0) {
            AutomationHistory historyAnnotation = historyAnnotations[0];
            for (String storyDefectNumber : historyAnnotation.StoryOrDefectNumbers()) {
                tags.append(storyDefectNumber).append("|");
            }
        }

        return tags.toString();
    }
}
