package framework;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import framework.annotations.AutomatedTest;
import framework.annotations.AutomationHistory;
import framework.database.ConnectionManager;
import framework.enums.Environment;
import org.apache.commons.dbutils.QueryRunner;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;

class ReportManager {

    // Network Storage Location
    private static String REPORT_FILE_NAME = System.getProperty("reportFileName") == null ? "Default" : System.getProperty("reportFileName");
    static String REPORT_DIRECTORY_LOCATION = "\\\\qa\\regression_logs\\" + REPORT_FILE_NAME;

    // Reporting Indices
    private static HashMap<String, ExtentTest> classMap;
    private static HashMap<String, ExtentTest> testMap;
    private static HashMap<String, ExtentTest> suiteMap;

    private static ExtentReports extentReports;

    private ReportManager() {

    }

    static ExtentReports initiate() {
        extentReports = new ExtentReports();
        ExtentHtmlReporter extentReporter;

        classMap = new HashMap<>();
        testMap = new HashMap<>();
        suiteMap = new HashMap<>();

        File file = new File(REPORT_DIRECTORY_LOCATION + "\\" + REPORT_FILE_NAME + ".html");
        if(!file.exists()){
            boolean mkdir = new File(REPORT_DIRECTORY_LOCATION).mkdir();
        }

        extentReporter = new ExtentHtmlReporter(REPORT_DIRECTORY_LOCATION + "\\" + REPORT_FILE_NAME + ".html");

        // Configurations
        extentReporter.setAnalysisStrategy(AnalysisStrategy.SUITE);
        extentReporter.config().setTheme(Theme.DARK);
        extentReporter.config().setCSS(compileCustomCSS());
        extentReporter.config().setJS("document.getElementsByClassName(\"brand-logo blue darken-3\")[0].innerText = \"QA Report\"");
        extentReporter.config().setDocumentTitle("ART Regression Health Report");
        extentReporter.config().setReportName("Regression");
        extentReporter.config().setChartVisibilityOnOpen(false);
        extentReporter.setAppendExisting(true);
        extentReports.attachReporter(extentReporter);
        return extentReports;
    }

    static ExtentTest recordSuite(String suiteName){
        if(!suiteMap.containsKey(suiteName)){
            ExtentTest suite = extentReports.createTest(suiteName);
            suiteMap.put(suiteName, suite);
        }

        return suiteMap.get(suiteName);
    }

    static ExtentTest recordClass(String className, String suiteName) {
        if (!classMap.containsKey(className) && !className.equalsIgnoreCase("org.testng.TestRunner")) {
            ExtentTest extentTestClass = suiteMap.get(suiteName).createNode(className);
            classMap.put(className, extentTestClass);
        }
        return classMap.get(className);
    }

    static ExtentTest recordTest(ITestResult test) {
        String testName = test.getMethod().getConstructorOrMethod().getMethod().getName();
        if(testMap.containsKey(testName)){
            return testMap.get(testName);
        } else {
            String className = test.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName();
            String suiteName = test.getMethod().getXmlTest().getSuite().getName();
            ExtentTest testClass = recordClass(className, suiteName);
            ExtentTest testNode = testClass.createNode(testName);
            testMap.put(testName, testNode);

            return testNode;
        }

    }

    static void removeClass(String className){
        extentReports.removeTest(classMap.get(className));
    }

    static ExtentTest getTest(String testName) {
        return testMap.get(testName);
    }

    static boolean recordTestResult(ITestResult iTestResult, String status) {

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

        QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(Environment.REPORTING);

        try {
            return regressionDB
                    .update("INSERT INTO TestResults" +
                                    "(ClockMove, TestCreator, TestName, StartTime, " +
                                    "EndTime, FailureImageURL, TestStatus, FailureReason, " +
                                    "BuildNumber, SuiteName, TestRunSource, Tags) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
            clockMove, testCreator, testName, startDate, endDate, failureImageURL, status, failureReason, buildNumber,
                            suiteName, testRunSource, tags) > 0;
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

    private static java.lang.String compileCustomCSS(){

        return (".leaf.pass > .collapsible-header, .leaf.pass > .collapsible-body {\n" +
                "            border: #b5d6a7;\n" +
                "            border-left: 2px solid #b5d6a7;\n" +
                "            }\n" +
                "\n" +
                "            .leaf.fail > .collapsible-header, .leaf.fail > .collapsible-body {\n" +
                "            border: #ff9a9a;\n" +
                "            border-left: 2px solid #ff9a9a;\n" +
                "            }\n" +
                "\n" +
                "            .leaf.fatal > .collapsible-header, .leaf.fatal > .collapsible-body {\n" +
                "            border: darkorange;\n" +
                "            border-left: 2px solid darkorange;\n" +
                "            }\n" +
                "\n" +
                "            span.node-time.label.grey.lighten-1.white-text, span.node-duration.label.grey.lighten-1.white-text {\n" +
                "            background-color: transparent !important;\n" +
                "            }\n" +
                "\n" +
                "            span.category.label.white-text {\n" +
                "            display: table-cell;\n" +
                "            /*    background: #444 !important; */\n" +
                "            }\n" +
                "\n" +
                "            span.category.label.white-text:after {\n" +
                "            content: '\\A' !important;\n" +
                "            white-space: pre;\n" +
                "            }\n" +
                "\n" +
                "            span.author{\n" +
                "            display: none;\n" +
                "            }\n" +
                "\n" +
                "            .collapsible-header.active > span.author{\n" +
                "            display: table-cell;\n" +
                "            float: right;\n" +
                "            }\n" +
                "\n" +
                "            span.category.label.white-text:before, span.author.label.white-text:before {\n" +
                "            font-family: 'material icons';\n" +
                "            position: relative;\n" +
                "            top: 2px;\n" +
                "            left: -2px;\n" +
                "            }\n" +
                "\n" +
                "            span.category.label.white-text:before {\n" +
                "            content: 'local_offer';\n" +
                "            }\n" +
                "\n" +
                "            span.author.label.white-text:before {\n" +
                "            content: 'person';\n" +
                "            }\n" +
                "\n" +
                "            .collapsible-header > span.label{\n" +
                "            border-radius: 0;\n" +
                "            }" +
                "\n" +
                "            a.brand-logo.blue.darken-3{\n" +
                "            padding-left: 10px;\n" +
                "            }" +
                "\n" +
                "            #nav-mobile li:last-child{\n" +
                "            display: none;\n" +
                "            }");
    }
}
