package framework.applications.gw;

import framework.enums.Environment;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;

public interface GWOperations {
    void login(String userName, String password);

    void openBatchServer();

    void startBatch(String batchName);

    void openEnvironment(Environment environment);

    GuidewireInteract getInteractObject();

    boolean hasErrorMessageOnScreen();

    String getErrorMessageOnScreen();
}
