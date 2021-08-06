package framework.applications;

import com.github.javafaker.Faker;
import framework.database.ConnectionManager;
import framework.environmentResolution.Environment;
import framework.integrations.ftp.FTPConnection;
import framework.logger.RegressionLogger;
import org.apache.commons.dbutils.QueryRunner;

import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;


abstract public class Application {
    protected Environment environment;
    private HashMap<String, String> storage = new HashMap<>();
    private HashMap<String, Object> objectStorage = new HashMap<>();
    private final Faker faker = new Faker(Locale.US);
    private final UUID applicationIdentifier;

    public Application(){
        this.applicationIdentifier = UUID.randomUUID();
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

    public HashMap<String, Object> getObjectStorage(){
        return objectStorage;
    }

    public Faker getFaker() {
        return faker;
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

    public void setStorage(HashMap<String, String> storage) {
        this.storage = storage;
    }

    public void setObjectStorage(HashMap<String, Object> objectStorage) {
        this.objectStorage = objectStorage;
    }

    public UUID getApplicationUUID() {
        return applicationIdentifier;
    }
}
