package framework.elements.alertwindow;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.By;

public class UIConfirmationWindow extends UIElement implements IUIConfirmationWindow {

    /**\
     *
     * Use of this constructor automatically assumes the Confirmation Window in question is
     * optional and will wait upto the reaction time passed as parameter
     *
     * @param identifier element Identifier
     * @param reactionTime time to wait
     *
     *
     */
    public UIConfirmationWindow(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }

    @Override
    public void clickOkButton() {
        System.out.println("Clicking OK Button");
        this.getElement().findElement(By.linkText("OK")).click();
    }

    @Override
    public void clickCancelButton() {
        System.out.println("Clicking Cancel Button");
        this.getElement().findElement(By.linkText("Cancel")).click();
    }

    @Override
    public void clickButtonWithText(String buttonText) {
        System.out.println("Clicking button with text: "+buttonText);
        this.getElement().findElement(By.linkText(buttonText)).click();
    }
}
