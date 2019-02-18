package framework.elements.alertwindow;

import framework.elements.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UIConfirmationWindow extends UIElement implements IUIConfirmationWindow {

    private WebElement element;

    public UIConfirmationWindow(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public void clickOkButton() {
        this.element.findElement(By.linkText("OK")).click();
    }

    @Override
    public void clickCancelButton() {
        this.element.findElement(By.linkText("Cancel")).click();
    }

    @Override
    public void clickButtonWithText(String buttonText) {
        this.element.findElement(By.linkText(buttonText)).click();
    }
}
