package framework.webdriver;

import framework.constants.ReactionTime;
import framework.guidewire.PortalInteract;
import framework.guidewire.pages.GWIDs;
import framework.logger.RegressionLogger;
import framework.webdriver.utils.BrowserStorageAccess;
import framework.webdriver.utils.WaitConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

// TODO clean up class and remove GWCore support
public class PauseTest {

    private synchronized static WebDriver initiateDriver(final ReactionTime reactionTime) {
        final WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
        driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
        return driver;
    }

    private synchronized static WebDriver initiateDriver(final WebDriver driver, final ReactionTime reactionTime) {
        driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
        return driver;
    }

    /**
     * Utility method to create a standard WebdriverWait instance
     * Remember to call BrowserFactory.reloadDriver() method after this use
     *
     * @param timeOutInSeconds              timeout after which lookup is terminated
     * @param pollingIntervalInMilliseconds how frequently do you want to check for the waiting condition
     * @return Standard Webdriver wait object
     */
    public synchronized static WaitConditions createSpecialInstance(final long timeOutInSeconds, final long pollingIntervalInMilliseconds) {
        final WebDriver webDriver = initiateDriver(ReactionTime.IMMEDIATE);
        return new WaitConditions(webDriver, timeOutInSeconds, pollingIntervalInMilliseconds);
    }

    /**
     * Utility method to create a standard WebdriverWait instance
     * Remember to call BrowserFactory.reloadDriver() method after this use
     *
     * @param timeOutInSeconds timeout after which the lookup is terminated
     * @return standard Webdriver wait object
     */
    public synchronized static WaitConditions createSpecialInstance(final long timeOutInSeconds) {
        final WebDriver webDriver = initiateDriver(ReactionTime.IMMEDIATE);
        return new WaitConditions(webDriver, timeOutInSeconds, 10);
    }

    /**
     * Utility method to create a standard WebdriverWait instance, with a timeout of upto 10 seconds and polling every 100 milliseconds
     * to check for the wait condition
     * <p>
     * Remember to call BrowserFactory.reloadDriver() method after this use
     *
     * @return Standard WebdriverWait Object.
     */
    public synchronized static WaitConditions createInstance() {
        final WebDriver webDriver = initiateDriver(ReactionTime.IMMEDIATE);
        return new WaitConditions(webDriver, 10, 100);
    }


    /**
     * It has been assumed that a page is loaded when the page load animation is gone and
     * quick jump box is clickable.
     * <p>
     * Utility method to create the wait loop to check for the loading animation to go away
     * and for the quick jump element to be clickable
     */
    public synchronized static void waitForPageToLoad() {
        _waitForPageToLoad(ReactionTime.getInstance(10, TimeUnit.SECONDS), "This message should not show", false);
    }


    /**
     * It has been assumed that a page is loaded when the page load animation is gone and
     * quick jump box is clickable.
     * <p>
     * Utility method to create the wait loop to check for the loading animation to go away
     * and for the quick jump element to be clickable
     *
     * @param messageToShowWhileWaiting Message to show while waiting
     */
    public synchronized static void waitForPageToLoad(final String messageToShowWhileWaiting) {
        _waitForPageToLoad(ReactionTime.getInstance(10, TimeUnit.SECONDS), messageToShowWhileWaiting, true);
    }


    /**
     * It has been assumed that a page is loaded when the page load animation is gone and
     * quick jump box is clickable.
     * <p>
     * Utility method to create the wait loop to check for the loading animation to go away
     * and for the quick jump element to be clickable
     *
     * @param timeToWait {@link ReactionTime} time to wait for page load to complete
     */
    public synchronized static void waitForPageToLoad(final ReactionTime timeToWait) {
        _waitForPageToLoad(timeToWait, "This message should not show", false);
    }

    /**
     * It has been assumed that a page is loaded when the page load animation is gone and
     * quick jump box is clickable.
     * <p>
     * Utility method to create the wait loop to check for the loading animation to go away
     * and for the quick jump element to be clickable
     *
     * @param timeToWait                {@link ReactionTime} time to wait for page load to complete
     * @param messageToShowWhileWaiting Message to show while waiting
     */
    public synchronized static void waitForPageToLoad(final ReactionTime timeToWait, final String messageToShowWhileWaiting) {
        _waitForPageToLoad(ReactionTime.getInstance(timeToWait.getTime(), TimeUnit.SECONDS), messageToShowWhileWaiting, true);
    }


    /**
     * Adds a timer to the page and waits until the timer runs out.
     * Timer takes foreground control on the thread, meaning nothing is active while the timer is running.
     *
     * @param timeoutInSeconds Time to wait in seconds
     */
    public synchronized static void startWaitTimer(final int timeoutInSeconds) {
        long elapsedTime = 0;
        final long startTime = System.currentTimeMillis();
        final Interact interact = BrowserFactory.getCurrentBrowser();
        interact.withDOM().injectInfoMessage("Starting timer for " + timeoutInSeconds + " Seconds");
        RegressionLogger.getTestLogger().info("Starting timer for " + timeoutInSeconds + " seconds");
        while (elapsedTime < timeoutInSeconds) {
            if (startTime + (elapsedTime * 1000) <= System.currentTimeMillis()) {
                interact.withDOM().injectWarningMessage("Waiting for timer to run out in " + (timeoutInSeconds - elapsedTime) + " Seconds");
                elapsedTime += 1;
            }
        }
        interact.withDOM().clearBannerMessage();
    }


    private synchronized static void _waitForPageToLoad(final ReactionTime reactionTime, final String messageToShowWhileWaiting, final boolean showMessage) {
        final WaitConditions waitConditions = PauseTest.createSpecialInstance(reactionTime.getTime())
                .showMessage(showMessage);
        try {
            String applicationSystem = "default";
            try {
                applicationSystem = BrowserStorageAccess.getInstance().get("ApplicationSystem").toString().toLowerCase(Locale.ROOT);
            } catch (final NullPointerException npe) {
                // do nothing it is defaulted to guidewire.
            }
            switch (applicationSystem) {
                case "portals":
                    PortalInteract.waitForPageLoad();
                    break;
                case "guidewire":
                default:
                    waitConditions
                            .until(ExpectedConditions.and(
                                    ExpectedConditions.attributeToBe(By.id("gw-click-overlay"), "class", "gw-click-overlay"),
                                    ExpectedConditions.elementToBeClickable(GWIDs.QUICK_JUMP.getReference())
                            ), messageToShowWhileWaiting);
            }

        } catch (final TimeoutException te) {
            if (!waitConditions.shouldSkipTimeout()) {
                throw te;
            }
        }

    }
}
