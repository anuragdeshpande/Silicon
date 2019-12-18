package framework.integrations.gwServices.gwTestRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import framework.ReportManager;
import framework.integrations.gwServices.gwTestRunner.generated.Testsuite;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class GWTestRunResults {

    private Testsuite testsuiteResults;

    public GWTestRunResults(Testsuite testsuiteResults) {
        this.testsuiteResults = testsuiteResults;
    }

    public void generateHTMLReport() {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String suiteName = this.testsuiteResults.getName();
        ExtentReports extentReports = ReportManager.initiate(suiteName + "_" + timeStamp);
        if (this.testsuiteResults.getTestcase().size() > 0) {
            this.testsuiteResults.getTestcase().forEach(testCase -> {
                ExtentTest test = extentReports.createTest(testCase.getName());
                if(testCase.getFailure().isEmpty() && testCase.getError().isEmpty()){
                    test.pass(testCase.getName()+": Passed");
                }
                if(!testCase.getError().isEmpty()){
                    testCase.getError().forEach(error -> {
                        test.fatal(error.getType());
                        test.fatal(MarkupHelper.createCodeBlock(error.getMessage().replaceAll("   ", "\n").replaceAll("\nat", "\n\tat")));
                    });
                }

                if(!testCase.getFailure().isEmpty()){
                    testCase.getFailure().forEach(failure -> {
                        test.fatal(failure.getType());
                        test.fatal(MarkupHelper.createCodeBlock(failure.getMessage().replaceAll("   ", "\n").replaceAll("\nat", "\n\tat")));
                    });
                }
            });
        } else {
            extentReports.createTest("No Tests found.");
        }
        extentReports.flush();
    }

    public boolean recordResultsInReportsDb(){
//        String jenkinsBuildNumber = System.getProperty("jenkinsBuildNumber");
        String jenkinsBuildNumber = "9999999";
        String startedByUser = System.getProperty("startedByUser");

        AtomicReference<String> failureReason = new AtomicReference<>();
        if(startedByUser == null){
            startedByUser = "Local";
        }


        if(this.testsuiteResults.getTestcase().size() > 0){
            String finalStartedByUser = startedByUser;

            // Recording to the TestResults Table
            this.testsuiteResults.getTestcase().forEach(testcase -> {
                String status = "Success";
                if(testcase.getFailure().size() > 0 && testcase.getError().size() > 0){
                    status = "Failure";
                }

                if(testcase.getFailure().size() > 0){
                    failureReason.set(testcase.getFailure().get(0).getType());
                }

                if(testcase.getError().size() > 0){
                    failureReason.set(testcase.getError().get(0).getType());
                }

                ReportManager.insertIntoTestResults(false, "Guidewire", testcase.getName(),
                        null, null, null, status, failureReason.get(), jenkinsBuildNumber, testsuiteResults.getName(), finalStartedByUser, "");
            });

            // Recording to the SuiteResults Table
            String applicationName = System.getProperty("ApplicationName");
            String reportPath = ReportManager.getReportPath();
            AtomicInteger totalTests = new AtomicInteger(0);
            AtomicInteger passingTests = new AtomicInteger(0);
            AtomicInteger failingTests = new AtomicInteger(0);
            AtomicInteger skippedTests = new AtomicInteger(0);
            if(testsuiteResults.getTestcase().size() > 0){
                totalTests.set(testsuiteResults.getTests().length());
                testsuiteResults.getTestcase().forEach(testCase -> {
                    if(testCase.getError().isEmpty() && testCase.getFailure().isEmpty()){
                        passingTests.getAndIncrement();
                    } else {
                        failingTests.getAndIncrement();
                    }
                });
            }
            double passPercentage = ((double)passingTests.get()/totalTests.get())*100;
            double failPercentage = ((double) failingTests.get()/totalTests.get())*100;
            ReportManager.insertIntoSuiteResults(applicationName, passPercentage, failPercentage, skippedTests.get(), jenkinsBuildNumber, testsuiteResults.getName(), reportPath);
        }
        return false;
    }
}
