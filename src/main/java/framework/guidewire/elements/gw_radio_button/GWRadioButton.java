package framework.guidewire.elements.gw_radio_button;

import framework.constants.ReactionTime;
import framework.customExceptions.IncorrectCallException;
import framework.elements.Identifier;
import framework.elements.radiobutton.UIRadioButton;
import framework.elements.ui_element.UIElement;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GWRadioButton extends UIRadioButton implements IGWRadioButton {

    public GWRadioButton(WebElement element) {
        super(element);
    }

    public GWRadioButton(Identifier identifier) {
        super(identifier);
    }

    public GWRadioButton(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }

    @Override
    public void clickByLabel(String labelText) {
        getElementByLabel(labelText).click();
    }

    @Override
    public void clickByLabel(Identifier identifier, String label) {
        new GWRadioButton(identifier).clickByLabel(label);
    }

    public void clickByValue(String value){
        getElementByValue(value).click();
    }

    public void clickByValue(Identifier identifier, String value){
        new GWRadioButton(identifier).clickByValue(value);
    }


    private UIElement getElementByValue(String labelText) {
        if(getElement().getAttribute("type").equalsIgnoreCase("radio") && getElement().getTagName().equalsIgnoreCase("input")){
            return new UIElement(BrowserFactory.getCurrentGuidewireBrowser().getDriver().findElement(By.xpath("//input[@type='radio'][@name='" + getElement().getAttribute("name") + "'][@value='" + labelText + "']")));
        } else {
            throw new IncorrectCallException("Current element is not of input[type='radio'] please resolve the identifier correctly and try again");
        }
    }

    private UIElement getElementByLabel(String labelText) {
        if(getElement().getAttribute("type").equalsIgnoreCase("radio") && getElement().getTagName().equalsIgnoreCase("input")){
            return new UIElement(BrowserFactory.getCurrentGuidewireBrowser().getDriver().findElement(By.xpath("//input[@type='radio'][@name='" + getElement().getAttribute("name") + "'][@aria-label='" + labelText + "']")));
        } else {
            throw new IncorrectCallException("Current element is not of input[type='radio'] please resolve the identifier correctly and try again");
        }
    }
}
