package framework.applications.gw;

import framework.enums.Environment;

public interface GWOperations {
    void login(String userName, String password);
    void openBatchServer();
    void startBatch(String batchName);
    void openEnvironment(Environment environment);
}
