package framework.guidewire.elements;

import framework.constants.ReactionTime;
import framework.customExceptions.IncorrectCallException;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.guidewire.ErrorMessageOnScreenException;
import framework.guidewire.GuidewireInteract;
import framework.logger.RegressionLogger;
import framework.webdriver.BrowserFactory;
import framework.webdriver.PauseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class GWElement extends UIElement {
    public GWElement(Identifier identifier) {
        super(identifier);
    }

    /**
     * \
     * <p>
     * Use of this constructor automatically assumes the Select Box in question is
     * optional and will wait upto the reaction time passed as parameter
     *
     * @param identifier   element Identifier
     * @param reactionTime time to wait
     */
    public GWElement(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }

    protected GWElement(WebElement element) {
        super(element);
    }

    public boolean isDisabled() {
        String attribute = this.getElement().getAttribute("aria-disabled");
        return attribute != null && attribute.equalsIgnoreCase("true");
    }

    @Override
    public void click() {
        PauseTest.waitForPageToLoad();
        if (!this.isPresent() || this.isDisabled()) {
            if (this.isDisabled()) {
                RegressionLogger.getTestLogger().warn("Element: " + identifier.getFriendlyName() + " is disabled at the moment");
            }
            throw new IncorrectCallException("Element " + identifier.getFriendlyName() + " is not clickable.");
        }

        try {
            this.getElement().click();
            PauseTest.waitForPageToLoad(ReactionTime.getInstance(identifier.getTimeout(), TimeUnit.SECONDS));
        } catch (ElementClickInterceptedException cie) {
            PauseTest.waitForPageToLoad(ReactionTime.getInstance(identifier.getTimeout(), TimeUnit.SECONDS));
            this.getElement().click();
            PauseTest.waitForPageToLoad(ReactionTime.getInstance(identifier.getTimeout(), TimeUnit.SECONDS));
        } catch (UnhandledAlertException uae) {
            Alert alert = BrowserFactory.getCurrentGuidewireBrowser().getDriver().switchTo().alert();
            RegressionLogger.getTestLogger().info("Accepting Alert: " + alert.getText());
            alert.accept();
        }

        if (GuidewireInteract.hasErrorMessageOnScreen(ReactionTime.IMMEDIATE) && !identifier.shouldCheckForWarning()) {
            Optional<List<String>> errorMessagesFromScreen = GuidewireInteract.getErrorMessageFromScreen(ReactionTime.IMMEDIATE);
            errorMessagesFromScreen.ifPresent(strings -> {
                if ((identifier.shouldSafeguardAgainstRaceCondition() && strings.stream().anyMatch(errorMessage -> identifier.getRaceConditionStrings().contains(errorMessage))) ||
                        strings.stream().anyMatch(errorMessage -> errorMessage.toLowerCase(Locale.ROOT).contains("the object you are trying to update"))) {
                    identifier.setIgnoreErrorMessageCheck(true);
                    RegressionLogger.getTestLogger().warn("Encountered simultaneous changes in the system for RaceConditionElementFriendlyName=" + identifier.getFriendlyName() + " RaceConditionElementIdentifier="+identifier.getReferenceValue()+", waiting for 10 seconds before retrying the action again.");
                    PauseTest.startWaitTimer(10);
                    this.getElement().click();
                    identifier.setIgnoreErrorMessageCheck(false);
                    GuidewireInteract.getErrorMessageFromScreen(ReactionTime.IMMEDIATE).ifPresent(strings1 -> {
                        throw new ErrorMessageOnScreenException(Arrays.toString(strings1.toArray()));
                    });
                } else {
                    throw new ErrorMessageOnScreenException(Arrays.toString(strings.toArray()));
                }
            });
        }
    }
}
