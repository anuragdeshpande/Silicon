package framework.guidewire;

import framework.applications.portals.elements.PortalConfirmationWindow;
import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.checkbox.UICheckbox;
import framework.webdriver.Interact;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class PortalInteract extends Interact {

    public PortalInteract(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isGuidewire() {
        return true;
    }

    @Override
    public void setGuidewire(boolean guidewire) {
        throw new IllegalStateException("Cannot change \"Is Guidewire\" setting for this product. " +
                "The value is always set to true.");
    }

    @Override
    public Actions getActions() {
        return super.getActions();
    }

    @Override
    public WebDriver getDriver() {
        return super.getDriver();
    }

    @Override
    public UICheckbox withCheckbox(Identifier identifier) {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public PortalConfirmationWindow withConfirmationWindow() {
        return new PortalConfirmationWindow(new Identifier(By.xpath("//*[contains(@class, 'gw-modal-body')]//*[contains(@class, 'gw-message')]")),
                ReactionTime.THREE_SECONDS);
    }

    @Override
    public PortalConfirmationWindow withOptionalConfirmationWindow(ReactionTime reactionTime) {
        return new PortalConfirmationWindow(new Identifier(By.xpath("//*[contains(@class, 'gw-modal-body')]//*[contains(@class, 'gw-message')]")),
                reactionTime);
    }

    @Override
    public List<WebElement> withMultiValuedElement(Identifier identifier) {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }
}
