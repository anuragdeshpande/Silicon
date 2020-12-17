package framework.utils;

import framework.logger.RegressionLogger;
import framework.webdriver.BrowserFactory;
import framework.webdriver.Interact;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public class WaitConditions {
    private WebDriver driver;
    private long timeoutInSeconds;
    private long pollingIntervalMilliseconds;
    private WebDriverWait wait;

    public WaitConditions(WebDriver driver, long timeoutInSeconds, long pollingIntervalMilliseconds){
        this.driver = driver;
        this.timeoutInSeconds = timeoutInSeconds;
        this.pollingIntervalMilliseconds = pollingIntervalMilliseconds;

        this.wait = new WebDriverWait(driver, timeoutInSeconds);
    }

    public WebDriverWait getWebdriverWait() {
        return wait;
    }

    public <V> V until(Function<? super WebDriver, V> waitCondition, String messageToShowWhileWaiting){
        Interact interact = BrowserFactory.getCurrentBrowser();
        interact.withDOM().injectInfoMessage(messageToShowWhileWaiting);
        try{
            V until = wait.until(waitCondition);
            interact.withDOM().clearBannerMessage();
            return until;
        } catch (TimeoutException toe){
            interact.withDOM().injectDangerMessage("Page did not load in under "+timeoutInSeconds+" seconds");
            RegressionLogger.getTestLogger().captureScreenshot("Page is still loading");
            throw new TimeoutException("Page is still loading");
        }
    }
}
