package framework.applications.gw;

import framework.enums.LogLevel;
import framework.environmentResolution.Environment;
import framework.guidewire.GuidewireInteract;

public interface GWOperations {
    void login(String userName, String password);

    void tempLogin(String userName, String password);

    void logout();

    void openBatchServer();

    void startBatch(String batchName);

    void openEnvironment(Environment environment);

    void openDefaultEnvironment();

    GuidewireInteract getInteractObject();

    boolean hasErrorMessageOnScreen();

    String getErrorMessageOnScreen();

    void overrideEnvironmentURL(String url);

    void setLogLevel(String loggerName, LogLevel logLevel);

    ServerPages openServerPages();
}
