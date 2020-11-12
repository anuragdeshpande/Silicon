package framework.webdriver;

import framework.constants.ReactionTime;
import framework.guidewire.pages.GWIDs;
import framework.webdriver.utils.WaitConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

// TODO clean up class and remove GWCore support
public class PauseTest {

    private synchronized static WebDriver initiateDriver(ReactionTime reactionTime){
        WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
        driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
        return driver;
    }

    private synchronized static WebDriver initiateDriver(WebDriver driver, ReactionTime reactionTime){
        driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
        return driver;
    }

    /**
     * Utility method to create a standard WebdriverWait instance
     * Remember to call BrowserFactory.reloadDriver() method after this use
     * @param timeOutInSeconds timeout after which lookup is terminated
     * @param pollingIntervalInMilliseconds how frequently do you want to check for the waiting condition
     * @return Standard Webdriver wait object
     */
    public synchronized static WaitConditions createSpecialInstance(long timeOutInSeconds, long pollingIntervalInMilliseconds){
        WebDriver webDriver = initiateDriver(ReactionTime.IMMEDIATE);
        return new WaitConditions(webDriver, timeOutInSeconds, pollingIntervalInMilliseconds);
    }

    /**
     * Utility method to create a standard WebdriverWait instance
     * Remember to call BrowserFactory.reloadDriver() method after this use
     * @param timeOutInSeconds timeout after which the lookup is terminated
     * @return standard Webdriver wait object
     */
    public synchronized static WaitConditions createSpecialInstance(long timeOutInSeconds){
        WebDriver webDriver = initiateDriver(ReactionTime.IMMEDIATE);
        return new WaitConditions(webDriver,timeOutInSeconds, 10);
    }
    /**
     * Utility method to create a standard WebdriverWait instance, with a timeout of upto 10 seconds and polling every 100 milliseconds
     * to check for the wait condition
     *
     * Remember to call BrowserFactory.reloadDriver() method after this use
     * @return Standard WebdriverWait Object.
     */
    public synchronized static WaitConditions createInstance(){
        WebDriver webDriver = initiateDriver(ReactionTime.IMMEDIATE);
        return new WaitConditions(webDriver,10, 100);
    }

    /**
     * It has been assumed that a page is loaded when the page load animation is gone and
     * quick jump box is clickable.
     *
     * Utility method to create the wait loop to check for the loading animation to go away
     * and for the quick jump element to be clickable
     */
    public synchronized static void waitForPageToLoad(){
        PauseTest.createInstance().until(ExpectedConditions.and(
                ExpectedConditions.attributeToBe(By.id("gw-click-overlay"), "class", "gw-click-overlay"),
                ExpectedConditions.elementToBeClickable(GWIDs.QUICK_JUMP.getReference())
        ), "Waiting for page load to complete");
    }
}
