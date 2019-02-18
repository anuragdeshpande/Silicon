package framework.elements;

import org.openqa.selenium.WebElement;

public interface IUIElementOperations {
    void click();

    void doubleClick();

    void hover();

    String screenGrab();

    boolean isPresent();

    void refreshElement();

    WebElement getElement();
}
