package framework.database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

public class ConnectionManager {
    private static BasicDataSource testResultsDS = new BasicDataSource();

    static {
        testResultsDS.setUrl("jdbc:sqlserver://FBMSGWSQL-RR01:1433;DatabaseName=Regression");
        testResultsDS.setUsername("sa");
        testResultsDS.setPassword("password");
        testResultsDS.setMinIdle(5);
        testResultsDS.setMaxIdle(10);
        testResultsDS.setMaxOpenPreparedStatements(100);
    }

    public static QueryRunner getRegressionDB() {
        return new QueryRunner(testResultsDS);
    }
}
