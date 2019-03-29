package framework.enums;

public enum Environment {

    // CC Environment
    CC8DEV("CC8DEV", "http://cc8dev/cc/GuidewireCenter.do", "ClaimCenterDatabase8DEV"),
    CC8DEV3("CC8DEV3", "http://cc8dev3/cc/GuidewireCenter.do", "ClaimCenterDatabase8DEV3"),
    CC8UAT("CC8UAT", "http://cc8uat/cc/GuidewireCenter.do", "ClaimCenterDatabase8UAT"),
    CC8BUAT("CC8BUAT", "http://cc8buat/cc/GuidewireCenter.do", "ClaimCenterDatabase8UAT"),
    CC8BUAT2("CC8UAT2", "http://fbmsgwccb-uat82/cc/GuidewireCenter.do", "ClaimCenterDatabase8UAT2"),
    CC8QA("CC8QA", "http://cc8qa/cc/GuidewireCenter.do", "ClaimCenterDatabase8QA"),
    CC8IT("CC8IT", "http://cc8it/cc/GuidewireCenter.do", "ClaimCenterDatabase8IT"),
    CC8STAB01("CC8STAB01", "http://fbmsgw-stab01:8080/cc/GuidewireCenter.do", "ClaimCenterDatabase8STAB01"),
    CC8STAB02("CC8STAB02", "http://fbmsgw-stab02:8080/cc/GuidewireCenter.do", "ClaimCenterDatabase8STAB02"),
    CC8STAB04("CC8STAB04", "http://fbmsgw-stab04:8080/cc/GuidewireCenter.do", "ClaimCenterDatabase8STAB04"),

    // PC Environments
    PC8DEV("PC8DEV", "http://pc8dev/pc/PolicyCenter.do","PolicyCenterDatabase8DEV" ),

    // AB Environments
    AB8DEV("AB8DEV", "http://ab8dev/ab/ContactManager.do", "ContactManagerDatabase8DEV"),

    // Reporting Environment
    REPORTING("Reporting", null, "Regression"),


    // QA-WIZPro - Data repo
    QA_WIZPRO_DATA_REPO("qawizpro_data_repo", null, "QAWIZPROGLOBALDATAREPOSITORY");

    private String environment;
    private String environmentUrl;
    private String databaseName;

    Environment(String environment, String url, String databaseName) {
        this.environment = environment;
        this.environmentUrl = url;
        this.databaseName = databaseName;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getEnvironmentUrl() {
        return environmentUrl;
    }

    public String getDatabaseName() {
        return databaseName;
    }
}
