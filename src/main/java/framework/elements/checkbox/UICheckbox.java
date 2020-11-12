package framework.elements.checkbox;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.WebElement;

public class UICheckbox extends UIElement implements IUICheckbox {

    public UICheckbox(Identifier identifier) {
        super(identifier);
    }

    public UICheckbox(WebElement element){
        super(element);
    }

    public UICheckbox(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }

    public boolean isChecked() {
        return this.getElement().isSelected();
    }

    @Override
    public void mark() {
        if(!isChecked()){
            click();
        }
    }

    @Override
    public void unmark() {
        if(isChecked()){
            click();
        }
    }
}
