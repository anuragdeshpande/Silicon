package framework.elements.checkbox;

import framework.elements.UIElement;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;

public class UICheckbox extends UIElement implements IUICheckbox {

    private WebElement element;

    public UICheckbox(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public void click() {
        Action clickCheckbox = BrowserFactory.getCurrentBrowser().getActions().clickAndHold(this.element)
                .moveByOffset(1, 1)
                .release(this.element)
                .build();
        clickCheckbox.perform();
    }

    public boolean isChecked() {
        return this.element.getAttribute("class").contains("checked");
    }
}
