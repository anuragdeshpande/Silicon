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
    private Environment environment;
    private HashMap<String, String> storage = new HashMap<>();
    private Faker faker = new Faker(Locale.US);

    public Application(){

    }

    protected QueryRunner connectToDB() {
        return ConnectionManager.getDBConnectionTo(this.environment);
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

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public RegressionLogger testLogger(){
        return RegressionLogger.getTestLogger();
    }

    public RegressionLogger testClassLogger(){
        return RegressionLogger.getTestClassLogger();
    }

    public RegressionLogger xmlTestLogger(){
        return RegressionLogger.getXMLTestLogger();
    }
}
