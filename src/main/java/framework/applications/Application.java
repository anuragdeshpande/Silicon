package framework.applications;

import framework.database.ConnectionManager;
import framework.enums.Environment;
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
}
