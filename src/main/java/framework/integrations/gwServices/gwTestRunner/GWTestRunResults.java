package framework.integrations.gwServices.gwTestRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import framework.ReportManager;
import framework.integrations.gwServices.gwTestRunner.generated.Testsuite;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        return false;
    }
}
