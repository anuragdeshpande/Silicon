package framework.integrations.gwServices.gwTestRunner;

public class GWTestBean {
    private String suiteName;
    private String packageString;

    private GWTestBean(String suiteName, String packageString) {
        this.suiteName = suiteName;
        this.packageString = packageString;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public String getPackageString() {
        return packageString;
    }

    public static GWTestBean getInstance(String suiteName, String packageString){
        return new GWTestBean(suiteName, packageString);
    }
}
