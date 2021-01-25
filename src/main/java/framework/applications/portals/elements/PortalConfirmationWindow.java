package framework.applications.portals.elements;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.alertwindow.UIConfirmationWindow;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.By;

public class PortalConfirmationWindow extends UIConfirmationWindow {
    /**
     * <p>
     * Use of this constructor automatically assumes the Confirmation Window in question is
     * optional and will wait upto the reaction time passed as parameter
     *
     */
    public PortalConfirmationWindow() {
    }

    @Override
    public void clickOkButton() {
        clickButtonWithText("Ok");
    }

    @Override
    public void clickCancelButton() {
        clickButtonWithText("Cancel");
    }

    public void clickYes(){
        clickButtonWithText("Yes");
    }

    public void clickNo(){
        clickButtonWithText("No");
    }

    public String getConfirmationWindowMessage(){
        String heading = new UIElement(new Identifier(By.xpath("//*[contains(@class, 'gw-modal-body')]//*[contains(@class, 'gw-heading')]"))).screenGrab();
        String messageBody = new UIElement(new Identifier(By.xpath("//*[contains(@class, 'gw-modal-body')]//*[contains(@class, 'gw-message')]"))).screenGrab();

        return heading+": "+messageBody;
    }

    public void clickButtonWithText(String buttonText) {
        new UIElement(new Identifier(By.xpath("//*[contains(@class, 'gw-modal-footer')]//*[text()='"+buttonText+"']"))).click();
    }
}
