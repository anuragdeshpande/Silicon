package framework.integrations.gwServices.gwTestRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import framework.ReportManager;
import framework.database.models.SuiteResultsDTO;
import framework.database.models.TestResultsDTO;
import framework.integrations.gwServices.gwTestRunner.generated.Testcase;
import framework.integrations.gwServices.gwTestRunner.generated.Testsuite;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

public class GWTestRunResults {

    private Testsuite testsuiteResults;

    public GWTestRunResults(Testsuite testsuiteResults) {
        this.testsuiteResults = testsuiteResults;
    }

    public void generateHTMLReport() {
        DateFormat utcFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date testSuiteTimeStamp;
        try {
            testSuiteTimeStamp = utcFormat.parse(testsuiteResults.getTimestamp());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(testSuiteTimeStamp);
        String suiteName = this.testsuiteResults.getName();
        ExtentReports extentReports = ReportManager.initiate(suiteName + "_" + timeStamp);
        // report to klov reporting if flag is setup
        if (System.getProperty("EnableKLOVReporting", "false").equalsIgnoreCase("true")) {
            System.out.println("Attempting to push tests to klov server");
            ExtentKlovReporter klov = new ExtentKlovReporter();
            klov
                    .initKlovServerConnection(System.getProperty("KLOVHost", "http://127.0.0.1:80"))
                    .initMongoDbConnection(System.getProperty("MongoHost", "127.0.0.1"),  27017);
            klov.setProjectName(System.getProperty("ProjectName"));
            klov.setReportName(System.getProperty("ApplicationName")+"_"+System.getProperty("jenkinsBuildNumber"));
            extentReports.attachReporter(klov);
        }
        extentReports.setReportUsesManualConfiguration(true);
        if (this.testsuiteResults.getTestcase().size() > 0) {
            for (Testcase testCase : this.testsuiteResults.getTestcase()) {
                ExtentTest test = extentReports.createTest(testCase.getName());
                test.getModel().setDescription("Package Name: "+testCase.getClassname().replaceAll("null\\(", "").replaceAll("\\)", ""));
                test.getModel().setStartTime(testSuiteTimeStamp);
                testSuiteTimeStamp = DateUtils.addMilliseconds(testSuiteTimeStamp, (int) (Double.parseDouble(testCase.getTime()) * 1000));
                test.getModel().setEndTime(testSuiteTimeStamp);
                if (testCase.getFailure().isEmpty() && testCase.getError().isEmpty()) {
                    test.pass(testCase.getName() + ": Passed");
                }
                if (!testCase.getError().isEmpty()) {
                    testCase.getError().forEach(error -> {
                        test.fail(error.getType());
                        test.fail(MarkupHelper.createCodeBlock(error.getMessage().replaceAll("   ", "  ")
                                .replaceAll("  ", "\n").replaceAll("\nat", "\n\tat")));
                    });
                }

                if (!testCase.getFailure().isEmpty()) {
                    testCase.getFailure().forEach(failure -> {
                        test.fail(failure.getType());
                        test.fail(MarkupHelper.createCodeBlock(failure.getMessage().replaceAll("   ", "  ")
                                .replaceAll("  ", "\n").replaceAll("\nat", "\n\tat")));
                    });
                }
            }

        } else {
            extentReports.createTest("No Tests found.");
        }
        extentReports.flush();
    }

    public boolean recordResultsInReportsDb() {
        DateFormat utcFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date testSuiteTimeStamp;
        try {
            testSuiteTimeStamp = utcFormat.parse(testsuiteResults.getTimestamp());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String jenkinsBuildNumber = System.getProperty("jenkinsBuildNumber");
        String startedByUser = System.getProperty("startedByUser");

        if (startedByUser == null) {
            startedByUser = "Local";
        }

        if(jenkinsBuildNumber == null){
            jenkinsBuildNumber = "9999999";
            System.out.println("No Jenkins Build Number Received. Using default Build Number: 9999999");
        }

        if (this.testsuiteResults.getTestcase().size() > 0) {
            ArrayList<TestResultsDTO> resultsDTOS = new ArrayList<>();
            // Recording to the TestResults Table
            for (Testcase testcase : this.testsuiteResults.getTestcase()) {
                String failureReason = null;
                Status status = Status.PASS;
                // Time on testCase is total run time in Seconds. Convert to milliseconds and construct timeStamps.
                DecimalFormat format = new DecimalFormat("000000.000");
                int testRunTimeInMilliSeconds = (int)((Double.parseDouble(format.format(Double.parseDouble(testcase.getTime()))))*1000);
                Timestamp startTimeStamp = new Timestamp(testSuiteTimeStamp.getTime());
                Timestamp endTimeStamp = new Timestamp(DateUtils.addMilliseconds(testSuiteTimeStamp, testRunTimeInMilliSeconds).getTime());
                testSuiteTimeStamp = endTimeStamp;

                if (testcase.getSkipped() != null) {
                    status = Status.SKIP;
                }

                if (testcase.getFailure().size() > 0) {
                    failureReason = testcase.getFailure().get(0).getType();
                    status = Status.FAIL;
                }

                if (testcase.getError().size() > 0) {
                    failureReason = testcase.getError().get(0).getType();
                    status = Status.FAIL;
                }

                TestResultsDTO resultsDTO = TestResultsDTO.getInstance(false, "Guidewire", testcase.getName(),
                        startTimeStamp, endTimeStamp, null, status, failureReason, jenkinsBuildNumber, testsuiteResults.getName(), startedByUser, "");

                resultsDTOS.add(resultsDTO);
            }
            ReportManager.bulkInsertIntoTestResults(resultsDTOS);

            // Recording to the SuiteResults Table
            String applicationName = System.getProperty("ApplicationName");
            if (applicationName == null) {
                applicationName = "Local Run";
            }
            String reportPath = ReportManager.getReportPath();
            AtomicInteger totalTests = new AtomicInteger(0);
            AtomicInteger passingTests = new AtomicInteger(0);
            AtomicInteger failingTests = new AtomicInteger(0);
            AtomicInteger skippedTests = new AtomicInteger(0);
            if (testsuiteResults.getTestcase().size() > 0) {
                totalTests.set(Integer.parseInt(testsuiteResults.getTests()));
                testsuiteResults.getTestcase().forEach(testCase -> {
                    if (testCase.getError().isEmpty() && testCase.getFailure().isEmpty()) {
                        passingTests.getAndIncrement();
                    } else {
                        failingTests.getAndIncrement();
                    }
                });
            }
            SuiteResultsDTO suiteResultsDTO = SuiteResultsDTO.createInstance(applicationName, passingTests.get(), failingTests.get(), skippedTests.get(), 0, jenkinsBuildNumber, testsuiteResults.getName(), reportPath);
            ReportManager.insertIntoSuiteResults(suiteResultsDTO);
        }
        return false;
    }

    public Testsuite getTestSuiteResults() {
        return testsuiteResults;
    }
}
