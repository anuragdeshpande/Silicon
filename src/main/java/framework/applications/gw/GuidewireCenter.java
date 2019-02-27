package framework.applications.gw;

import framework.enums.Environment;
import framework.guidewire.GuidewireInteract;
import framework.guidewire.pages.GWIDs;
import framework.webdriver.BrowserFactory;
import org.testng.Assert;

abstract public class GuidewireCenter implements GWOperations{
    @Override
    public void openEnvironment(Environment environment) {
        BrowserFactory.getNewChromeBrowser().getDriver().get(environment.getEnvironmentUrl());
    }

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
}
