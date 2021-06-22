package framework.database.models;

import com.aventstack.extentreports.Status;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;

public class TestResultsDTO {
    // ONLY DECLARE VARIABLES THAT ARE BEING USED FOR INSERT STATEMENT
    // THERE IS A REFLECTION UTILITY TO COUNT THESE VARIABLES
    private boolean isClockMove;
    private String testCreator;
    private String testName;
    private String className;
    private String packageName;
    private Timestamp startTimeStamp;
    private Timestamp endTimestamp;
    private String failureImageURL;
    private Status Status;
    private String failureReason;
    private String buildNumber;
    private String suiteName;
    private String testRunSource;
    private String tags;
    private String UUID;


    private TestResultsDTO(boolean isClockMove, String testCreator, String testName, String className, String packageName, Timestamp startTimeStamp, Timestamp endTimestamp, String failureImageURL, Status status, String failureReason, String buildNumber, String suiteName, String testRunSource, String tags) {
        this.isClockMove = isClockMove;
        this.testCreator = testCreator;
        this.testName = testName;
        this.startTimeStamp = startTimeStamp;
        this.endTimestamp = endTimestamp;
        this.failureImageURL = failureImageURL;
        Status = status;
        this.failureReason = failureReason;
        this.buildNumber = buildNumber;
        this.suiteName = suiteName;
        this.testRunSource = testRunSource;
        this.tags = tags;
        this.UUID = System.getProperty("UUID");
        this.className = className;
        this.packageName = packageName;
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

    public Status getStatus() {
        return Status;
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

    public String getTags() {
        return tags;
    }

    public String getUUID() {
        return UUID;
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

    public void setStatus(Status status) {
        Status = status;
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

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public static int getFieldCount(){
        return TestResultsDTO.class.getDeclaredFields().length;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public static String getJDBCPreparedInsertStatementWithoutParameters(){
        return "INSERT INTO TestResults" +
                "(ClockMove, TestCreator, TestName, ClassName, PackageName, StartTime, " +
                "EndTime, FailureImageURL, TestStatus, FailureReason, " +
                "BuildNumber, SuiteName, TestRunSource, Tags, UUID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    public Object[] getValuesAsObjectArray(){
        // Order of array is important. Keep it inline with the prepared statement,
        // refer getJDBCPreparedInsertStatementWithoutParameters method.
        Object[] values = new Object[getFieldCount()];
        values[0] = isClockMove();
        values[1] = getTestCreator();
        values[2] = getTestName();
        values[3] = getClassName();
        values[4] = getPackageName();
        values[5] = getStartTimeStamp();
        values[6] = getEndTimestamp();
        values[7] = getFailureImageURL();
        values[8] = StringUtils.capitalize(getStatus().name());
        values[9] = getFailureReason();
        values[10] = getBuildNumber();
        values[11] = getSuiteName();
        values[12] = getTestRunSource();
        values[13] = getTags();
        values[14] = getUUID();
        return values;
    }

    public static TestResultsDTO getInstance(boolean clockMove, String testCreator, String testName, String className, String packageName,
                                             Timestamp startDate, Timestamp endDate, String failureImageURL,
                                             Status status, String failureReason, String buildNumber,
                                             String suiteName, String testRunSource, String tags){
        return new TestResultsDTO(clockMove, testCreator, testName, className, packageName, startDate, endDate, failureImageURL, status, failureReason, buildNumber, suiteName, testRunSource, tags);

    }



}
