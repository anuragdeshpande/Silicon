package framework.database.models;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import java.sql.Timestamp;

public class TestResultsDTO {
    // ONLY DECLARE VARIABLES THAT ARE BEING USED FOR INSERT STATEMENT
    // THERE IS A REFLECTION UTILITY TO COUNT THESE VARIABLES
    private boolean isClockMove;
    private String testCreator;
    private String testName;
    private Timestamp startTimeStamp;
    private Timestamp endTimestamp;
    private String failureImageURL;
    private TestStatus TestStatus;
    private String failureReason;
    private String buildNumber;
    private String suiteName;
    private String testRunSource;
    private String[] tags;

    private TestResultsDTO(boolean isClockMove, String testCreator, String testName, Timestamp startTimeStamp, Timestamp endTimestamp, String failureImageURL, framework.database.models.TestStatus testStatus, String failureReason, String buildNumber, String suiteName, String testRunSource, String... tags) {
        this.isClockMove = isClockMove;
        this.testCreator = testCreator;
        this.testName = testName;
        this.startTimeStamp = startTimeStamp;
        this.endTimestamp = endTimestamp;
        this.failureImageURL = failureImageURL;
        TestStatus = testStatus;
        this.failureReason = failureReason;
        this.buildNumber = buildNumber;
        this.suiteName = suiteName;
        this.testRunSource = testRunSource;
        this.tags = tags;
    }

    public boolean isClockMove() {
        return isClockMove;
    }

    public String getTestCreator() {
        return testCreator;
    }

    public String getTestName() {
        return testName;
    }

    public Timestamp getStartTimeStamp() {
        return startTimeStamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public String getFailureImageURL() {
        return failureImageURL;
    }

    public framework.database.models.TestStatus getTestStatus() {
        return TestStatus;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public String getTestRunSource() {
        return testRunSource;
    }

    public String[] getTags() {
        return tags;
    }

    public void setClockMove(boolean clockMove) {
        isClockMove = clockMove;
    }

    public void setTestCreator(String testCreator) {
        this.testCreator = testCreator;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setStartTimeStamp(Timestamp startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public void setEndTimestamp(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public void setFailureImageURL(String failureImageURL) {
        this.failureImageURL = failureImageURL;
    }

    public void setTestStatus(framework.database.models.TestStatus testStatus) {
        TestStatus = testStatus;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public void setTestRunSource(String testRunSource) {
        this.testRunSource = testRunSource;
    }

    public void setTags(String... tags) {
        this.tags = tags;
    }

    public String getFlatTags(){
        return Joiner.on("|").join(this.tags);
    }

    public static int getFieldCount(){
        return TestResultsDTO.class.getDeclaredFields().length;
    }

    public static String getJDBCPreparedInsertStatementWithoutParameters(){
        return "INSERT INTO TestResults" +
                "(ClockMove, TestCreator, TestName, StartTime, " +
                "EndTime, FailureImageURL, TestStatus, FailureReason, " +
                "BuildNumber, SuiteName, TestRunSource, Tags) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    public Object[] getValuesAsObjectArray(){
        // Order of array is important. Keep it inline with the prepared statement,
        // refer getJDBCPreparedInsertStatementWithoutParameters method.
        Object[] values = new Object[getFieldCount()];
        values[0] = isClockMove();
        values[1] = getTestCreator();
        values[2] = getTestName();
        values[3] = getStartTimeStamp();
        values[4] = getEndTimestamp();
        values[5] = getFailureImageURL();
        values[6] = StringUtils.capitalize(getTestStatus().name());
        values[7] = getFailureReason();
        values[8] = getBuildNumber();
        values[9] = getSuiteName();
        values[10] = getTestRunSource();
        values[11] = getFlatTags();
        return values;
    }

    public static TestResultsDTO getInstance(boolean clockMove, String testCreator, String testName,
                                             Timestamp startDate, Timestamp endDate,  String failureImageURL,
                                             TestStatus status, String failureReason, String buildNumber,
                                             String suiteName, String testRunSource, String... tags){
        return new TestResultsDTO(clockMove, testCreator, testName, startDate, endDate, failureImageURL, status, failureReason, buildNumber, suiteName, testRunSource, tags);

    }

}
