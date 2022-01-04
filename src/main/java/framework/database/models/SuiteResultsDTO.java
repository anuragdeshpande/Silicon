package framework.database.models;

import framework.database.ConnectionManager;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class SuiteResultsDTO {
    private String applicationName;
    private int passedTests;
    private int failedTests;
    private int skippedTests;
    private int warningTests;
    private int fatalTests;
    private String jenkinsBuildNumber;
    private String suiteName;
    private String reportPath;
    private String UUID;
    private boolean shouldShowInPowerBI;
    private boolean shouldMarkSuiteAsTestBuild;
    private Timestamp suiteStartTimeStamp;
    private Timestamp suiteEndTimeStamp;
    private Timestamp suite_end_date;
    private String suiteType;

    private SuiteResultsDTO(final String applicationName, final int passedTests, final int failedTests, final int skippedTests, final int warningTests, final int fatalTests, final String jenkinsBuildNumber, final String suiteName, final String reportPath, final String suiteType) {
        this.applicationName = applicationName;
        this.passedTests = passedTests;
        this.failedTests = failedTests;
        this.skippedTests = skippedTests;
        this.fatalTests = fatalTests;
        this.jenkinsBuildNumber = jenkinsBuildNumber;
        this.suiteName = suiteName;
        this.reportPath = reportPath;
        this.warningTests = warningTests;
        this.UUID = System.getProperty("UUID");
        this.shouldShowInPowerBI = System.getProperty("MarkAsTestBuild") != null && System.getProperty("MarkAsTestBuild").equalsIgnoreCase("false");
        this.shouldMarkSuiteAsTestBuild = !this.shouldShowInPowerBI;
        this.suiteStartTimeStamp = new Timestamp(Long.parseLong(System.getProperty("SuiteStartTime")));
        this.suiteEndTimeStamp = new Timestamp(Long.parseLong(System.getProperty("SuiteEndTime")));
        this.suiteType = suiteType;
    }

    public SuiteResultsDTO() {
    }

    public static SuiteResultsDTO createInstance(final String applicationName, final int passedTests, final int failedTests, final int skippedTests, final int warningTests, final int fatalTests, final String jenkinsBuildNumber, final String suiteName, final String reportPath, final String suiteType) {
        return new SuiteResultsDTO(applicationName, passedTests, failedTests, skippedTests, warningTests, fatalTests, jenkinsBuildNumber, suiteName, reportPath, suiteType);
    }

    public static Optional<SuiteResultsDTO> getExisting(final String uuid, final String applicationName, final String suiteName) {
        try {
            final SuiteResultsDTO existingDTO = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER).query("Select * from SuiteResults where UUID = '" + uuid + "' and ApplicationName='" + applicationName + "' and SuiteName='" + suiteName + "'",
                    new BeanHandler<>(SuiteResultsDTO.class));
            return Optional.ofNullable(existingDTO);
        } catch (final SQLException sqlException) {
            return Optional.empty();
        }
    }

    public static SuiteResultsDTO updateExisting(final TestCountDTO testCountDTO, final SuiteResultsDTO existingDTO) {
        existingDTO.incrementPassedTests(testCountDTO.getPassCount());
        existingDTO.incrementFailedTests(testCountDTO.getFailCount());
        existingDTO.incrementSkippedTests(testCountDTO.getSkipCount());
        existingDTO.incrementWarningTests(testCountDTO.getWarningCount());

        return existingDTO;
    }

    public static String getJDBCPreparedInsertStatementWithoutParameters() {
        return "INSERT INTO SuiteResults(ApplicationName, PassedTests, FailedTests, SkippedTests, FatalTests, WarningTests, BuildNumber, SuiteName, ReportPath, Suite_Date, Suite_End_Date, IsTestBuild, ShowInPowerBIDashboard,UUID,SuiteType) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    public static String getJDBCPreparedUpdateStatementWithoutParameters() {
        return "Update SuiteResults set PassedTests=?,FailedTests=?,SkippedTests=?,WarningTests=?,FatalTests=?,Suite_End_Date=GETDATE() where UUID=? and SuiteName=? and ApplicationName=?";
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }

    public int getPassedTests() {
        return passedTests;
    }

    public void setPassedTests(final int passedTests) {
        this.passedTests = passedTests;
    }

    public int getFailedTests() {
        return failedTests;
    }

    public void setFailedTests(final int failedTests) {
        this.failedTests = failedTests;
    }

    public int getFatalTests() {
        return fatalTests;
    }

    public int getSkippedTests() {
        return skippedTests;
    }

    public void setSkippedTests(final int skippedTests) {
        this.skippedTests = skippedTests;
    }

    public String getJenkinsBuildNumber() {
        return jenkinsBuildNumber;
    }

    public void setJenkinsBuildNumber(final String jenkinsBuildNumber) {
        this.jenkinsBuildNumber = jenkinsBuildNumber;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(final String suiteName) {
        this.suiteName = suiteName;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(final String reportPath) {
        this.reportPath = reportPath;
    }

    public int getWarningTests() {
        return warningTests;
    }

    public void setWarningTests(final int warningTests) {
        this.warningTests = warningTests;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(final String UUID) {
        this.UUID = UUID;
    }

    public boolean shouldShowInPowerBI() {
        return shouldShowInPowerBI;
    }

    public void setShouldShowInPowerBI(final boolean shouldShowInPowerBI) {
        this.shouldShowInPowerBI = shouldShowInPowerBI;
    }

    public boolean isSuiteTestBuild() {
        return shouldMarkSuiteAsTestBuild;
    }

    public void setShouldMarkSuiteAsTestBuild(final boolean shouldMarkSuiteAsTestBuild) {
        this.shouldMarkSuiteAsTestBuild = shouldMarkSuiteAsTestBuild;
    }

    public Timestamp getSuiteStartTimeStamp() {
        return suiteStartTimeStamp;
    }

    public void setSuiteStartTimeStamp(final Timestamp suiteStartTimeStamp) {
        this.suiteStartTimeStamp = suiteStartTimeStamp;
    }

    public Timestamp getSuiteEndTimeStamp() {
        return suiteEndTimeStamp;
    }

    public void setSuiteEndTimeStamp(final Timestamp suiteEndTimeStamp) {
        this.suiteEndTimeStamp = suiteEndTimeStamp;
    }

    public Timestamp getSuite_end_date() {
        return suite_end_date;
    }

    public void setSuite_end_date(final Timestamp suite_end_date) {
        this.suite_end_date = suite_end_date;
    }

    public String getSuiteType() {
        return suiteType;
    }

    public void setSuiteType(final String suiteType) {
        this.suiteType = suiteType;
    }

    public void incrementPassedTests(final int testCount) {
        this.passedTests += testCount;
    }

    public void incrementFailedTests(final int testCount) {
        this.failedTests += testCount;
    }

    public void incrementSkippedTests(final int testCount) {
        this.skippedTests += testCount;
    }

    public void incrementFatalTests(final int testCount) {
        this.failedTests += testCount;
    }

    public void incrementWarningTests(final int testCount) {
        this.warningTests += testCount;
    }
}
