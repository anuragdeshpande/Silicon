package framework.applications;

import framework.database.ConnectionManager;
import framework.enums.Environment;
import org.apache.commons.dbutils.QueryRunner;

abstract public class Application {
    protected Environment environment;
    protected QueryRunner connectToDB(){
        return ConnectionManager.getDBConnectionTo(this.environment);
    }
}
