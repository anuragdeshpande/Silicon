package framework.guidewire.elements;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.guidewire.ErrorMessageOnScreenException;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.PauseTest;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
            this.getElement().click();
        }

        if(GuidewireInteract.hasErrorMessageOnScreen(ReactionTime.IMMEDIATE) && !identifier.shouldCheckForWarning()){
            Optional<List<String>> errorMessagesFromScreen = GuidewireInteract.getErrorMessageFromScreen(ReactionTime.IMMEDIATE);
            errorMessagesFromScreen.ifPresent(strings -> {
                throw new ErrorMessageOnScreenException(Arrays.toString(strings.toArray()));
            });
        }
    }
}
