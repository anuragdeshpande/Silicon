package framework;

import annotations.*;
import ch.qos.logback.classic.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.ExceptionInfo;
import framework.constants.ReactionTime;
import framework.constants.StringConstants;
import framework.customExceptions.BlockedMessageQueueException;
import framework.customExceptions.KnownDefectException;
import framework.customExceptions.PotentialSystemIssueException;
import framework.database.models.TestRuntimeDTO;
import framework.enums.FrameworkSystemTags;
import framework.guidewire.ErrorMessageOnScreenException;
import framework.guidewire.GuidewireInteract;
import framework.logger.RegressionLogger;
import framework.logger.eventMessaging.GWEvents;
import framework.logger.regressionTestLogging.RegressionLogTemplates;
import framework.reports.models.TestDetailsDTO;
import framework.reports.models.junit.Failure;
import framework.reports.models.junit.Testcase;
import framework.reports.models.junit.Testsuite;
import framework.utils.PropertiesFileLoader;
import framework.webdriver.BrowserFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.annotations.Test;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Listener implements ISuiteListener, ITestListener {

    private final Logger regressionLogger = (Logger) LoggerFactory.getLogger("EventLogger");
    public boolean writeToDatabase;
    public boolean skipSuiteWriteToDB;
    private ExtentReports extentReports;

    public static TestDetailsDTO buildTestDetailsDTO(final ITestResult iTestResult) {
        final TestDetailsDTO dto = new TestDetailsDTO();
        dto.setTestName(iTestResult.getMethod().getConstructorOrMethod().getMethod().getName());
        dto.setClassName(iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName());
        dto.setPackageName(iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getPackage().getName());
        dto.setXmlTestName(iTestResult.getTestContext().getCurrentXmlTest().getName());
        return dto;
    }

    // Fires at the beginning of each suite
    @Override
    public void onStart(final ISuite iSuite) {
        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.setProperty("SuiteStartTime", String.valueOf(System.currentTimeMillis()));
        final String suiteName = iSuite.getName();
        this.extentReports = ReportManager.initiate(suiteName + "_" + timeStamp);
        writeToDatabase = !suiteName.equalsIgnoreCase("Default Suite") && System.getProperty("MarkAsTestBuild", "true").equalsIgnoreCase("false");
        this.skipSuiteWriteToDB = Boolean.parseBoolean(System.getProperty("SkipSuiteDBWrite", "false"));
    }

    // Fires at the beginning of each test class
    @Override
    public void onStart(final ITestContext iTestContext) {
        // do nothing
    }

    // Fires at the beginning of each test
    @Override
    public void onTestStart(final ITestResult iTestResult) {
//        TestRuntimeDTO testRuntimeDTO = buildTestRuntimeDTOForTestStart(iTestResult.getTestContext());
//        TestRuntimeDTO.setLiveStatusInDB(testRuntimeDTO.getPackageName(), testRuntimeDTO.getFullClassName(), true);
        final Method testMethod = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        final Test[] testAnnotations = testMethod.getDeclaredAnnotationsByType(Test.class);
        final TestDetailsDTO testDetailsDTO = buildTestDetailsDTO(iTestResult);
        final ExtentTest testLogger = ReportManager.recordTest(testDetailsDTO, testAnnotations.length > 0 ? testAnnotations[0].description() : null).getExtentTest();

        final CPPTest[] declaredAnnotationsByType = iTestResult.getTestClass().getRealClass().getDeclaredAnnotationsByType(CPPTest.class);
        if (declaredAnnotationsByType.length > 0) {
            testLogger.assignCategory("CPPTest");
        }

        final AutomatedTest[] annotations = testMethod.getDeclaredAnnotationsByType(AutomatedTest.class);
        if (annotations.length == 0) {
            testLogger.fail("Fatal Error: @AutomatedTest annotation not found.");
            iTestResult.setStatus(ITestResult.SKIP);
            throw new SkipException("Fatal Error: @AutomatedTest annotation not found on test: " + testDetailsDTO.getTestName() + " on class: " + testDetailsDTO.getClassName());
        } else {
            final AutomatedTest automatedTest = annotations[0];
            testLogger.assignAuthor(automatedTest.Author());
            if (!automatedTest.FeatureNumber().isEmpty()) {
                testLogger.assignCategory(automatedTest.FeatureNumber());
            }
            testLogger.assignCategory(automatedTest.StoryOrDefectNumber(), iTestResult.getTestContext().getSuite().getName());
            testLogger.assignCategory(automatedTest.Themes());
        }
    }

    // fires when a test is successful
    @Override
    public void onTestSuccess(final ITestResult iTestResult) {
        final TestDetailsDTO dto = buildTestDetailsDTO(iTestResult);
//        TestRuntimeDTO testRuntimeDTO = buildTestRuntimeDTOForTestStart(iTestResult.getTestContext());
//        TestRuntimeDTO.setLiveStatusInDB(testRuntimeDTO.getPackageName(), testRuntimeDTO.getFullClassName(), false);
        final ExtentTest test = ReportManager.getTest(dto).getExtentTest();
        test.getModel().setStartTime(new Date(iTestResult.getStartMillis()));
        test.getModel().setEndTime(new Date(iTestResult.getEndMillis()));
        test.pass(iTestResult.getName() + ": Passed");
        regressionLogger.info(RegressionLogTemplates.getLogTemplateForTestEndPass(iTestResult));
        if (writeToDatabase) {
            ReportManager.recordTestResult(iTestResult, Status.PASS.toString());
        } else {
            System.out.println("Test " + dto.getTestName() + "Passed but, Build is marked as TestBuild, skipping recording results to db");
        }

    }

    // fires when a test fails
    @Override
    public void onTestFailure(final ITestResult iTestResult) {
        final TestDetailsDTO dto = buildTestDetailsDTO(iTestResult);
        final ExtentTest testNode = ReportManager.getTest(dto).getExtentTest();
        final AtomicBoolean writeErrorMessageToLog = new AtomicBoolean(true);
        testNode.getModel().setStartTime(new Date(iTestResult.getStartMillis()));
        testNode.getModel().setEndTime(new Date(iTestResult.getEndMillis()));
        try {
            testNode.addScreenCaptureFromPath(this.captureScreenshot(iTestResult));
        } catch (final Exception e) {
            testNode.fail(e);
        }

        testNode.log(Status.FAIL, iTestResult.getName() + ": Failed");
        testNode.fail(MarkupHelper.createCodeBlock(ExceptionUtils.getStackTrace(iTestResult.getThrowable())));

        if (GuidewireInteract.hasErrorMessageOnScreen(ReactionTime.MOMENTARY)) {
            testNode.log(Status.FAIL, iTestResult.getName() + " Failed with critical system failure");
            GuidewireInteract.getErrorMessageFromScreen(ReactionTime.MOMENTARY).ifPresent(errorMessagesFromScreen -> {
                for (final String errorMessageFromScreen : errorMessagesFromScreen) {
                    testNode.fail(errorMessageFromScreen);
                }
                writeErrorMessageToLog.set(false);
                iTestResult.setThrowable(new ErrorMessageOnScreenException(Arrays.toString(errorMessagesFromScreen.toArray())));
            });
        }

        if (iTestResult.getThrowable() instanceof KnownDefectException) {
            testNode.assignCategory(FrameworkSystemTags.ACTIVE_DEFECT.getValue());
            RegressionLogger.getTestClassLogger().logInstantEvent(GWEvents.FATAL_ISSUE, "Failing with " + FrameworkSystemTags.ACTIVE_DEFECT.getValue());
        }

        if (iTestResult.getThrowable() instanceof BlockedMessageQueueException) {
            testNode.assignCategory(FrameworkSystemTags.BLOCKED_MESSAGE_QUEUE.getValue());
            RegressionLogger.getTestClassLogger().logInstantEvent(GWEvents.FATAL_ISSUE, "Failing with " + FrameworkSystemTags.BLOCKED_MESSAGE_QUEUE.getValue());
            testNode.assignCategory(FrameworkSystemTags.POTENTIAL_SYSTEM_FAILURE.getValue());
            RegressionLogger.getTestClassLogger().logInstantEvent(GWEvents.FATAL_ISSUE, "Failing with " + FrameworkSystemTags.POTENTIAL_SYSTEM_FAILURE.getValue());
        }
        if (iTestResult.getThrowable() instanceof PotentialSystemIssueException) {
            testNode.assignCategory(FrameworkSystemTags.POTENTIAL_SYSTEM_FAILURE.getValue());
            RegressionLogger.getTestClassLogger().logInstantEvent(GWEvents.FATAL_ISSUE, "Failing with " + FrameworkSystemTags.POTENTIAL_SYSTEM_FAILURE.getValue());
        }

        if (iTestResult.getThrowable() instanceof ErrorMessageOnScreenException) {
            final Properties properties = PropertiesFileLoader.load("config.properties");
            final String stringsToIgnore = properties.getProperty("ErrorMessagesOnScreenToIgnore");
            if (stringsToIgnore != null) {
                final String[] errorMessagesOnScreenToIgnore = stringsToIgnore.split(";");
                if (Arrays.stream(errorMessagesOnScreenToIgnore).noneMatch(s -> s.contains(iTestResult.getThrowable().getMessage()))) {
                    testNode.assignCategory(FrameworkSystemTags.POTENTIAL_SYSTEM_FAILURE.getValue());
                    testNode.assignCategory(FrameworkSystemTags.ERROR_ON_SCREEN.getValue());
                    if (writeErrorMessageToLog.get()) {
                        testNode.fail(iTestResult.getThrowable().getMessage());
                    }
                } else {
                    testNode.log(Status.INFO, "Match found for: " + iTestResult.getThrowable().getMessage() + " in " + Arrays.toString(errorMessagesOnScreenToIgnore) + ". Not marking as a Potential system failure");
                }
            }
        }

        regressionLogger.info(RegressionLogTemplates.getLogTemplateForTestEndFailed(iTestResult));

        if (writeToDatabase) {
            ReportManager.recordTestResult(iTestResult, testNode.getStatus().toString());
        } else {
            System.out.println("Test " + dto.getTestName() + "Failed but, Build is marked as TestBuild, skipping recording results to db");
        }
    }

    // fires when a test is skipped
    @Override
    public void onTestSkipped(final ITestResult iTestResult) {
        final TestDetailsDTO dto = buildTestDetailsDTO(iTestResult);
        final ExtentTest extentLogger = ReportManager.getTest(dto).getExtentTest();
        extentLogger.getModel().setStartTime(new Date(iTestResult.getStartMillis()));
        extentLogger.getModel().setEndTime(new Date(iTestResult.getEndMillis()));
        extentLogger.skip(iTestResult.getName() + ": Skipped");
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotationsByType(AutomatedTest.class).length > 0) {
            extentLogger.log(Status.SKIP, "Test is not marked with @AutomationTest annotation: Skipping Test");
            if (writeToDatabase) {
                ReportManager.recordTestResult(iTestResult, Status.SKIP.toString());
            } else {
                System.out.println("Test " + dto.getTestName() + "has been Skipped. but, Build is marked as TestBuild, skipping recording results to db");
            }
        }
        regressionLogger.info(RegressionLogTemplates.getLogTemplateForTestEndSkipped(iTestResult));
    }

    // fires when the test fails, but passes a certain test coverage percentage.
    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult iTestResult) {

    }

    // Fires on Finishing a test class
    @Override
    public void onFinish(final ITestContext iTestContext) {
        try {
            final TestRuntimeDTO testRuntimeDTO = buildTestRuntimeDTO(iTestContext);
            System.out.println("Insert/Update completed Test: " + testRuntimeDTO.getFullClassName() + ": " + testRuntimeDTO);
            ReportManager.insertIntoTestRuntimeCatalog(testRuntimeDTO);
        } catch (final ArrayIndexOutOfBoundsException aie) {
            if (iTestContext.getAllTestMethods().length == 0) {
                RegressionLogger.getXMLTestLogger().warn("No Active tests found in the class. Class Maintenance might be needed.");
            } else {
                throw new RuntimeException(aie);
            }
        }
    }

    // Fires at the end of each suite.
    @Override
    public void onFinish(final ISuite iSuite) {
        System.setProperty("SuiteEndTime", String.valueOf(System.currentTimeMillis()));
//        EMailWriter.writeNewEMail().sendRegressionReport(, "http://qa.idfbins.com/regression_logs/"+ReportManager.REPORT_FILE_NAME+"/"+ReportManager.REPORT_FILE_NAME+".html");
        this.extentReports.flush();
        // generating xml report
        final File xmlReportFile = new File(ReportManager.REPORT_DIRECTORY_LOCATION + File.separator + iSuite.getName() + ".xml");
        final Testsuite testSuite = new Testsuite();
        for (final com.aventstack.extentreports.model.Test ancestorTest : extentReports.getReport().getTestList()) {
            List<com.aventstack.extentreports.model.Test> testsToParse = new ArrayList<>();
            if (ancestorTest.hasChildren()) {
                testsToParse = ancestorTest.getChildren();
            } else {
                testsToParse.add(ancestorTest);
            }

            testSuite.setName(iSuite.getName());
            testSuite.setTests(String.valueOf(testsToParse.size()));
            for (final com.aventstack.extentreports.model.Test test : testsToParse) {
                final Testcase testcase = new Testcase();
                testcase.setName(test.getName());
                testcase.setClassname(test.getFullName());
                testcase.setTime(String.valueOf(Duration.between(LocalDateTime.ofInstant(test.getStartTime().toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(test.getEndTime().toInstant(), ZoneId.systemDefault())).toMinutes()));
                switch (test.getStatus()) {
                    case PASS:
                    case WARNING:
                    case INFO:
                        break;
                    case FAIL:
                        for (final ExceptionInfo exception : test.getExceptions()) {
                            final Failure failure = new Failure();
                            failure.setContent(exception.getStackTrace());
                            failure.setType(exception.getName());
                            failure.setMessage(exception.getException().getLocalizedMessage());
                            testcase.getFailure().add(failure);
                        }
                        break;
                    case SKIP:
                        testcase.setSkipped(test.getFullName());
                        break;
                }
                testSuite.getTestcase().add(testcase);
            }
        }
        try (final FileWriter writer = new FileWriter(xmlReportFile)) {
            final StringWriter sw = new StringWriter();
            JAXB.marshal(testSuite, sw);
            writer.write(sw.toString());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        try {
            this.extentReports.getReport().getTestList().forEach(test -> {
                final LocalTime startTime = test.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                final LocalTime endTime = test.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                System.out.println(test.getFullName() + ": " + Duration.between(startTime, endTime).toMinutes() + " minute(s)");
            });
        } catch (final Exception e) {
            System.out.println("Ran into Exception: " + e.getLocalizedMessage());
        }
        if (this.writeToDatabase && !this.skipSuiteWriteToDB) {
            System.out.println("Recording suite results");
            ReportManager.recordSuiteResults(iSuite);
        } else {
            System.out.println("Suite complete. But, Build is marked as TestBuild, skipping recording suite results to db");
        }

        if (Boolean.parseBoolean(System.getProperty("SplitSuitesAtEnd", "false"))) {
            System.out.println("Splitting suite");
            ReportManager.recordPartitionGWApplicationSuiteResults(System.getProperty("UUID"));
        }
    }

    @SuppressWarnings("Duplicates")
    private String captureScreenshot(final ITestResult iTestResult) {
        final WebDriver driver;
        driver = BrowserFactory.getCurrentBrowser().getDriver();
        final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        final String destinationFilePath = ReportManager.REPORT_DIRECTORY_LOCATION + File.separator + iTestResult.getName() + ".png";
        try {
            final File destFile = new File(destinationFilePath);
            FileUtils.moveFile(scrFile, destFile);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return destinationFilePath.replace("\\\\qa\\regression_logs\\", "http://qa.idfbins.com/regression_logs/").replaceAll("\\\\", "/");
    }

    private TestRuntimeDTO buildTestRuntimeDTO(final ITestContext testContext) {
        final LocalTime endTime = testContext.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        final LocalTime startTime = testContext.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        final long timeTakenToRunSeconds = Duration.between(startTime, endTime).toMinutes() * 60;
        final TestRuntimeDTO testRuntimeDTO = buildTestRuntimeDTOForTestStart(testContext);
        testRuntimeDTO.setTotalRuntime(timeTakenToRunSeconds);
        return testRuntimeDTO;
    }

    private TestRuntimeDTO buildTestRuntimeDTOForTestStart(final ITestContext testContext) {
        final Class realClass = testContext.getAllTestMethods()[0].getRealClass();
        final String isClockMove = realClass.isAnnotationPresent(ClockMoveTest.class) ? "true" : "false";
        String testType = StringConstants.UI_TEST;
        if (realClass.isAnnotationPresent(APITest.class)) {
            testType = StringConstants.API_TEST;
        }

        if (realClass.isAnnotationPresent(SmokeTest.class)) {
            testType = StringConstants.SMOKE_TEST;
        }

        return TestRuntimeDTO.getInstance(realClass.getName(), realClass.getPackage().getName(), 0, System.getProperty("startedByUser"), isClockMove, testType);
    }
}
