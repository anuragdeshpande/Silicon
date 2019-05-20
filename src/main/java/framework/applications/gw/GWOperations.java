package framework.applications.gw;

import framework.enums.Environment;
import framework.enums.LogLevel;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;

public interface GWOperations {
    void login(String userName, String password);

    void logout();

    void openBatchServer();

    void startBatch(String batchName);

    void openEnvironment(Environment environment);

    GuidewireInteract getInteractObject();

    boolean hasErrorMessageOnScreen();

    String getErrorMessageOnScreen();

    void overrideEnvironmentURL(String url);

    void setLogLevel(String loggerName, LogLevel logLevel);

    ServerPages openServerPages();
}
