package framework.guidewire.elements.gw_table;

import framework.elements.checkbox.UICheckbox;
import framework.elements.selectbox.UISelect;
import framework.elements.selectbox.UISelectBox;
import framework.elements.textbox.UITextbox;
import framework.elements.ui_element.UIElement;
import framework.guidewire.elements.gw_radio_button.GWRadioButton;
import framework.webdriver.BrowserFactory;
import org.apache.commons.lang3.NotImplementedException;
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
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    public void clickLink() {
        WebElement linkElement = element.findElement(By.tagName("a"));
        String linkText = linkElement.getText();
        linkElement.click();
//        System.out.println("Clicked on link: "+linkText);
    }

    public UITextbox getTextBox(){
        WebElement element = this.getElement().findElement(By.cssSelector("input[type='text']"));
        return new UITextbox(element);
    }

    public UISelect getSelectBox(){
        WebElement element = this.getElement().findElement(By.tagName("select"));
        return new UISelectBox(element);
    }

    public GWRadioButton getRadios(){
        return new GWRadioButton(this.getElement());
    }


}
