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
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

class ReportManager {

    // Network Storage Location
    static String REPORT_FILE_NAME = System.getProperty("reportFileName") == null ? "LocalTestRun"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) : System.getProperty("reportFileName");
    static String REPORT_DIRECTORY_LOCATION = System.getProperty("jenkinsBuildNumber") == null ? "C:/tmp":"\\\\qa\\regression_logs\\" + REPORT_FILE_NAME;
    static String FULL_FILE_PATH = REPORT_DIRECTORY_LOCATION + "\\" + REPORT_FILE_NAME + ".html";

    // Reporting Indices
    private static HashMap<String, ExtentTest> xmlTestMap;
    private static HashMap<String, ExtentTest> classMap;
    private static HashMap<String, ExtentTest> testMap;
    private static HashMap<String, ExtentTest> suiteMap;

    private static ExtentReports extentReports;

    private ReportManager() {

    }

    static boolean isInitiated(){
        return extentReports != null;
    }

    static ExtentReports initiate() {
        extentReports = new ExtentReports();
        ExtentHtmlReporter extentReporter;

        classMap = new HashMap<>();
        testMap = new HashMap<>();
        suiteMap = new HashMap<>();
        xmlTestMap = new HashMap<>();

        File file = new File(FULL_FILE_PATH);
        if(!file.exists()){
            boolean mkdir = new File(REPORT_DIRECTORY_LOCATION).mkdir();
        }

        extentReporter = new ExtentHtmlReporter(FULL_FILE_PATH);

        // Configurations
        extentReporter.setAnalysisStrategy(AnalysisStrategy.SUITE);
        extentReporter.config().setTheme(Theme.DARK);
//        extentReporter.config().setCSS(compileCustomCSS());
        extentReporter.config().setJS("document.getElementsByClassName(\"brand-logo blue darken-3\")[0].innerText = \"QA Report\"");
        extentReporter.config().setDocumentTitle("ART Regression Health Report");
        extentReporter.config().setReportName("Regression");
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

    static ExtentTest recordClass(String className, String xmlTestName) {
        if (!classMap.containsKey(className) && !className.equalsIgnoreCase("TestRunner")) {
            ExtentTest extentTestClass = xmlTestMap.get(xmlTestName).createNode(className);
            classMap.put(className, extentTestClass);
        }
        return classMap.get(className);
    }

    @SuppressWarnings("Duplicates")
    static ExtentTest recordXMLTest(String xmlTestName, String suiteName){
        if(!xmlTestMap.containsKey(xmlTestName)){
            ExtentTest extentXMLTest = suiteMap.get(suiteName).createNode(xmlTestName);
            xmlTestMap.put(xmlTestName, extentXMLTest);
        }

        return xmlTestMap.get(xmlTestName);
    }

    @SuppressWarnings("Duplicates")
    static ExtentTest recordTest(String testName, String className) {
        if(!testMap.containsKey(testName)){
            ExtentTest extentTest = classMap.get(className).createNode(testName);
            testMap.put(testName,extentTest);
        }

        return testMap.get(testName);

    }

    static void removeClass(String className){
        extentReports.removeTest(classMap.get(className));
    }

    static ExtentTest getTest(String testName) {
        return testMap.get(testName);
    }
    static ExtentTest getClass(String className){return classMap.get(className);}
    static ExtentTest getXMLTest(String xmlTestName){return xmlTestMap.get(xmlTestName);}
    static ExtentTest getSuite(String suiteName){return suiteMap.get(suiteName);}

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

    public static void recordSuiteResults(ISuite iSuite){
        if(!iSuite.getName().equalsIgnoreCase("Default Suite") && ReportManager.FULL_FILE_PATH.startsWith("\\\\")){
            System.out.println("!!!!!! Recording Suite Results to the database. !!!!!!");
            iSuite.getResults().values().forEach(iSuiteResult -> {
                ITestContext testContext = iSuiteResult.getTestContext();
                int passedTests = testContext.getPassedTests().size();
                int failedTests = testContext.getFailedTests().size();
                int skippedTests = testContext.getSkippedTests().size();

                double passPercentage = ((double) passedTests / (passedTests + failedTests + skippedTests))*100;
                double failPercentage = ((double) failedTests / (passedTests + failedTests + skippedTests))*100;
                String jenkinsBuildNumber = System.getProperty("jenkinsBuildNumber");
                String applicationName = System.getProperty("ApplicationName");
                String suiteName = iSuite.getName();
                String reportPath = "http://qa.idfbins.com/regression_logs/"+REPORT_FILE_NAME+"/"+REPORT_FILE_NAME+".html";
                QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(Environment.REPORTING);
                try{
                    regressionDB.update("INSERT INTO SuiteResults(ApplicationName, PassPercentage, FailPercentage, SkippedCount, BuildNumber, SuiteName, ReportPath) " +
                            "values (?,?,?,?,?,?,?)", applicationName, passPercentage, failPercentage, skippedTests, jenkinsBuildNumber, suiteName, reportPath);
                } catch (SQLException e) {
                    e.printStackTrace();
                    Assert.fail("Could not save the suite results to the database");
                }
            });
        } else {
            System.out.println("Could not Record Suite: " + iSuite.getName() +" with report path: " + ReportManager.FULL_FILE_PATH);
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
