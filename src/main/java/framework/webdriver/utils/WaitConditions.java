package framework.webdriver.utils;

import framework.enums.FrameworkSystemEvents;
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
    private boolean showMessage = true;
    private boolean skipTimeout;

    public WaitConditions(WebDriver driver, long timeoutInSeconds, long pollingIntervalMilliseconds) {
        this.driver = driver;
        this.timeoutInSeconds = timeoutInSeconds;
        this.pollingIntervalMilliseconds = pollingIntervalMilliseconds;

        this.wait = new WebDriverWait(driver, timeoutInSeconds);
    }

    public WebDriverWait getWebdriverWait() {
        return wait;
    }

    public WaitConditions showMessage(boolean value) {
        this.showMessage = value;
        return this;
    }

    public WaitConditions skipTimeOut(boolean value) {
        this.skipTimeout = value;
        return this;
    }

    public <V> V until(Function<? super WebDriver, V> waitCondition, String messageToShowWhileWaiting) {
        Interact interact = BrowserFactory.getCurrentBrowser();
        if (showMessage) {
            interact.withDOM().injectInfoMessage(messageToShowWhileWaiting);
        }
        int counter = 0;
        do {
            try {
                V until = wait.until(waitCondition);
                interact.withDOM().clearBannerMessage();
                return until;
            } catch (TimeoutException toe) {
                interact.withDOM().injectDangerMessage("Page did not load in under " + timeoutInSeconds + " seconds");
                RegressionLogger.getFirstAvailableLogger().addTag(FrameworkSystemEvents.EXTENDED_WAIT_TIME.getValue());
                RegressionLogger.getFirstAvailableLogger().info("Attempt: " + counter + " Page is still loading. Automatically extending wait time by another " + timeoutInSeconds + " seconds");
            }
            counter++;
        } while (counter <= 10);

        throw new TimeoutException("Page is still loading after " + counter + " attempts of " + timeoutInSeconds + " seconds waits");
    }

    public boolean shouldSkipTimeout() {
        return this.skipTimeout;
    }
}
