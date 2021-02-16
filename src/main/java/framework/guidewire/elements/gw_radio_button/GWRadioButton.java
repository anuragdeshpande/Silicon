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

    @Override
    public WebElement getElement() {
        WebElement baseElement = super.getElement();
        if (!baseElement.getTagName().equalsIgnoreCase("input") && this.label != null) {
            baseElement = baseElement.findElement(By.xpath(".//label[text()='" + this.label + "']/preceding-sibling::input[@role='radio']"));
//            System.out.println("Found radio button: "+ baseElement);
            return baseElement;
        }
        if (this.label == null && !baseElement.getTagName().equalsIgnoreCase("input")) {
            throw new NoSuchElementException("Radio label must be defined if the identifier does not reference an <input> tag.");
        } else {
            if (baseElement.getTagName().equalsIgnoreCase("input") && baseElement.getAttribute("role").equalsIgnoreCase("radio")) {
//                System.out.println("Found radio button: "+ baseElement);
                return  baseElement;
            } else {
                throw new NoSuchElementException("Provided <input> identifier did not resolve a radio button.");
            }
        }
    }

    public UIElement getElementByLabel(String nameAttributeOfInputRadio, String labelText) {
        return new UIElement(new Identifier(By.xpath("//input[@type='radio'][@name='" + nameAttributeOfInputRadio + "'][@value='" + labelText + "']")));
    }

    @Override
    public void clickByLabel(String nameAttributeOfInputRadio, String labelText) {
        getElementByLabel(nameAttributeOfInputRadio, labelText).click();
    }
}
