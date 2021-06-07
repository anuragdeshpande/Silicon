package framework.guidewire.elements.gw_table;

import framework.elements.checkbox.UICheckbox;
import framework.elements.selectbox.UISelectBox;
import framework.elements.textbox.UITextbox;
import framework.elements.ui_element.UIElement;
import framework.guidewire.elements.gw_radio_button.GWRadioButton;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GWCell extends UIElement  {

    private WebElement element;

    public GWCell(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public void click() {
        element.click();
    }
    public UICheckbox getCheckbox(){
        return new UICheckbox(this.element.findElement(By.cssSelector("input[type='checkbox']")));

    }

    @Override
    public void doubleClick() {
        Actions actions = BrowserFactory.getCurrentGuidewireBrowser().getActions();
        actions.doubleClick(this.element).perform();
        actions.doubleClick(this.element).perform();
    }

    @Override
    public boolean isPresent() {
        return this.element != null;
    }

    @Override
    public String screenGrab() {
        return this.element.getText();
    }

    public void clickSelect() {
        WebElement element = this.element.findElement(By.xpath(".//div[contains(@class, 'gw-label') and contains(., 'Select')] | .//div[contains(@class, 'gw-mini-button')] | .//div[@role='link']"));
        element.click();
    }

    public void clickLink() {
        WebElement linkElement = element.findElement(By.xpath(".//div[@role='button']"));
        linkElement.click();
    }

    public UITextbox getTextBox(){
        WebElement element = this.getElement().findElement(By.cssSelector("input[type='text']"));
        return new UITextbox(element);
    }

    public UISelectBox getSelectBox(){
        WebElement element = this.getElement().findElement(By.tagName("select"));
        return new UISelectBox(element);
    }

    public GWRadioButton getRadios(){
        WebElement element = this.getElement().findElement(By.xpath(".//input[@type='radio']"));
        return new GWRadioButton(element);

    }


}
