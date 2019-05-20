package framework.applications.gw;

import framework.applications.Application;
import framework.enums.LogLevel;
import framework.guidewire.GuidewireInteract;
import framework.guidewire.pages.GWIDs;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    @Override
    public void setLogLevel(String loggerName, LogLevel logLevel){
        ServerPages serverPages = openServerPages();
        serverPages.clickSetLogLevel();
        GuidewireInteract interact = getInteractObject();
        interact.withSelectBox(GWIDs.ServerPages.ServerTools.LogLevel.LOGGERS).select(loggerName);
        interact.withSelectBox(GWIDs.ServerPages.ServerTools.LogLevel.LEVELS).select(logLevel.name());
        if(!interact.withOptionalElement(GWIDs.ServerPages.ServerTools.LogLevel.CURRENT_LEVEL).isPresent()){
            interact.withElement(GWIDs.ServerPages.ServerTools.SET_LOG_LEVEL).click();
        } else {
            System.out.println("System already at "+logLevel.name()+" for Logger: "+ loggerName);
        }

        serverPages.returnToCenter();

    }

    @Override
    public ServerPages openServerPages() {
        GuidewireInteract interact = getInteractObject();
        interact.pressKeys(Keys.ALT, Keys.SHIFT, "T");
        return new ServerPages(this);
    }
}
