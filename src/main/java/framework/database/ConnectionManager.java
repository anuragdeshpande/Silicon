package framework.database;

import framework.enums.Database;
import framework.enums.Environment;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

public class ConnectionManager {

    public static QueryRunner getDBConnectionTo(Environment environment){
        switch (environment){
            case CC8IT: return buildDataSource(Database.GWIT, environment.getDatabaseName());
            case CC8QA: return buildDataSource(Database.GWQA, environment.getDatabaseName());
            case CC8DEV:
            case CC8DEV3:
                return buildDataSource(Database.GWDEV, environment.getDatabaseName());
            case CC8UAT:
            case CC8BUAT2:
            case CC8BUAT:
                return buildDataSource(Database.GWUAT, environment.getDatabaseName());
            default:
            case REPORTING:
                return buildDataSource(Database.REPORTING, environment.getDatabaseName());
        }
    }

    private static String buildConnectionString(String serverName, String databaseName){
        return "jdbc:sqlserver://"+serverName+":1433;DatabaseName="+databaseName;
    }

    private static QueryRunner buildDataSource(Database database, String databaseName){
        BasicDataSource testResultsDS = new BasicDataSource();
        testResultsDS.setUrl(buildConnectionString(database.getServerName(), databaseName));
        testResultsDS.setUsername(database.getUsername());
        testResultsDS.setPassword(database.getPassword());
        testResultsDS.setMinIdle(5);
        testResultsDS.setMaxIdle(10);
        testResultsDS.setMaxOpenPreparedStatements(100);

        return new QueryRunner(testResultsDS);
    }
}
