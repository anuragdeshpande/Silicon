package framework.elements.ui_element;

import org.openqa.selenium.WebElement;

import java.time.LocalDate;

public interface IUIElementOperations {
    void click();

    void doubleClick();

    void hover();

    String screenGrab();

    boolean isPresent();

    WebElement getElement();


    // translators
    LocalDate toDate(String pattern);
}
