package framework.reports.models;

public class TestDetailsDTO {
    private String testName;
    private String className;
    private String xmlTestName;
    private String suiteName;
    private String packageName;

    public TestDetailsDTO() {
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getXmlTestName() {
        return xmlTestName;
    }

    public void setXmlTestName(String xmlTestName) {
        this.xmlTestName = xmlTestName;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public String getReportTestName(){
        return this.className+"_"+this.testName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
