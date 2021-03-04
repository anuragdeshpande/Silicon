package framework.elements.textbox;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class UITextbox extends UIElement implements IUITextOperations {

    public UITextbox(WebElement element) {
        super(element);
    }

    public UITextbox(Identifier identifier) {
        super(identifier);
    }

    public UITextbox(Identifier identifier, ReactionTime reactionTime){
        super(identifier, reactionTime);
    }

    @Override
    public void fill(String value) {
        this.getElement().sendKeys(Keys.chord(Keys.CONTROL + "a"));
        this.getElement().sendKeys(value);
        this.getElement().sendKeys(Keys.TAB);
//        System.out.println("Text Filled: "+value);
    }

    @Override
    public void fillIfEmpty(String value) {
        String fieldValue = this.getElement().getAttribute("value");
        if (fieldValue.equalsIgnoreCase("<none>") || fieldValue.equalsIgnoreCase("")) {
            fill(value);
        }
    }
}
