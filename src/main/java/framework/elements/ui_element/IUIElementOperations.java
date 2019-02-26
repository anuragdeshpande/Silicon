package framework.elements.ui_element;

import org.openqa.selenium.WebElement;

public interface IUIElementOperations {
    void click();

    void doubleClick();

    void hover();

    String screenGrab();

    boolean isPresent();

    WebElement getElement();
}
