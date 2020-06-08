package framework;

import annotations.AutomatedTest;
import annotations.AutomationHistory;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Joiner;
import framework.constants.StringConstants;
import framework.database.ConnectionManager;
import framework.database.models.DBConnectionDTO;
import framework.database.models.SuiteResultsDTO;
import framework.database.models.TestCountDTO;
import framework.database.models.TestResultsDTO;
import framework.webdriver.utils.BrowserStorageAccess;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.Validate;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestResult;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ReportManager {

    // Network Storage Location
    private static String REPORT_FILE_NAME = System.getProperty("reportFileName") == null ? "LocalTestRun" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) : System.getProperty("reportFileName");
    public static String REPORT_DIRECTORY_LOCATION = System.getProperty("jenkinsBuildNumber") == null ? "C:/tmp" : "\\\\qa\\regression_logs\\" + REPORT_FILE_NAME;
    private static String FULL_FILE_PATH = REPORT_DIRECTORY_LOCATION + "\\" + REPORT_FILE_NAME + ".html";
    private static String INIT_SUITE_NAME;

    // Reporting Indices
    private static HashMap<String, ExtentTest> xmlTestMap;
    private static HashMap<String, ExtentTest> classMap;
    private static HashMap<String, ExtentTest> testMap;
    private static HashMap<String, ExtentTest> suiteMap;

    private static ExtentReports extentReports;

    private ReportManager() {

    }

    public static boolean isInitiated() {
        return extentReports != null;
    }

    public static ExtentReports initiate(String suiteName) {
        if(System.getProperty("jenkinsBuildNumber") != null){
            Validate.notNull(System.getProperty("UUID"));
            Validate.notBlank(System.getProperty("UUID"));
        }
        INIT_SUITE_NAME = suiteName;
        classMap = new HashMap<>();
        testMap = new HashMap<>();
        suiteMap = new HashMap<>();
        xmlTestMap = new HashMap<>();
        FULL_FILE_PATH = REPORT_DIRECTORY_LOCATION + "\\" + INIT_SUITE_NAME + "_" + REPORT_FILE_NAME + ".html";
        File file = new File(FULL_FILE_PATH);
        if (!file.exists()) {
            boolean mkdir = new File(REPORT_DIRECTORY_LOCATION).mkdir();
        }

        ExtentSparkReporter extentReporter = new ExtentSparkReporter(FULL_FILE_PATH);
        extentReports = new ExtentReports();
        extentReports.setReportUsesManualConfiguration(true);

        // Configurations
        extentReporter.setAnalysisStrategy(AnalysisStrategy.TEST);
        extentReporter.config().enableTimeline(true);
        extentReporter.config().setTheme(Theme.DARK);
//        extentReporter.config().setCSS(compileCustomCSS());
        extentReporter.config().setJS("document.getElementsByClassName(\"brand-logo blue darken-3\")[0].innerText = \"QA Report\"");
        String applicationName = System.getProperty("ApplicationName") == null ? "Custom" : System.getProperty("ApplicationName");
        extentReporter.config().setDocumentTitle(applicationName + " Regression Health Report");
        extentReporter.config().setReportName(applicationName + " Regression Report");
        extentReports.attachReporter(extentReporter);
        return extentReports;
    }

    static ExtentTest recordSuite(String suiteName) {
        if (!suiteMap.containsKey(suiteName)) {
            ExtentTest suite = extentReports.createTest("SuiteLogger - " + suiteName);
            suite.log(Status.INFO, "This is NOT a test, this has been created for Config Methods like BeforeSuite and AfterSuite Methods Only");
            suiteMap.put(suiteName, suite);
        }

        return suiteMap.get(suiteName);
    }

    static ExtentTest recordClass(String className, String xmlTestName) {
        if (!classMap.containsKey(className) && !className.equalsIgnoreCase("TestRunner")) {
            ExtentTest extentTestClass = xmlTestMap.get(xmlTestName).createNode(className);
            BrowserStorageAccess.getInstance().store(StringConstants.TEST_CLASS_NAME, className);
            classMap.put(className, extentTestClass);
        }
        return classMap.get(className);
    }

    @SuppressWarnings("Duplicates")
    static ExtentTest recordXMLTest(String xmlTestName, String suiteName) {
        if (!xmlTestMap.containsKey(xmlTestName)) {
            ExtentTest extentXMLTest = extentReports.createTest(xmlTestName);
            xmlTestMap.put(xmlTestName, extentXMLTest);
            BrowserStorageAccess.getInstance().store(StringConstants.XML_TEST_NAME, xmlTestName);
        }

        return xmlTestMap.get(xmlTestName);
    }

    @SuppressWarnings("Duplicates")
    static ExtentTest recordTest(String testName, String className, String description) {
        if (!testMap.containsKey(testName)) {
            ExtentTest extentTest = classMap.get(className).createNode(testName, description);
            testMap.put(testName, extentTest);
            BrowserStorageAccess.getInstance().store(StringConstants.TEST_NAME, testName);
        }

        return testMap.get(testName);

    }

    public static void removeClass(String className) {
        extentReports.removeTest(classMap.get(className));
    }

    public static ExtentTest getTest(String testName) {
        return testMap.get(testName);
    }

    public static ExtentTest getClass(String className) {
        return classMap.get(className);
    }

    public static ExtentTest getXMLTest(String xmlTestName) {
        return xmlTestMap.get(xmlTestName);
    }

    public static ExtentTest getSuite(String suiteName) {
        return suiteMap.get(suiteName);
    }

    static boolean recordTestResult(ITestResult iTestResult, String status) {
        AutomatedTest automatedAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomatedTest.class)[0];

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
        String tags = flattenTags(iTestResult);

        if (status.equalsIgnoreCase(Status.FAIL.toString()) || status.equalsIgnoreCase(Status.FATAL.toString())) {
            failureImageURL = REPORT_DIRECTORY_LOCATION + "\\" + iTestResult.getName() + ".png";
            failureReason = iTestResult.getThrowable().getLocalizedMessage();
        }
        TestResultsDTO testResultsDTO = TestResultsDTO.getInstance(clockMove, testCreator, testName, startDate, endDate, failureImageURL, Status.valueOf(status.toUpperCase()),failureReason, buildNumber, suiteName, testRunSource, tags);
        return insertIntoTestResults(testResultsDTO);

    }

    public static boolean insertIntoTestResults(TestResultsDTO testResultsDTO){
        QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        try {
            return regressionDB
                    .update(TestResultsDTO.getJDBCPreparedInsertStatementWithoutParameters(),
                            testResultsDTO.isClockMove(),
                            testResultsDTO.getTestCreator(),
                            testResultsDTO.getTestName(),
                            testResultsDTO.getStartTimeStamp(),
                            testResultsDTO.getEndTimestamp(),
                            testResultsDTO.getFailureImageURL(),
                            testResultsDTO.getStatus().toString(),
                            testResultsDTO.getFailureReason(),
                            testResultsDTO.getBuildNumber(),
                            testResultsDTO.getSuiteName(),
                            testResultsDTO.getTestRunSource(),
                            testResultsDTO.getTags(),
                            testResultsDTO.getUUID()) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean bulkInsertIntoTestResults(List<TestResultsDTO> resultsDTOS){
        QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        Object[][] params = new Object[resultsDTOS.size()][TestResultsDTO.getFieldCount()];
        for (int i = 0; i < resultsDTOS.size(); i++) {
            params[i] = resultsDTOS.get(i).getValuesAsObjectArray();
        }
        try {
            return regressionDB.batch(TestResultsDTO.getJDBCPreparedInsertStatementWithoutParameters(), params).length > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void recordSuiteResults(ISuite iSuite) {
        if (!iSuite.getName().equalsIgnoreCase("Default Suite") && ReportManager.FULL_FILE_PATH.startsWith("\\\\")) {
//            System.out.println("!!!!!! Recording Suite Results to the database. !!!!!!");
            String UUID = System.getProperty("UUID");
            TestCountDTO testCountDTO = TestCountDTO.getTestCountDataFor(UUID);
            String jenkinsBuildNumber = System.getProperty("jenkinsBuildNumber");
            String applicationName = System.getProperty("ApplicationName");
            String reportPath = getReportPath();
            SuiteResultsDTO suiteDTO = SuiteResultsDTO.createInstance(applicationName, testCountDTO.getPassCount(), testCountDTO.getFailCount(), testCountDTO.getSkipCount(), testCountDTO.getFatalCount(), testCountDTO.getWarningCount(), jenkinsBuildNumber, iSuite.getName(), reportPath);
            insertIntoSuiteResults(suiteDTO);
        } else {
            System.out.println("Could not Record Suite: " + iSuite.getName() + " with report path: " + ReportManager.FULL_FILE_PATH);
        }

    }

    public static boolean insertIntoSuiteResults(SuiteResultsDTO suiteResultsDTO){
        QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        try {
            regressionDB.update(SuiteResultsDTO.getJDBCPreparedInsertStatementWithoutParameters(),
                    suiteResultsDTO.getApplicationName(),
                    suiteResultsDTO.getPassedTests(),
                    suiteResultsDTO.getFailedTests(),
                    suiteResultsDTO.getSkippedTests(),
                    suiteResultsDTO.getFatalTests(),
                    suiteResultsDTO.getWarningTests(),
                    suiteResultsDTO.getJenkinsBuildNumber(),
                    suiteResultsDTO.getSuiteName(),
                    suiteResultsDTO.getReportPath(),
                    suiteResultsDTO.getSuiteStartTimeStamp(),
                    suiteResultsDTO.getSuiteEndTimeStamp(),
                    suiteResultsDTO.isSuiteTestBuild(),
                    suiteResultsDTO.shouldShowInPowerBI(),
                    suiteResultsDTO.getUUID());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Could not save the suite results to the database");
            return false;
        }
    }

    private static String flattenTags(ITestResult iTestResult) {
        Method testMethod = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        AutomationHistory[] historyAnnotations = testMethod.getAnnotationsByType(AutomationHistory.class);
        AutomatedTest automatedAnnotation = testMethod.getAnnotationsByType(AutomatedTest.class)[0];

        StringBuilder tags = new StringBuilder();
        tags.append("|").append(automatedAnnotation.Author()).append("|");
        tags.append(automatedAnnotation.Team()).append("|");
        tags.append(automatedAnnotation.PI()).append("|");
        tags.append(automatedAnnotation.FeatureNumber()).append("|");
        tags.append(automatedAnnotation.Iteration()).append("|");
        tags.append(automatedAnnotation.StoryOrDefectNumber()).append("|");
        tags.append(Joiner.on("|").join(automatedAnnotation.Centers())).append("|");
        tags.append(Joiner.on("|").join(automatedAnnotation.Themes())).append("|");

        if (historyAnnotations.length > 0) {
            AutomationHistory historyAnnotation = historyAnnotations[0];
            tags.append(Joiner.on("|").join(historyAnnotation.StoryOrDefectNumbers())).append("|");
        }

        return tags.toString();
    }


    private static java.lang.String compileCustomCSS() {

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

    public static String getReportPath(){
        return "http://qa.idfbins.com/regression_logs/" + REPORT_FILE_NAME + "/" + INIT_SUITE_NAME + "_" + REPORT_FILE_NAME + ".html";
    }
}
