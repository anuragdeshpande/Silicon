package framework.database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class ConnectionManager {
    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl("jdbc:sqlserver://fbms2048:1433;DatabaseName=QAWIZPROGlobalDataRepository");
        ds.setUsername("SeleniumUser");
        ds.setPassword("seleniumuser");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static QueryRunner getRegressionDB() {
        return new QueryRunner(ds);
    }
}
