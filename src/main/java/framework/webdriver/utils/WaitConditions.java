package framework.webdriver.utils;

import framework.enums.FrameworkSystemEvents;
import framework.events.FrameworkEvents;
import framework.guidewire.pages.GWIDs;
import framework.logger.RegressionLogger;
import framework.logger.eventMessaging.LoggingEvent;
import framework.webdriver.BrowserFactory;
import framework.webdriver.Interact;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public class WaitConditions {
    private final WebDriver driver;
    private final long timeoutInSeconds;
    private final long pollingIntervalMilliseconds;
    private final WebDriverWait wait;
    private boolean showMessage = true;
    private boolean skipTimeout;

    public WaitConditions(final WebDriver driver, final long timeoutInSeconds, final long pollingIntervalMilliseconds) {
        this.driver = driver;
        this.timeoutInSeconds = timeoutInSeconds;
        this.pollingIntervalMilliseconds = pollingIntervalMilliseconds;

        this.wait = new WebDriverWait(driver, timeoutInSeconds);
    }

    public static void main(final String[] args) {
        final ExpectedCondition<WebElement> waitCondition = ExpectedConditions.elementToBeClickable(GWIDs.Login.LOGIN.getReference());
        System.out.println(waitCondition);
    }

    public WebDriverWait getWebdriverWait() {
        return wait;
    }

    public WaitConditions showMessage(final boolean value) {
        this.showMessage = value;
        return this;
    }

    public WaitConditions skipTimeOut(final boolean value) {
        this.skipTimeout = value;
        return this;
    }

    public <V> V until(final Function<? super WebDriver, V> waitCondition, final String messageToShowWhileWaiting) {
        final Interact interact = BrowserFactory.getCurrentBrowser();
        if (showMessage) {
            interact.withDOM().injectInfoMessage(messageToShowWhileWaiting);
        }
        LoggingEvent extendedWaitTimeEvent = null;
        int counter = 0;
        do {
            try {
                final V until = wait.until(waitCondition);
                interact.withDOM().clearBannerMessage();
                return until;
            } catch (final TimeoutException toe) {
                interact.withDOM().injectDangerMessage("Page did not load in under " + timeoutInSeconds + " seconds");
                RegressionLogger.getFirstAvailableLogger().addTag(FrameworkSystemEvents.EXTENDED_WAIT_TIME.getValue());
                if (extendedWaitTimeEvent == null) {
                    extendedWaitTimeEvent = RegressionLogger.getFirstAvailableLogger().startEvent(FrameworkEvents.EXTENDED_TIME);
                }
                RegressionLogger.getFirstAvailableLogger().info("Attempt: " + counter + " Page is still loading. Waiting for condition to be true:" + waitCondition + " Automatically extending wait time by another " + timeoutInSeconds + " seconds");
            }
            counter++;
        } while (counter <= 3);

        if (extendedWaitTimeEvent != null) {
            RegressionLogger.getFirstAvailableLogger().endEvent(extendedWaitTimeEvent, "WaitCondition=\"" + waitCondition + "\"");
        }
        throw new TimeoutException("Page is still loading after " + counter + " attempts of " + timeoutInSeconds + " seconds waits");
    }

    public boolean shouldSkipTimeout() {
        return this.skipTimeout;
    }
}
