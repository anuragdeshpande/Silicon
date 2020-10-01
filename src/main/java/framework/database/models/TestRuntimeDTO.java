package framework.database.models;

public class TestRuntimeDTO {
    private String fullClassName;
    private String packageName;
    private long totalRuntime;
    private String projectSource;

    private TestRuntimeDTO(String fullClassName, String packageName, long totalRuntime, String projectSource) {
        this.fullClassName = fullClassName;
        this.packageName = packageName;
        this.totalRuntime = totalRuntime;
        this.projectSource = projectSource;
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

    public static int getFieldCount(){
        return TestResultsDTO.class.getDeclaredFields().length;
    }

    public static String getJDBCPreparedInsertStatementWithoutParameters(){
        return "INSERT INTO Regression.dbo.TestRuntimeCatalog (fullClassName, packageName, totalRunTime, projectSource) VALUES (?,?,?,?);";
    }

    public Object[] getValuesAsObjectArray(){
        Object[] values = new Object[getFieldCount()];
        values[0] = getFullClassName();
        values[1] = getPackageName();
        values[2] = getTotalRuntime();
        values[3] = getProjectSource();

        return values;
    }

    public static TestRuntimeDTO getInstance(String fullClassName, String packageName, long totalRuntime, String projectSource){
        return new TestRuntimeDTO(fullClassName, packageName, totalRuntime, projectSource);
    }
}
