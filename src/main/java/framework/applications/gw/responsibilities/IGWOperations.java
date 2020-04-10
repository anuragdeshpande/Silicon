package framework.applications.gw.responsibilities;

import framework.applications.gw.ServerPages;
import framework.enums.LogLevel;
import framework.environmentResolution.Environment;
import framework.guidewire.GuidewireInteract;

public interface IGWOperations {
    void login(String userName, String password);

    void logout();

    void openEnvironment(Environment environment);

    void openDefaultEnvironment();

    GuidewireInteract getInteractObject();

    void overrideEnvironmentURL(String url);
}
