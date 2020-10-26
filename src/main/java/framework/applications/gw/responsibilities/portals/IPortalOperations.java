package framework.applications.gw.responsibilities.portals;

import framework.environmentResolution.PortalEnvironment;
import framework.guidewire.PortalInteract;
import framework.webdriver.BrowserFactory;

public interface IPortalOperations<T> {
    T login(String username, String password);
    void logout();
    void openEnvironment(PortalEnvironment environment);

    default PortalInteract getInteractObject(){
        return BrowserFactory.getCurrentPortalsBrowser();
    }
}
