package framework.applications.gw;

import framework.applications.Application;
import framework.guidewire.GuidewireInteract;
import framework.guidewire.pages.GWIDs;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

abstract public class GuidewireCenter extends Application implements GWOperations {

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
