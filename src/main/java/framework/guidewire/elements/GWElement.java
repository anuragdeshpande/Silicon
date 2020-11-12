package framework.guidewire.elements;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.guidewire.ErrorMessageOnScreenException;
import framework.guidewire.GuidewireInteract;
import framework.guidewire.pages.GWIDs;
import framework.utils.PropertiesFileLoader;
import framework.webdriver.BrowserFactory;
import framework.webdriver.PauseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Properties;

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

    @Override
    public void click() {
        PauseTest.waitForPageToLoad();
        if (!this.isPresent()) {
            Assert.fail("Element is not Clickable");
        }

        try {
            this.getElement().click();
            PauseTest.waitForPageToLoad();
        } catch (ElementClickInterceptedException cie) {
            PauseTest.waitForPageToLoad();
        }
    }

    private void closeWarningWindow() {
        // Making sure page is ready for taking input
        GuidewireInteract.clickQuickJump();

        // Checking if warning window appears
        try {
            PauseTest.createSpecialInstance(2, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("southPanel")), "Checking if warning window appears");
        } catch (Exception e) {
            // do nothing. just the south panel did not show up. so no warning windows.
        }

        if (GuidewireInteract.hasErrorMessageOnScreen()) {
            GWElement closeButton = new GWElement(new Identifier(By.linkText("Close")), ReactionTime.IMMEDIATE);
            GWElement clearButton = new GWElement(new Identifier(By.linkText("Clear")), ReactionTime.IMMEDIATE);
            if (closeButton.isPresent()) {
                closeButton.getElement().click();
                this.getElement().click();
            }

            if (clearButton.isPresent()) {
                clearButton.getElement().click();
                this.getElement().click();
            }
        }
    }

    private void braceForRaceCondition() {
        GuidewireInteract.clickQuickJump();
        try{
            PauseTest.createSpecialInstance(1, 10).until(ExpectedConditions.visibilityOfElementLocated(GWIDs.ERROR_MESSAGE.getReference()), "Checking for any error messages");
            // Race Condition Check
            if (GuidewireInteract.hasErrorMessageOnScreen() && GuidewireInteract.getErrorMessageFromScreen().trim().equalsIgnoreCase("Your data change could not be made because another user already changed the data. Please cancel out of the wizard and create your check again.")) {
                System.out.println("#################### Race Condition: Retrying the click #########################");
                int timeoutCounter = 10;
                while (GuidewireInteract.hasErrorMessageOnScreen() && timeoutCounter > 0) {
                    timeoutCounter--;
                    this.getElement().click();
                    PauseTest.createInstance().until(ExpectedConditions.invisibilityOfElementLocated(GWIDs.ERROR_MESSAGE.getReference()), "Checking for any error messages");
                }
            }
        } catch (TimeoutException tme){
            // no error message - did not run into race condition
        }


    }

    private void closeWarningWindowWhenAppears() {

    }
}
