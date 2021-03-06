package framework;

import annotations.AutomatedTest;
import annotations.AutomationHistory;
import annotations.ClockMoveTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.NamedAttribute;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Joiner;
import framework.constants.StringConstants;
import framework.database.ConnectionManager;
import framework.database.models.*;
import framework.enums.ApplicationNames;
import framework.enums.FrameworkSystemTags;
import framework.reports.models.LoggerDTO;
import framework.reports.models.TestDetailsDTO;
import framework.utils.fileFilters.JSONFileNameFilter;
import framework.webdriver.ThreadFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
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
import java.util.stream.Collectors;

public class ReportManager {

    // Network Storage Location
    private static final String GLOBAL_SUITE_NAME = System.getProperty("globalSuiteName") == null ? "" : System.getProperty("globalSuiteName");
    private static final String REPORT_FILE_NAME = System.getProperty("reportFileName") == null ? "LocalTestRun" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) : System.getProperty("reportFileName");
    public static String REPORT_DIRECTORY_LOCATION = System.getProperty("jenkinsBuildNumber") == null ? "C:/tmp/" + REPORT_FILE_NAME : (!GLOBAL_SUITE_NAME.isEmpty() ? StringConstants.DEFAULT_REPORT_LOCATION_PATH : "\\\\qa\\regression_logs\\" + REPORT_FILE_NAME);
    private static String FULL_FILE_PATH = REPORT_DIRECTORY_LOCATION + File.separator + REPORT_FILE_NAME + ".html";
    private static String INIT_SUITE_NAME;

    // Reporting Indices
    private static HashMap<String, LoggerDTO> xmlTestMap;
    private static HashMap<String, LoggerDTO> classMap;
    private static HashMap<String, LoggerDTO> testMap;
    private static HashMap<String, LoggerDTO> suiteMap;

    private static ExtentReports extentReports;

    private ReportManager() {

    }

    public static boolean isInitiated() {
        return extentReports != null;
    }

    public static ExtentReports initiate(final String suiteName) {
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

        FULL_FILE_PATH = REPORT_DIRECTORY_LOCATION + File.separator + INIT_SUITE_NAME + "_" + REPORT_FILE_NAME + ".html";
        final File file = new File(FULL_FILE_PATH);
        if (!file.exists()) {
            new File(REPORT_DIRECTORY_LOCATION).mkdirs();
            System.setProperty("ReportDirectoryFullLocation", REPORT_DIRECTORY_LOCATION);
        }

        final ExtentSparkReporter extentReporter = new ExtentSparkReporter(FULL_FILE_PATH);
        extentReports = new ExtentReports();

        attachCustomConfig(extentReporter);
        extentReports.attachReporter(extentReporter);
        // attaching json reporter for combining reports at the end of the suite run
        final JsonFormatter jsonReport = new JsonFormatter(REPORT_DIRECTORY_LOCATION + File.separator + INIT_SUITE_NAME + "_" + REPORT_FILE_NAME + ".json");
        extentReports.attachReporter(jsonReport);
        return extentReports;
    }

    public static LoggerDTO recordSuite(final TestDetailsDTO dto) {
        if (!suiteMap.containsKey(dto.getSuiteName())) {
            final ExtentTest suite = extentReports.createTest("SuiteLogger - " + dto.getSuiteName());
            suite.log(Status.INFO, "This is NOT a test, this has been created for Config Methods like BeforeSuite and AfterSuite Methods Only");
            suiteMap.put(dto.getSuiteName(), new LoggerDTO(suite));
        }

        return suiteMap.get(dto.getSuiteName());
    }

    public static LoggerDTO recordClass(final TestDetailsDTO dto) {
        final String className = dto.getClassName();
        if (!classMap.containsKey(className) && !className.equalsIgnoreCase("TestRunner")) {
            final ExtentTest extentTestClass = xmlTestMap.get(dto.getXmlTestName()).getExtentTest().createNode(className);
            ThreadFactory.getInstance().getStorage().put(StringConstants.TEST_CLASS_NAME, className);
            ThreadFactory.getInstance().getStorage().put(StringConstants.PACKAGE_NAME, dto.getPackageName());
            ThreadFactory.getInstance().getStorage().put(StringConstants.SUITE_NAME, dto.getSuiteName());
            classMap.put(className, new LoggerDTO(extentTestClass));
        }
        return classMap.get(className);
    }

    @SuppressWarnings("Duplicates")
    public static LoggerDTO recordXMLTest(final TestDetailsDTO dto) {
        final String xmlTestName = dto.getXmlTestName();
        if (!xmlTestMap.containsKey(xmlTestName)) {
            final ExtentTest extentXMLTest = extentReports.createTest(xmlTestName);
            xmlTestMap.put(xmlTestName, new LoggerDTO(extentXMLTest));
            ThreadFactory.getInstance().getStorage().put(StringConstants.XML_TEST_NAME, xmlTestName);
        }

        return xmlTestMap.get(xmlTestName);
    }

    @SuppressWarnings("Duplicates")
    public static LoggerDTO recordTest(final TestDetailsDTO dto, final String description) {
        final String testName = dto.getTestName();
        if (!testMap.containsKey(testName)) {
            final ExtentTest extentTest = xmlTestMap.get(dto.getXmlTestName()).getExtentTest().createNode(dto.getTestName(), description);
            testMap.put(testName, new LoggerDTO(extentTest));
            ThreadFactory.getInstance().getStorage().put(StringConstants.TEST_NAME, testName);
        }

        return testMap.get(testName);

    }

    public static void removeClass(final String className) {
        extentReports.removeTest(classMap.get(className).getExtentTest());
    }

    public static LoggerDTO getTest(final TestDetailsDTO dto) {
        return testMap.get(dto.getTestName());
    }

    public static LoggerDTO getClass(final String className) {
        return classMap.get(className);
    }

    public static LoggerDTO getXMLTest(final String xmlTestName) {
        return xmlTestMap.get(xmlTestName);
    }

    public static LoggerDTO getSuite(final String suiteName) {
        return suiteMap.get(suiteName);
    }

    public static boolean recordTestResult(final ITestResult iTestResult, final String status) {
        final AutomatedTest automatedAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomatedTest.class)[0];

        final Timestamp startDate = new Timestamp(iTestResult.getStartMillis());
        final Timestamp endDate = new Timestamp(iTestResult.getEndMillis());
        String failureImageURL = null;
        String failureReason = null;
        final boolean clockMove;
        if (System.getProperty("UseClockMoveAnnotation", "false").equalsIgnoreCase("true")) {
            clockMove = iTestResult.getTestClass().getRealClass().isAnnotationPresent(ClockMoveTest.class);
        } else {
            clockMove = Arrays.stream(iTestResult.getTestContext().getIncludedGroups()).anyMatch(s -> s.equalsIgnoreCase("ClockMove"));
        }

        final String testCreator = automatedAnnotation.Author();
        final String testName = iTestResult.getMethod().getMethodName();
        final String className = iTestResult.getMethod().getTestClass().getName();
        final String packageName = iTestResult.getMethod().getTestClass().getRealClass().getPackage().getName();
        final String buildNumber = System.getProperty("jenkinsBuildNumber");
        final String suiteName = iTestResult.getTestContext().getSuite().getName();
        final String testRunSource = System.getProperty("startedByUser");
        final String tags = flattenTags(iTestResult);

        if (status.equalsIgnoreCase(Status.FAIL.toString())) {
            failureImageURL = REPORT_DIRECTORY_LOCATION + "\\" + iTestResult.getName() + ".png";
            failureReason = iTestResult.getThrowable().getLocalizedMessage();
        }
        final TestResultsDTO testResultsDTO = TestResultsDTO.getInstance(clockMove, testCreator, testName, className, packageName, startDate, endDate, failureImageURL, Status.valueOf(status.toUpperCase()), failureReason, buildNumber, suiteName, testRunSource, tags);
        return insertIntoTestResults(testResultsDTO);

    }


    public static boolean insertIntoTestResults(final TestResultsDTO testResultsDTO) {
        final QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        try {
            return regressionDB
                    .update(TestResultsDTO.getJDBCPreparedInsertStatementWithoutParameters(),
                            testResultsDTO.isClockMove(),
                            testResultsDTO.getTestCreator(),
                            testResultsDTO.getTestName(),
                            testResultsDTO.getClassName(),
                            testResultsDTO.getPackageName(),
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
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertIntoTestRuntimeCatalog(final TestRuntimeDTO runtimeDTO) {
        final QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        try {
            final TestRuntimeDTO hasExistingRecord = regressionDB.query("select fullClassName, packageName, totalRunTime, projectSource, isClockMove, testType, isLive from TestRuntimeCatalog where fullClassName = '" + runtimeDTO.getFullClassName() + "' and packageName = '" + runtimeDTO.getPackageName() + "'",
                    new BeanHandler<>(TestRuntimeDTO.class));
            System.out.println(runtimeDTO.getFullClassName() + " has existing Record so updating with latest info: " + (hasExistingRecord != null) + ": " + hasExistingRecord);
            return regressionDB.update(hasExistingRecord == null ?
                            TestRuntimeDTO.getJDBCPreparedInsertStatementWithoutParameters() : TestRuntimeDTO.getJDBCPreparedUpdateStatementWithoutParameters(runtimeDTO.getFullClassName(), runtimeDTO.getPackageName()),
                    runtimeDTO.getFullClassName(),
                    runtimeDTO.getPackageName(),
                    (hasExistingRecord != null && hasExistingRecord.isLive()) ? hasExistingRecord.getTotalRuntime() : runtimeDTO.getTotalRuntime(),
                    runtimeDTO.getProjectSource(),
                    runtimeDTO.getIsClockMove(),
                    runtimeDTO.getTestType(),
                    runtimeDTO.isLive()) > 0;
        } catch (final Exception e) {
            e.printStackTrace();
            System.out.println("Failed to insert: " + e.getLocalizedMessage());
            return false;
        }
    }

    public static boolean insertBulkIntoTestRuntimeCatalog(final List<TestRuntimeDTO> runtimeDTOs) {
        final QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        final Object[][] params = new Object[runtimeDTOs.size()][TestResultsDTO.getFieldCount()];
        for (int i = 0; i < runtimeDTOs.size(); i++) {
            params[i] = runtimeDTOs.get(i).getValuesAsObjectArray();
        }

        try {
            return regressionDB.batch(TestRuntimeDTO.getJDBCPreparedInsertStatementWithoutParameters(), params).length > 0;
        } catch (final SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean bulkInsertIntoTestResults(final List<TestResultsDTO> resultsDTOS) {
        final QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        final Object[][] params = new Object[resultsDTOS.size()][TestResultsDTO.getFieldCount()];
        for (int i = 0; i < resultsDTOS.size(); i++) {
            params[i] = resultsDTOS.get(i).getValuesAsObjectArray();
        }
        try {
            return regressionDB.batch(TestResultsDTO.getJDBCPreparedInsertStatementWithoutParameters(), params).length > 0;
        } catch (final SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void recordSuiteResults(final ISuite iSuite) {
        try {
            if (!iSuite.getName().equalsIgnoreCase("Default Suite") && ReportManager.FULL_FILE_PATH.startsWith("\\\\")) {
//            System.out.println("!!!!!! Recording Suite Results to the database. !!!!!!");
                final String UUID = System.getProperty("UUID");
                final String suiteName = iSuite.getName();
                final TestCountDTO testCountDTO = TestCountDTO.getTestCountDataFor(UUID, suiteName);
                final String jenkinsBuildNumber = System.getProperty("jenkinsBuildNumber");
                final String applicationName = System.getProperty("ApplicationName");
                final String reportPath = getReportPath();
                final Optional<SuiteResultsDTO> existingSuiteDTO = SuiteResultsDTO.getExisting(UUID, applicationName, suiteName);
                final String suiteType = System.getProperty("RunType", "Custom");
                if (existingSuiteDTO.isPresent()) {
                    final SuiteResultsDTO updatedDTO = SuiteResultsDTO.updateExisting(testCountDTO, existingSuiteDTO.get());
                    updateSuiteResults(updatedDTO);
                } else {
                    final SuiteResultsDTO suiteDTO = SuiteResultsDTO.createInstance(applicationName, testCountDTO.getPassCount(), testCountDTO.getFailCount(), testCountDTO.getSkipCount(), testCountDTO.getWarningCount(), 0, jenkinsBuildNumber, suiteName, reportPath, suiteType);
                    insertIntoSuiteResults(suiteDTO);
                }
            } else {
                System.out.println("Could not Record Suite: " + iSuite.getName() + " with report path: " + ReportManager.FULL_FILE_PATH);
            }
        } catch (final Exception e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }


    public static void recordPartitionGWApplicationSuiteResults(final String buildUUID) {
        final TestCountDTO pcTestCountDTO = TestCountDTO.getTestCountDataFor(buildUUID, ApplicationNames.PC);
        final TestCountDTO bcTestCountDTO = TestCountDTO.getTestCountDataFor(buildUUID, ApplicationNames.BC);
        final TestCountDTO ccTestCountDTO = TestCountDTO.getTestCountDataFor(buildUUID, ApplicationNames.CC);
        final TestCountDTO abTestCountDTO = TestCountDTO.getTestCountDataFor(buildUUID, ApplicationNames.AB);
        final TestCountDTO portalsTestCountDTO = TestCountDTO.getTestCountDataFor(buildUUID, ApplicationNames.ACCOUNT_MANAGEMENT_PORTAL);

        final String jenkinsBuildNumber = System.getProperty("jenkinsBuildNumber");
        final String reportPath = getReportPath();
        //PC
        updateExistingOrInsertSuiteDTO(buildUUID, pcTestCountDTO, ApplicationNames.PC, "PC_UITests", jenkinsBuildNumber, reportPath);

        //BC
        updateExistingOrInsertSuiteDTO(buildUUID, bcTestCountDTO, ApplicationNames.BC, "BC_UITests", jenkinsBuildNumber, reportPath);

        //CC
        updateExistingOrInsertSuiteDTO(buildUUID, ccTestCountDTO, ApplicationNames.CC, "CC_UITests", jenkinsBuildNumber, reportPath);

        //AB
        updateExistingOrInsertSuiteDTO(buildUUID, abTestCountDTO, ApplicationNames.AB, "AB_UITests", jenkinsBuildNumber, reportPath);

        //Portals
        updateExistingOrInsertSuiteDTO(buildUUID, portalsTestCountDTO, ApplicationNames.ACCOUNT_MANAGEMENT_PORTAL, "AMP_UITests", jenkinsBuildNumber, reportPath);
    }

    private static void updateExistingOrInsertSuiteDTO(final String uuid, final TestCountDTO dto, final ApplicationNames applicationName, final String suiteName, final String jenkinsBuildNumber, final String reportPath) {
        final Optional<SuiteResultsDTO> amp_uiTests = SuiteResultsDTO.getExisting(uuid, applicationName.getFullName(), suiteName);
        if (!amp_uiTests.isPresent()) {
            final String suiteType = System.getProperty("RunType", "Custom");
            insertIntoSuiteResults(buildSuiteDTO(dto, applicationName.getFullName(), suiteName, jenkinsBuildNumber, reportPath, suiteType));
        } else {
            updateSuiteResults(SuiteResultsDTO.updateExisting(dto, amp_uiTests.get()));
        }
    }

    private static SuiteResultsDTO buildSuiteDTO(final TestCountDTO dto, final String applicationName, final String suiteName, final String jenkinsBuildNumber, final String reportPath, final String suiteType) {
        return SuiteResultsDTO.createInstance(applicationName, dto.getPassCount(), dto.getFailCount(), dto.getSkipCount(), dto.getWarningCount(), 0, jenkinsBuildNumber, suiteName, reportPath, suiteType);
    }

    public static boolean insertIntoSuiteResults(final SuiteResultsDTO suiteResultsDTO) {
        final QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
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
                    suiteResultsDTO.getUUID(),
                    suiteResultsDTO.getSuiteType());
            return true;
        } catch (final SQLException e) {
            e.printStackTrace();
            Assert.fail("Could not save the suite results to the database");
            return false;
        }
    }

    public static boolean updateSuiteResults(final SuiteResultsDTO suiteResultsDTO) {
        final QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        try {
            regressionDB.update(SuiteResultsDTO.getJDBCPreparedUpdateStatementWithoutParameters(),
                    suiteResultsDTO.getPassedTests(),
                    suiteResultsDTO.getFailedTests(),
                    suiteResultsDTO.getSkippedTests(),
                    suiteResultsDTO.getWarningTests(),
                    suiteResultsDTO.getFatalTests(),
                    suiteResultsDTO.getUUID(),
                    suiteResultsDTO.getSuiteName(),
                    suiteResultsDTO.getApplicationName());
            return true;
        } catch (final SQLException e) {
            e.printStackTrace();
            Assert.fail("Could not update the suite results to the database");
            return false;
        }
    }

    private static String flattenTags(final ITestResult iTestResult) {
        final Method testMethod = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        final AutomationHistory[] historyAnnotations = testMethod.getAnnotationsByType(AutomationHistory.class);
        final AutomatedTest automatedAnnotation = testMethod.getAnnotationsByType(AutomatedTest.class)[0];

        final StringBuilder tags = new StringBuilder();
        tags.append("|").append(automatedAnnotation.Author()).append("|");
        tags.append(automatedAnnotation.FeatureNumber()).append("|");
        tags.append("SD_").append(automatedAnnotation.StoryOrDefectNumber()).append("|");
        tags.append(Joiner.on("|").join(Arrays.stream(automatedAnnotation.Themes()).map(s -> s = "TH_" + s).collect(Collectors.toList()))).append("|");

        if (historyAnnotations.length > 0) {
            final AutomationHistory historyAnnotation = historyAnnotations[0];
            tags.append(Joiner.on("|").join(historyAnnotation.StoryOrDefectNumbers())).append("|");
        }
        final List<String> testTags = getTest(Listener.buildTestDetailsDTO(iTestResult)).getExtentTest().getModel().getCategorySet().stream().map(NamedAttribute::getName).collect(Collectors.toList());
        tags.append(Joiner.on("|").join(testTags)).append("|");
        //noinspection RegExpEmptyAlternationBranch

        return tags.toString().replaceAll("\\|\\|", "|");
    }

    public static void main(final String[] args) {
        System.out.println("|Ken Tennant||US19052||DEV10|ClaimCenter|US19052|CC_ClaimSearch||CC_UITests|PotentialSystemFailure|".replaceAll("\\|\\|", "|"));
    }

    public static String getReportPath() {
        return ReportManager.FULL_FILE_PATH.replace("\\\\qa\\regression_logs\\", "http://qa.idfbins.com/regression_logs/").replaceAll("\\\\", "/");
    }

    public static void generateCombinedReports(final boolean forMasterFile, final String targetLocation, final String... sourceFilesDirectoryPath) throws IOException {
        System.out.println("Combining Reports present at " + Arrays.toString(sourceFilesDirectoryPath));
        System.out.println("Final Report will be generated at: " + targetLocation);
        final ExtentReports extent = new ExtentReports();
        // report to klov reporting if flag is setup
//        if (System.getProperty("EnableKLOVReporting", "false").equalsIgnoreCase("true")) {
//            System.out.println("Attempting to push tests to klov server");
//            ExtentKlovReporter klov = new ExtentKlovReporter();
//            klov
//                    .initKlovServerConnection(System.getProperty("KLOVHost", "http://127.0.0.1:80"))
//                    .initMongoDbConnection(System.getProperty("MongoHost", "127.0.0.1"), 27017);
//            klov.setProjectName(System.getProperty("ProjectName"));
//            klov.setReportName(System.getProperty("ApplicationName") + "_" + System.getProperty("jenkinsBuildNumber"));
//            extent.attachReporter(klov);
//        }

        // Scanning for json files to parse for reports
        final ArrayList<File> jsonFiles = new ArrayList<>();
        if (forMasterFile) {
            // deep scanning target location for existing combined reports
            for (final String directoryPath : sourceFilesDirectoryPath) {
                final File directory = new File(directoryPath);
                jsonFiles.addAll(Arrays.asList(Objects.requireNonNull(directory.listFiles(new JSONFileNameFilter()))));
                if (directory.isDirectory()) {
                    for (final File subdirectory : Objects.requireNonNull(directory.listFiles())) {
                        if (subdirectory.isDirectory()) {
                            jsonFiles.addAll(Arrays.asList(Objects.requireNonNull(subdirectory.listFiles(new JSONFileNameFilter()))));
                        }
                    }
                }
            }
        } else {
            for (final String directoryPath : sourceFilesDirectoryPath) {
                final File directory = new File(directoryPath);
                jsonFiles.addAll(Arrays.asList(Objects.requireNonNull(directory.listFiles(new JSONFileNameFilter()))));
            }
        }

        // Preparing to read existing Reports
        for (final File jsonFile : jsonFiles) {
            System.out.println("Parsing Report: " + jsonFile.getAbsolutePath());
            extent.createDomainFromJsonArchive(jsonFile);
        }
        String finalReportPath = targetLocation + "\\" + "combinedReport.html";
        final ExtentSparkReporter sparkReporter = new ExtentSparkReporter(finalReportPath);
        final JsonFormatter jsonReporter = new JsonFormatter(finalReportPath.replace(".html", ".json"));
        attachCustomConfig(sparkReporter);
        extent.attachReporter(sparkReporter);
        extent.attachReporter(jsonReporter);
        System.out.println("Generating Combined Report at: " + finalReportPath);
        extent.flush();

        if (forMasterFile) {
            // updating suite and test results table with the combined path
            int passedTests = 0;
            int failedTests = 0;
            int skippedTests = 0;
            int warningTests = 0;
            int fatalTests = 0;
            System.setProperty("SuiteStartTime", String.valueOf(extent.getReport().getStartTime().getTime()));
            System.setProperty("SuiteEndTime", String.valueOf(extent.getReport().getEndTime().getTime()));
            for (final Test ancestorTest : extent.getReport().getTestList()) {
                List<Test> testsToParse = new ArrayList<>();
                if (ancestorTest.hasChildren()) {
                    testsToParse = ancestorTest.getChildren();
                } else {
                    testsToParse.add(ancestorTest);
                }

                for (final Test test : testsToParse) {
                    switch (test.getStatus()) {
                        case PASS:
                            passedTests++;
                            break;
                        case FAIL:
                            fatalTests += getFatalTestCount(test);
                            failedTests++;
                            break;
                        case SKIP:
                            skippedTests++;
                            break;
                        case WARNING:
                            warningTests++;
                            break;
                    }
                }

            }

            System.out.println("Inserting master record in the suite results table");
            finalReportPath = finalReportPath.replace("\\\\qa\\regression_logs\\", "http://qa.idfbins.com/regression_logs/").replaceAll("\\\\", "/");
            final SuiteResultsDTO suiteResultsDTO = SuiteResultsDTO.createInstance(System.getProperty("ProjectName", "NightlyRegression"), passedTests, failedTests, skippedTests, warningTests, fatalTests, System.getProperty("jenkinsBuildNumber"), System.getProperty("masterReportName"), finalReportPath, System.getProperty("RunType", "Custom"));
            ReportManager.insertIntoSuiteResults(suiteResultsDTO);
        }
    }

    private static long getFatalTestCount(final Test test) {
        int fatalTestCounter = 0;
        final long count = test.getCategorySet().stream()
                .filter(category -> category.getName().equalsIgnoreCase(FrameworkSystemTags.ERROR_ON_SCREEN.getValue())).count();
//                        || category.getName().equalsIgnoreCase(FrameworkSystemTags.BLOCKED_MESSAGE_QUEUE.getValue())
//                        || category.getName().equalsIgnoreCase(FrameworkSystemTags.POTENTIAL_SYSTEM_FAILURE.getValue())).count();
        if (count > 0) {
            fatalTestCounter++;
        }

        return fatalTestCounter;
    }

    private static void attachCustomConfig(final ExtentSparkReporter extentReporter) {
        // Configurations
        extentReporter.config().setTimelineEnabled(true);
        extentReporter.config().setTheme(Theme.DARK);
//        extentReporter.config().setCSS(compileCustomCSS());
        extentReporter.config().setJs("document.getElementsByClassName(\"brand-logo blue darken-3\")[0].innerText = \"QA Report\"");
        final String applicationName = System.getProperty("ApplicationName") == null ? "Custom" : System.getProperty("ApplicationName");
        extentReporter.config().setDocumentTitle(applicationName + " Regression Health Report");
        extentReporter.config().setReportName(applicationName + " Regression Report");
    }
}
