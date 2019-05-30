package framework.webdriver;

import framework.constants.ReactionTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PauseTest {

    private synchronized static WebDriver initiateDriver(ReactionTime reactionTime){
        WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
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
    public synchronized static WebDriverWait createSpecialInstance(long timeOutInSeconds, long pollingIntervalInMilliseconds){
        WebDriver webDriver = initiateDriver(ReactionTime.IMMEDIATE);
        return new WebDriverWait(webDriver,timeOutInSeconds, pollingIntervalInMilliseconds);
    }

    /**
     * Utility method to create a standard WebdriverWait instance
     * Remember to call BrowserFactory.reloadDriver() method after this use
     * @param timeOutInSeconds timeout after which the lookup is terminated
     * @return standard Webdriver wait object
     */
    public synchronized static WebDriverWait createSpecialInstance(long timeOutInSeconds){
        WebDriver webDriver = initiateDriver(ReactionTime.IMMEDIATE);
        return new WebDriverWait(webDriver,timeOutInSeconds);
    }

    /**
     * Utility method to create a standard WebdriverWait instance, with a timeout of upto 10 seconds and polling every 100 milliseconds
     * to check for the wait condition
     *
     * Remember to call BrowserFactory.reloadDriver() method after this use
     * @return Standard WebdriverWait Object.
     */
    public synchronized static WebDriverWait createInstance(){
        WebDriver webDriver = initiateDriver(ReactionTime.IMMEDIATE);
        return new WebDriverWait(webDriver,10, 100);
    }
}
