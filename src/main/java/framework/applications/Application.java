package framework.applications;

import framework.database.ConnectionManager;
import framework.enums.Environment;
import framework.integrations.ftp.FTPConnection;
import framework.logger.RegressionLogger;
import org.apache.commons.dbutils.QueryRunner;


abstract public class Application {
    protected Environment environment;
    protected RegressionLogger logger;

    public Application(RegressionLogger logger){
        this.logger = logger;
    }

    protected QueryRunner connectToDB() {
        return ConnectionManager.getDBConnectionTo(this.environment);
    }

    public RegressionLogger getLogger() {
        return logger;
    }

    public FTPConnection openFTPConnection(String host, String username, String password){
        return new FTPConnection(this, host,username,password);
    }
}
