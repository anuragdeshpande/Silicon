package framework.elements.alertwindow;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UIConfirmationWindow extends UIElement implements IUIConfirmationWindow {

    public UIConfirmationWindow(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void clickOkButton() {
        this.getElement().findElement(By.linkText("OK")).click();
    }

    @Override
    public void clickCancelButton() {
        this.getElement().findElement(By.linkText("Cancel")).click();
    }

    @Override
    public void clickButtonWithText(String buttonText) {
        this.getElement().findElement(By.linkText(buttonText)).click();
    }
}
