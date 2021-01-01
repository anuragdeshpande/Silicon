package framework.database.models;

public class TestRuntimeDTO {
    private String fullClassName;
    private String packageName;
    private long totalRuntime;
    private String projectSource;
    private String isClockMove;
    private String testType;

    public TestRuntimeDTO() {
    }

    private TestRuntimeDTO(String fullClassName, String packageName, long totalRuntime, String projectSource, String isClockMove, String testType) {
        this.fullClassName = fullClassName;
        this.packageName = packageName;
        this.totalRuntime = totalRuntime;
        this.projectSource = projectSource;
        this.isClockMove = isClockMove;
        this.testType = testType;
    }
    
    // Getter

    public String getFullClassName() {
        return fullClassName;
    }

    public String getPackageName() {
        return packageName;
    }

    public long getTotalRuntime() {
        return totalRuntime;
    }

    public String getProjectSource() {
        return projectSource;
    }

    public String getIsClockMove() {
        return isClockMove;
    }

    public String getTestType() {
        return testType;
    }

    // Setter


    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setTotalRuntime(long totalRuntime) {
        this.totalRuntime = totalRuntime;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
    }

    public void setIsClockMove(String isClockMove) {
        this.isClockMove = isClockMove;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public static int getFieldCount(){
        return TestResultsDTO.class.getDeclaredFields().length;
    }

    public static String getJDBCPreparedInsertStatementWithoutParameters(){
        return "INSERT INTO Regression.dbo.TestRuntimeCatalog (fullClassName, packageName, totalRunTime, projectSource, isClockMove, testType) VALUES (?,?,?,?,?,?);";
    }

    public static String getJDBCPreparedUpdateStatementWithoutParameters(String fullClassName, String packageName){
        return "Update Regression.dbo.TestRuntimeCatalog set fullClassName=?,packageName=?,totalRunTime=?,projectSource=?,isClockMove=?,testType=? where fullClassName='"+fullClassName+"' and packageName='"+packageName+"'";
    }

    public Object[] getValuesAsObjectArray(){
        Object[] values = new Object[getFieldCount()];
        values[0] = getFullClassName();
        values[1] = getPackageName();
        values[2] = getTotalRuntime();
        values[3] = getProjectSource();
        values[4] = getIsClockMove();
        values[5] = getTestType();

        return values;
    }

    public static TestRuntimeDTO getInstance(String fullClassName, String packageName, long totalRuntime, String projectSource, String isClockMove, String testType){
        return new TestRuntimeDTO(fullClassName, packageName, totalRuntime, projectSource, isClockMove, testType);
    }

    @Override
    public String toString() {
        return "fullClassName= " + fullClassName + ", packageName= " + packageName +  ", totalRuntime= " + totalRuntime + " seconds, projectSource= " + projectSource+" isClockMove: "+isClockMove+" testType: "+testType;
    }
}
