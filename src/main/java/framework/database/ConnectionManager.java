package framework.database;

import framework.enums.Database;
import framework.enums.Environment;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            case CCREGR01:
                return buildDataSource(Database.REGR01, environment.getDatabaseName());
            case CCREGR02:
                return buildDataSource(Database.REGR02, environment.getDatabaseName());
            case CCREGR03:
                return buildDataSource(Database.REGR03, environment.getDatabaseName());
            case CCREGR04:
                return buildDataSource(Database.REGR04, environment.getDatabaseName());
            case CCREGR05:
                return buildDataSource(Database.REGR05, environment.getDatabaseName());
            case CCREGR06:
                return buildDataSource(Database.REGR06, environment.getDatabaseName());
            case CCREGR07:
                return buildDataSource(Database.REGR07, environment.getDatabaseName());
            case QA_WIZPRO_DATA_REPO:
                return buildDataSource(Database.QAWIZPRO_DATA_REPO, environment.getDatabaseName());
            case REPORTING:
                return buildDataSource(Database.REPORTING, environment.getDatabaseName());
            default:
                throw new NotImplementedException();
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

    public static ResultSetHandler<List<Object[]>> getResultHandlerInstance(){
        return new ResultSetHandler<List<Object[]>>() {
            @Override
            public List<Object[]> handle(ResultSet resultSet) throws SQLException {
                ArrayList<Object[]> results = new ArrayList<>();
                while(resultSet.next()){
                    ResultSetMetaData meta = resultSet.getMetaData();
                    int cols = meta.getColumnCount();
                    Object[] RowResult = new Object[cols];

                    for (int i = 0; i < cols; i++) {
                        RowResult[i] = resultSet.getObject(i + 1);
                    }

                    results.add(RowResult);
                }
                return results;
            }
        };
    }
}
