package framework.webdriver;

import framework.constants.ReactionTime;
import framework.guidewire.GuidewireInteract;
import framework.guidewire.PortalInteract;
import org.openqa.selenium.WebDriver;

public class BrowserFactory {

    public static synchronized Interact getCurrentBrowser() {
        return new Interact(ThreadFactory.getInstance().getDriver());
    }

    public static synchronized GuidewireInteract getCurrentGuidewireBrowser() {
        return new GuidewireInteract(ThreadFactory.getInstance().getDriver());
    }

    public static synchronized PortalInteract getCurrentPortalsBrowser(){
        return new PortalInteract(ThreadFactory.getInstance().getDriver());
    }

    public static synchronized void closeCurrentBrowser() {
        ThreadFactory.getInstance().closeBrowser();
    }

    public static synchronized void closeAllWindows(){
        ThreadFactory.getInstance().closeAllBrowserWindows();
    }

    public static synchronized void reloadDriver(){
        WebDriver driver = getCurrentBrowser().getDriver();
        ReactionTime reactionTime = DriverFactory.getReactionTime();
        driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
    }

    public static synchronized void changeImplicitWaitTo(ReactionTime reactionTime){
        WebDriver driver = getCurrentBrowser().getDriver();
        driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
    }


}
