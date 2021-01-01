package framework.webdriver;

import framework.constants.ReactionTime;
import framework.utils.WaitConditions;
import org.openqa.selenium.WebDriver;

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
        return new WaitConditions(webDriver,timeOutInSeconds, pollingIntervalInMilliseconds);
    }

    public synchronized static WaitConditions createSpecialInstance(WebDriver driver, long timeOutInSeconds, long pollingIntervalInMilliseconds){
        WebDriver webDriver = initiateDriver(driver, ReactionTime.IMMEDIATE);
        return new WaitConditions(webDriver,timeOutInSeconds, pollingIntervalInMilliseconds);
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

    public synchronized static WaitConditions createSpecialInstance(WebDriver driver, long timeOutInSeconds){
        WebDriver webDriver = initiateDriver(driver, ReactionTime.IMMEDIATE);
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

    public synchronized static WaitConditions createInstance(WebDriver driver){
        WebDriver webDriver = initiateDriver(driver, ReactionTime.IMMEDIATE);
        return new WaitConditions(webDriver,10, 100);
    }
}
