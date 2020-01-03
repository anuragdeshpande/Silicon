package framework.database;

import framework.database.models.DBConnectionDTO;
import framework.environmentResolution.Environment;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.lang3.Validate;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {

    public static QueryRunner getDBConnectionTo(Environment environment) {
        return buildDataSource(environment.getDbConnectionDetails());
    }

    public static QueryRunner getDBConnectionTo(DBConnectionDTO connectionDetails) {
        return buildDataSource(connectionDetails);
    }

    private static String buildConnectionString(String serverName, String databaseName) {
        return "jdbc:sqlserver://" + serverName + ":1433;DatabaseName=" + databaseName;
    }

    private static QueryRunner buildDataSource(DBConnectionDTO dbConnectionDetails) {
        // Only proceed to connection if all the necessary details are present
        Validate.notNull(dbConnectionDetails.getDbName());
        Validate.notNull(dbConnectionDetails.getDbServer());
        Validate.notNull(dbConnectionDetails.getDbUsername());
        Validate.notNull(dbConnectionDetails.getDbPassword());
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find Drivers");
        }
        BasicDataSource testResultsDS = new BasicDataSource();
        testResultsDS.setUrl(buildConnectionString(dbConnectionDetails.getDbServer(), dbConnectionDetails.getDbName()));
        testResultsDS.setUsername(dbConnectionDetails.getDbUsername());
        testResultsDS.setPassword(dbConnectionDetails.getDbPassword());
        testResultsDS.setMinIdle(5);
        testResultsDS.setMaxIdle(10);
        testResultsDS.setMaxOpenPreparedStatements(100);

        return new QueryRunner(testResultsDS);
    }

    public static ResultSetHandler<List<Object[]>> getResultHandlerInstance() {
        return resultSet -> {
            ArrayList<Object[]> results = new ArrayList<>();
            while (resultSet.next()) {
                ResultSetMetaData meta = resultSet.getMetaData();
                int cols = meta.getColumnCount();
                Object[] RowResult = new Object[cols];

                for (int i = 0; i < cols; i++) {
                    RowResult[i] = resultSet.getObject(i + 1);
                }

                results.add(RowResult);
            }
            return results;
        };
    }
}
