package framework.database.models;

public class SuiteResultsDTO {
    private String applicationName;
    private int passedTests;
    private int failedTests;
    private int skippedTests;
    private String jenkinsBuildNumber;
    private String suiteName;
    private String reportPath;

    private SuiteResultsDTO(String applicationName, int passedTests, int failedTests, int skippedTests, String jenkinsBuildNumber, String suiteName, String reportPath) {
        this.applicationName = applicationName;
        this.passedTests = passedTests;
        this.failedTests = failedTests;
        this.skippedTests = skippedTests;
        this.jenkinsBuildNumber = jenkinsBuildNumber;
        this.suiteName = suiteName;
        this.reportPath = reportPath;
    }

    public SuiteResultsDTO createInstance(String applicationName, int passedTests, int failedTests, int skippedTests, String jenkinsBuildNumber, String suiteName, String reportPath){
        return new SuiteResultsDTO(applicationName, passedTests, failedTests, skippedTests, jenkinsBuildNumber, suiteName, reportPath);
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getPassedTests() {
        return passedTests;
    }

    public void setPassedTests(int passedTests) {
        this.passedTests = passedTests;
    }

    public int getFailedTests() {
        return failedTests;
    }

    public void setFailedTests(int failedTests) {
        this.failedTests = failedTests;
    }

    public int getSkippedTests() {
        return skippedTests;
    }

    public void setSkippedTests(int skippedTests) {
        this.skippedTests = skippedTests;
    }

    public String getJenkinsBuildNumber() {
        return jenkinsBuildNumber;
    }

    public void setJenkinsBuildNumber(String jenkinsBuildNumber) {
        this.jenkinsBuildNumber = jenkinsBuildNumber;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public double getPassPercentage(){
        return ((double) passedTests / (passedTests + failedTests + skippedTests))*100;
    }

    public double getPassPercentage(int totalTests){
        return ((double) passedTests/totalTests)*100;
    }

    public double failPercentage(){
        return ((double) failedTests / (passedTests + failedTests + skippedTests))*100;
    }

    public double failPercentage(int totalTests){
        return ((double) failedTests/ totalTests)*100;
    }

    public static String getJDBCPreparedInsertStatementWithoutParameters(){
        return "INSERT INTO SuiteResults(ApplicationName, PassPercentage, FailPercentage, SkippedCount, BuildNumber, SuiteName, ReportPath, Suite_Date, IsTestBuild, ShowInPowerBIDashboard) values (?,?,?,?,?,?,?,?,?,?)";
    }
}
