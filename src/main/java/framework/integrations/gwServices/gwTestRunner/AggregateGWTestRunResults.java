package framework.integrations.gwServices.gwTestRunner;

import framework.integrations.gwServices.gwTestRunner.generated.Testcase;
import framework.integrations.gwServices.gwTestRunner.generated.Testsuite;
import framework.integrations.gwServices.gwTestRunner.generated.Testsuites;

import java.util.ArrayList;
import java.util.List;

public class AggregateGWTestRunResults {
    private Testsuites testsuites;
    private Testsuite aggregatedTestSuite;

    public AggregateGWTestRunResults(Testsuites testsuites){
        this.testsuites = testsuites;
        this.aggregatedTestSuite = aggregateTestSuites();
    }

    /**
     * Parses the suite of suites and generates a single cumulative report for of all suites.
     */
    public void generateAggregatedHTMLReport(){
        GWTestRunResults results = new GWTestRunResults(this.aggregatedTestSuite);
        results.generateHTMLReport();
    }

    /**
     * Parses the suite of suites and generates HTML reports for each suite.
     */
    public void generateHTMLReports(){
        testsuites.getTestsuite().forEach(testSuite -> {
            GWTestRunResults results = new GWTestRunResults(testSuite);
            results.generateHTMLReport();
        });
    }

    /**
     * Records the suite of suites to the db hosted for PowerBI dashboard.
     */
    public void recordResultsToDB(){
        GWTestRunResults results = new GWTestRunResults(aggregatedTestSuite);
        results.recordResultsInReportsDb();
    }

    private Testsuite aggregateTestSuites(){
        // Aggregated Data points for recording all test cases under single suite
        List<Testcase> aggregatedTestCases = new ArrayList<>();

        // Aggregated Data points for recording suite result
        int errors = 0;
        int failures = 0;
        int totalTests = 0;
        double totalTimeElapsed = 0;
        String timeStampWhenSuiteStarted = testsuites.getTestsuite().get(0).getTimestamp();

        // Compiling data points for the suite and aggregating test cases.
        for (Testsuite testsuite : testsuites.getTestsuite()) {
            errors+=Integer.parseInt(testsuite.getErrors());
            failures+=Integer.parseInt(testsuite.getFailures());
            totalTests+=testsuite.getTestcase().size();
            totalTimeElapsed+=Double.parseDouble(testsuite.getTime());
            aggregatedTestCases.addAll(testsuite.getTestcase());
        }

        // Compiling HTML Report for all test cases
        Testsuite testsuite = new Testsuite();
        testsuite.setName(testsuites.getName());
        testsuite.setErrors(String.valueOf(errors));
        testsuite.setFailures(String.valueOf(failures));
        testsuite.setId("0");
        testsuite.setTests(String.valueOf(totalTests));
        testsuite.setTime(String.valueOf(totalTimeElapsed));
        testsuite.setTimestamp(timeStampWhenSuiteStarted);

        testsuite.getTestcase().addAll(aggregatedTestCases);

        return testsuite;
    }

    public Testsuite getAggregatedTestSuite() {
        return aggregatedTestSuite;
    }
}
