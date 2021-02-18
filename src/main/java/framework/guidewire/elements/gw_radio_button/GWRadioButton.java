package framework.guidewire.elements.gw_radio_button;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.radiobutton.UIRadioButton;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GWRadioButton extends UIRadioButton implements IGWRadioButton {

    public GWRadioButton(Identifier identifier) {
        super(identifier);
    }

    public GWRadioButton(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }

    public UIElement getElementByLabel(String labelText) {
        return new UIElement(new Identifier(By.xpath("//input[@type='radio'][@name='" + getElement().getAttribute("name") + "'][@value='" + labelText + "']")));
    }

    @Override
    public void clickByLabel(String labelText) {
        getElementByLabel(labelText).click();
    }
}
