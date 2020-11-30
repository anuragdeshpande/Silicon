package framework;

import annotations.AutomatedTest;
import annotations.AutomationHistory;
import annotations.ClockMoveTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Joiner;
import framework.constants.StringConstants;
import framework.database.ConnectionManager;
import framework.database.models.*;
import framework.reports.models.TestDetailsDTO;
import framework.utils.fileFilters.JSONFileNameFilter;
import framework.webdriver.utils.BrowserStorageAccess;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.lang3.Validate;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportManager {

    // Network Storage Location
    private static final String GLOBAL_SUITE_NAME = System.getProperty("globalSuiteName") == null ? "" : System.getProperty("globalSuiteName");
    private static final String REPORT_FILE_NAME = System.getProperty("reportFileName") == null ? "LocalTestRun" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) : System.getProperty("reportFileName");
    public static String REPORT_DIRECTORY_LOCATION = System.getProperty("jenkinsBuildNumber") == null ? "C:/tmp/" + REPORT_FILE_NAME : (!GLOBAL_SUITE_NAME.isEmpty() ? StringConstants.DEFAULT_REPORT_LOCATION_PATH : "\\\\qa\\regression_logs\\" + REPORT_FILE_NAME);
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
        if (System.getProperty("jenkinsBuildNumber") != null) {
            Validate.notNull(System.getProperty("UUID"));
            Validate.notBlank(System.getProperty("UUID"));
        }

        INIT_SUITE_NAME = suiteName;
        classMap = new HashMap<>();
        testMap = new HashMap<>();
        suiteMap = new HashMap<>();
        xmlTestMap = new HashMap<>();
        if (System.getProperty("ReportDirectoryFullLocation") != null) {
            REPORT_DIRECTORY_LOCATION = System.getProperty("ReportDirectoryFullLocation");
        }

        FULL_FILE_PATH = REPORT_DIRECTORY_LOCATION + "\\" + INIT_SUITE_NAME + "_" + REPORT_FILE_NAME + ".html";
        File file = new File(FULL_FILE_PATH);
        if (!file.exists()) {
            new File(REPORT_DIRECTORY_LOCATION).mkdirs();
            System.setProperty("ReportDirectoryFullLocation", REPORT_DIRECTORY_LOCATION);
        }

        ExtentSparkReporter extentReporter = new ExtentSparkReporter(FULL_FILE_PATH);
        extentReports = new ExtentReports();

        attachCustomConfig(extentReporter);
        extentReports.attachReporter(extentReporter);
        // attaching json reporter for combining reports at the end of the suite run
        JsonFormatter jsonReport = new JsonFormatter(REPORT_DIRECTORY_LOCATION + "\\" + INIT_SUITE_NAME + "_" + REPORT_FILE_NAME + ".json");
        extentReports.attachReporter(jsonReport);
        return extentReports;
    }

    public static ExtentTest recordSuite(TestDetailsDTO dto) {
        if (!suiteMap.containsKey(dto.getSuiteName())) {
            ExtentTest suite = extentReports.createTest("SuiteLogger - " + dto.getSuiteName());
            suite.log(Status.INFO, "This is NOT a test, this has been created for Config Methods like BeforeSuite and AfterSuite Methods Only");
            suiteMap.put(dto.getSuiteName(), suite);
        }

        return suiteMap.get(dto.getSuiteName());
    }

    public static ExtentTest recordClass(TestDetailsDTO dto) {
        String className = dto.getClassName();
        if (!classMap.containsKey(className) && !className.equalsIgnoreCase("TestRunner")) {
            ExtentTest extentTestClass = xmlTestMap.get(dto.getXmlTestName()).createNode(className);
            if (System.getProperty("LithiumSafe", "false").equalsIgnoreCase("true")) {
                BrowserStorageAccess.getInstance().store(StringConstants.TEST_CLASS_NAME, className);
            }
            classMap.put(className, extentTestClass);
        }
        return classMap.get(className);
    }

    @SuppressWarnings("Duplicates")
    public static ExtentTest recordXMLTest(TestDetailsDTO dto) {
        String xmlTestName = dto.getXmlTestName();
        if (!xmlTestMap.containsKey(xmlTestName)) {
            ExtentTest extentXMLTest = extentReports.createTest(xmlTestName);
            xmlTestMap.put(xmlTestName, extentXMLTest);
            if (System.getProperty("LithiumSafe", "false").equalsIgnoreCase("true")) {
                BrowserStorageAccess.getInstance().store(StringConstants.XML_TEST_NAME, xmlTestName);
            }
        }

        return xmlTestMap.get(xmlTestName);
    }

    @SuppressWarnings("Duplicates")
    public static ExtentTest recordTest(TestDetailsDTO dto, String description) {
        String testName = dto.getTestName();
        if (!testMap.containsKey(testName)) {
            ExtentTest extentTest = classMap.get(dto.getClassName()).createNode(dto.getTestName(), description);
            testMap.put(testName, extentTest);
            if (System.getProperty("LithiumSafe", "false").equalsIgnoreCase("true")) {
                BrowserStorageAccess.getInstance().store(StringConstants.TEST_NAME, testName);
            }
        }

        return testMap.get(testName);

    }

    public static void removeClass(String className) {
        extentReports.removeTest(classMap.get(className));
    }

    public static ExtentTest getTest(TestDetailsDTO dto) {
        return testMap.get(dto.getTestName());
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

    public static boolean recordTestResult(ITestResult iTestResult, String status) {
        AutomatedTest automatedAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomatedTest.class)[0];

        Timestamp startDate = new Timestamp(iTestResult.getStartMillis());
        Timestamp endDate = new Timestamp(iTestResult.getEndMillis());
        String failureImageURL = null;
        String failureReason = null;
        boolean clockMove;
        if (System.getProperty("UseClockMoveAnnotation", "false").equalsIgnoreCase("true")) {
            clockMove = iTestResult.getTestClass().getRealClass().isAnnotationPresent(ClockMoveTest.class);
        } else {
            clockMove = Arrays.stream(iTestResult.getTestContext().getIncludedGroups()).anyMatch(s -> s.equalsIgnoreCase("ClockMove"));
        }

        String testCreator = automatedAnnotation.Author();
        String testName = iTestResult.getMethod().getMethodName();
        String buildNumber = System.getProperty("jenkinsBuildNumber");
        String suiteName = iTestResult.getTestContext().getSuite().getName();
        String testRunSource = System.getProperty("startedByUser");
        String tags = flattenTags(iTestResult);

        if (status.equalsIgnoreCase(Status.FAIL.toString())) {
            failureImageURL = REPORT_DIRECTORY_LOCATION + "\\" + iTestResult.getName() + ".png";
            failureReason = iTestResult.getThrowable().getLocalizedMessage();
        }
        TestResultsDTO testResultsDTO = TestResultsDTO.getInstance(clockMove, testCreator, testName, startDate, endDate, failureImageURL, Status.valueOf(status.toUpperCase()), failureReason, buildNumber, suiteName, testRunSource, tags);
        return insertIntoTestResults(testResultsDTO);

    }

    public static boolean insertIntoTestResults(TestResultsDTO testResultsDTO) {
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

    public static boolean insertIntoTestRuntimeCatalog(TestRuntimeDTO runtimeDTO) {
        QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        try {
            TestRuntimeDTO hasExistingRecord = regressionDB.query("select fullClassName, packageName, totalRunTime, projectSource, isClockMove, testType from TestRuntimeCatalog where fullClassName = '" + runtimeDTO.getFullClassName() + "' and packageName = '" + runtimeDTO.getPackageName() + "'",
                    new BeanHandler<>(TestRuntimeDTO.class));
            System.out.println(runtimeDTO.getFullClassName() + " has existing Record so updating with latest info: " + hasExistingRecord);
            return regressionDB.update(hasExistingRecord == null ?
                            TestRuntimeDTO.getJDBCPreparedInsertStatementWithoutParameters() : TestRuntimeDTO.getJDBCPreparedUpdateStatementWithoutParameters(runtimeDTO.getFullClassName(), runtimeDTO.getPackageName()),
                    runtimeDTO.getFullClassName(),
                    runtimeDTO.getPackageName(),
                    runtimeDTO.getTotalRuntime(),
                    runtimeDTO.getProjectSource(),
                    runtimeDTO.getIsClockMove(),
                    runtimeDTO.getTestType()) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertBulkIntoTestRuntimeCatalog(List<TestRuntimeDTO> runtimeDTOs) {
        QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        Object[][] params = new Object[runtimeDTOs.size()][TestResultsDTO.getFieldCount()];
        for (int i = 0; i < runtimeDTOs.size(); i++) {
            params[i] = runtimeDTOs.get(i).getValuesAsObjectArray();
        }

        try {
            return regressionDB.batch(TestRuntimeDTO.getJDBCPreparedInsertStatementWithoutParameters(), params).length > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean bulkInsertIntoTestResults(List<TestResultsDTO> resultsDTOS) {
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

    public static void recordSuiteResults(ISuite iSuite) {
        if (!iSuite.getName().equalsIgnoreCase("Default Suite") && ReportManager.FULL_FILE_PATH.startsWith("\\\\")) {
//            System.out.println("!!!!!! Recording Suite Results to the database. !!!!!!");
            String UUID = System.getProperty("UUID");
            String suiteName = iSuite.getName();
            TestCountDTO testCountDTO = TestCountDTO.getTestCountDataFor(UUID, suiteName);
            String jenkinsBuildNumber = System.getProperty("jenkinsBuildNumber");
            String applicationName = System.getProperty("ApplicationName");
            String reportPath = getReportPath();
            Optional<SuiteResultsDTO> existingSuiteDTO = SuiteResultsDTO.getExisting(UUID, applicationName, suiteName);
            if(existingSuiteDTO.isPresent()){
                SuiteResultsDTO updatedDTO = SuiteResultsDTO.updateExisting(testCountDTO, existingSuiteDTO.get());
                updateSuiteResults(updatedDTO);
            } else {
                SuiteResultsDTO suiteDTO = SuiteResultsDTO.createInstance(applicationName, testCountDTO.getPassCount(), testCountDTO.getFailCount(), testCountDTO.getSkipCount(), testCountDTO.getWarningCount(), jenkinsBuildNumber, suiteName, reportPath);
                insertIntoSuiteResults(suiteDTO);
            }
        } else {
            System.out.println("Could not Record Suite: " + iSuite.getName() + " with report path: " + ReportManager.FULL_FILE_PATH);
        }

    }

    public static boolean insertIntoSuiteResults(SuiteResultsDTO suiteResultsDTO) {
        QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        try {
            regressionDB.update(SuiteResultsDTO.getJDBCPreparedInsertStatementWithoutParameters(),
                    suiteResultsDTO.getApplicationName(),
                    suiteResultsDTO.getPassedTests(),
                    suiteResultsDTO.getFailedTests(),
                    suiteResultsDTO.getSkippedTests(),
                    0,
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

    public static boolean updateSuiteResults(SuiteResultsDTO suiteResultsDTO){
        QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        try {
            regressionDB.update(SuiteResultsDTO.getJDBCPreparedUpdateStatementWithoutParameters(),
                    suiteResultsDTO.getPassedTests(),
                    suiteResultsDTO.getFailedTests(),
                    suiteResultsDTO.getSkippedTests(),
                    suiteResultsDTO.getWarningTests(),
                    suiteResultsDTO.getUUID(),
                    suiteResultsDTO.getSuiteName(),
                    suiteResultsDTO.getApplicationName());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Could not update the suite results to the database");
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

    public static String getReportPath() {
        return "http://qa.idfbins.com/regression_logs/" + GLOBAL_SUITE_NAME;
    }

    public static void generateCombinedReports(String targetLocation, String... sourceFilesDirectoryPath) throws IOException {
        System.out.println("Combining Reports present at " + Arrays.toString(sourceFilesDirectoryPath));
        System.out.println("Final Report will be generated at: " + targetLocation);
        ExtentReports extent = new ExtentReports();
        // report to klov reporting if flag is setup
        if (System.getProperty("EnableKLOVReporting", "false").equalsIgnoreCase("true")) {
            System.out.println("Attempting to push tests to klov server");
            ExtentKlovReporter klov = new ExtentKlovReporter();
            klov
                    .initKlovServerConnection(System.getProperty("KLOVHost", "http://127.0.0.1:80"))
                    .initMongoDbConnection(System.getProperty("MongoHost", "127.0.0.1"),  27017);
            klov.setProjectName(System.getProperty("ProjectName"));
            klov.setReportName(System.getProperty("ApplicationName")+"_"+System.getProperty("jenkinsBuildNumber"));
            extent.attachReporter(klov);
        }

        // Scanning for json files to parse for reports
        ArrayList<File> jsonFiles = new ArrayList<>();
        for (String directoryPath : sourceFilesDirectoryPath) {
            File directory = new File(directoryPath);
            jsonFiles.addAll(Arrays.asList(Objects.requireNonNull(directory.listFiles(new JSONFileNameFilter()))));
        }

        // Preparing to read existing Reports
        for (File jsonFile : jsonFiles) {
            System.out.println("Parsing Report: " + jsonFile.getAbsolutePath());
            extent.createDomainFromJsonArchive(jsonFile);
        }
        String finalReportPath = targetLocation + "\\" + "combinedReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(finalReportPath);
        attachCustomConfig(sparkReporter);
        extent.attachReporter(sparkReporter);
        System.out.println("Generating Combined Report at: " + finalReportPath);
        extent.flush();
    }

    private static void attachCustomConfig(ExtentSparkReporter extentReporter) {
        // Configurations
        extentReporter.config().setTimelineEnabled(true);
        extentReporter.config().setTheme(Theme.DARK);
//        extentReporter.config().setCSS(compileCustomCSS());
        extentReporter.config().setJs("document.getElementsByClassName(\"brand-logo blue darken-3\")[0].innerText = \"QA Report\"");
        String applicationName = System.getProperty("ApplicationName") == null ? "Custom" : System.getProperty("ApplicationName");
        extentReporter.config().setDocumentTitle(applicationName + " Regression Health Report");
        extentReporter.config().setReportName(applicationName + " Regression Report");
    }
}
