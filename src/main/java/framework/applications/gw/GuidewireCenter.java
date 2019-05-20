package framework.applications.gw;

import framework.applications.Application;
import framework.guidewire.GuidewireInteract;
import framework.guidewire.pages.GWIDs;
import framework.webdriver.BrowserFactory;

abstract public class GuidewireCenter extends Application implements GWOperations {

    protected String overrideEnvironmentURL;

    @Override
    public GuidewireInteract getInteractObject() {
        return BrowserFactory.getCurrentGuidewireBrowser();
    }

    @Override
    public boolean hasErrorMessageOnScreen() {
        String errorMessage = BrowserFactory.getCurrentGuidewireBrowser().withOptionalElement(GWIDs.ERROR_MESSAGE).screenGrab();
        return !errorMessage.equalsIgnoreCase("");
    }

    @Override
    public String getErrorMessageOnScreen() {
        return BrowserFactory.getCurrentGuidewireBrowser().withOptionalElement(GWIDs.ERROR_MESSAGE).screenGrab();
    }

    @Override
    public void overrideEnvironmentURL(String url) {
        this.overrideEnvironmentURL = url;
    }
}
