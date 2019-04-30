package framework.elements.checkbox;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;

public class UICheckbox extends UIElement implements IUICheckbox {

    public UICheckbox(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void click() {
        Action clickCheckbox = BrowserFactory.getCurrentBrowser().getActions().clickAndHold(this.getElement())
                .moveByOffset(1, 1)
                .release(this.getElement())
                .build();
        clickCheckbox.perform();
        System.out.println(Thread.currentThread().getId() + ": Clicking Checkbox");
    }

    public boolean isChecked() {
        boolean isChecked = this.getElement().getAttribute("class").contains("checked");
        System.out.println(Thread.currentThread().getId() + ": Is Checkbox Checked: "+isChecked);
        return isChecked;
    }
}
