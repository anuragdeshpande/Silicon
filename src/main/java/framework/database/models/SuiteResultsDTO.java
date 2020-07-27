package framework.database.models;

import java.sql.Timestamp;

public class SuiteResultsDTO {
    private String applicationName;
    private int passedTests;
    private int failedTests;
    private int skippedTests;
    private int warningTests;
    private String jenkinsBuildNumber;
    private String suiteName;
    private String reportPath;
    private String UUID;
    private boolean shouldShowInPowerBI;
    private boolean shouldMarkSuiteAsTestBuild;
    private Timestamp suiteStartTimeStamp;
    private Timestamp suiteEndTimeStamp;

    private SuiteResultsDTO(String applicationName, int passedTests, int failedTests, int skippedTests, int warningTests, String jenkinsBuildNumber, String suiteName, String reportPath) {
        this.applicationName = applicationName;
        this.passedTests = passedTests;
        this.failedTests = failedTests;
        this.skippedTests = skippedTests;
        this.jenkinsBuildNumber = jenkinsBuildNumber;
        this.suiteName = suiteName;
        this.reportPath = reportPath;
        this.warningTests = warningTests;
        this.UUID = System.getProperty("UUID");
        this.shouldShowInPowerBI = System.getProperty("MarkAsTestBuild") != null && System.getProperty("MarkAsTestBuild").equalsIgnoreCase("false");
        this.shouldMarkSuiteAsTestBuild = !this.shouldShowInPowerBI;
        this.suiteStartTimeStamp = new Timestamp(Long.parseLong(System.getProperty("SuiteStartTime")));
        this.suiteEndTimeStamp = new Timestamp(Long.parseLong(System.getProperty("SuiteEndTime")));
    }

    public static SuiteResultsDTO createInstance(String applicationName, int passedTests, int failedTests, int skippedTests, int warningTests, String jenkinsBuildNumber, String suiteName, String reportPath){
        return new SuiteResultsDTO(applicationName, passedTests, failedTests, skippedTests, warningTests, jenkinsBuildNumber, suiteName, reportPath);
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

    public int getWarningTests() {
        return warningTests;
    }

    public void setWarningTests(int warningTests) {
        this.warningTests = warningTests;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public boolean shouldShowInPowerBI() {
        return shouldShowInPowerBI;
    }

    public void setShouldShowInPowerBI(boolean shouldShowInPowerBI) {
        this.shouldShowInPowerBI = shouldShowInPowerBI;
    }

    public boolean isSuiteTestBuild() {
        return shouldMarkSuiteAsTestBuild;
    }

    public void setShouldMarkSuiteAsTestBuild(boolean shouldMarkSuiteAsTestBuild) {
        this.shouldMarkSuiteAsTestBuild = shouldMarkSuiteAsTestBuild;
    }

    public Timestamp getSuiteStartTimeStamp() {
        return suiteStartTimeStamp;
    }

    public void setSuiteStartTimeStamp(Timestamp suiteStartTimeStamp) {
        this.suiteStartTimeStamp = suiteStartTimeStamp;
    }

    public Timestamp getSuiteEndTimeStamp() {
        return suiteEndTimeStamp;
    }

    public void setSuiteEndTimeStamp(Timestamp suiteEndTimeStamp) {
        this.suiteEndTimeStamp = suiteEndTimeStamp;
    }

    public static String getJDBCPreparedInsertStatementWithoutParameters(){
        return "INSERT INTO SuiteResults(ApplicationName, PassedTests, FailedTests, SkippedTests, FatalTests, WarningTests, BuildNumber, SuiteName, ReportPath, Suite_Date, Suite_End_Date, IsTestBuild, ShowInPowerBIDashboard,UUID) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }
}
