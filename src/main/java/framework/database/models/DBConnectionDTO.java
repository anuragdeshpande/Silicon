package framework.database.models;

/**
 * DTO for DB connection properties
 */
public class DBConnectionDTO {
    private String dbServer;
    private String dbUsername;
    private String dbPassword;
    private String dbName;

    public static final DBConnectionDTO QA_DATA_REPO = new DBConnectionDTO("fbms2048.idfbins.com", "qawizproglobal", "test4work", "QAWIZPROGLOBALDATAREPOSITORY");
    public static final DBConnectionDTO TEST_NG_REPORTING_SERVER = new DBConnectionDTO("FBMSGWSQL-RR01.idfbins.com", "sa", "mau$2ugguidewire", "Regression");
    public static final DBConnectionDTO PERFORMANCE_REPORTING_SERVER = new DBConnectionDTO("FBMSGWSQL-RR01.idfbins.com", "sa", "mau$2ugguidewire", "JMeterPerformanceTesting");


    public DBConnectionDTO() {
    }

    public DBConnectionDTO(String dbServer, String dbUsername, String dbPassword, String dbName) {
        this.dbServer = dbServer;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.dbName = dbName;
    }

    public String getDbServer() {
        return dbServer;
    }

    public void setDbServer(String dbServer) {
        this.dbServer = dbServer;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
