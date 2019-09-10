package framework.applications;

import com.github.javafaker.Faker;
import framework.database.ConnectionManager;
import framework.enums.Environment;
import framework.integrations.ftp.FTPConnection;
import framework.logger.RegressionLogger;
import org.apache.commons.dbutils.QueryRunner;

import java.util.HashMap;
import java.util.Locale;


abstract public class Application {
    protected Environment environment;
    protected RegressionLogger logger;
    protected HashMap<String, String> storage = new HashMap<>();
    protected Faker faker = new Faker(Locale.US);

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
        return new FTPConnection(this, host,username,password).openConnection();
    }

    public HashMap<String, String> getStorage() {
        return storage;
    }

    public Faker getFaker() {
        return faker;
    }
}
