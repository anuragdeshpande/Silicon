package framework.elements.textbox;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class UITextbox extends UIElement implements IUITextOperations {

    public UITextbox(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void fill(String value) {
        this.getElement().sendKeys(Keys.chord(Keys.CONTROL + "a"));
        this.getElement().sendKeys(value);
        this.getElement().sendKeys(Keys.TAB);
    }

    @Override
    public void fillIfEmpty(String value) {
        String fieldValue = this.getElement().getAttribute("value");
        if (fieldValue.equalsIgnoreCase("<none>") || fieldValue.equalsIgnoreCase("")) {
            fill(value);
        }
    }

    @Override
    public String screenGrab() {
        return this.getElement().getAttribute("value");
    }
}
