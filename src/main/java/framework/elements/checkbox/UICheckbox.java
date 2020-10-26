package framework.elements.checkbox;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;

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

    @Override
    public void click() {
        Action clickCheckbox = BrowserFactory.getCurrentBrowser().getActions().clickAndHold(this.getElement())
                .moveByOffset(1, 1)
                .release(this.getElement())
                .build();
        clickCheckbox.perform();
//        System.out.println("Clicking Checkbox");
    }

    public boolean isChecked() {
        //        System.out.println("Is Checkbox Checked: "+isChecked);
        return this.getElement().getAttribute("class").contains("checked");
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
