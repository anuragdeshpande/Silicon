package framework.elements.textbox;

import framework.elements.UIElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class UITextbox extends UIElement implements IUITextOperations {

    private WebElement element;

    public UITextbox(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public void fill(String value) {
        this.element.clear();
        this.element.sendKeys(value);
        this.element.sendKeys(Keys.TAB);
    }

    @Override
    public void fillIfEmpty(String value) {
        String fieldValue = this.element.getAttribute("value");
        if (fieldValue.equalsIgnoreCase("<none>") || fieldValue.equalsIgnoreCase("")) {
            fill(value);
        }
    }

    @Override
    public String screenGrab() {
        return this.element.getAttribute("value");
    }
}
