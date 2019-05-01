package framework.guidewire.gw8.elements.gw_radio_button;

import framework.elements.Identifier;
import framework.elements.radiobutton.UIRadioButton;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class GWRadioButton extends UIRadioButton implements IGWRadioButton {

    public GWRadioButton(Identifier identifier) {
        super(identifier);
    }

    public GWRadioButton(Identifier identifier, boolean isOptional) {
        super(identifier, isOptional);
    }

    @Override
    public WebElement getElement() {
        WebElement baseElement = super.getElement();
        if (!baseElement.getTagName().equalsIgnoreCase("input") && this.label != null) {
            baseElement = baseElement.findElement(By.xpath(".//label[text()='" + this.label + "']/preceding-sibling::input[@role='radio']"));
            System.out.println("Found radio button: "+ baseElement);
            return baseElement;
        }
        if (this.label == null && !baseElement.getTagName().equalsIgnoreCase("input")) {
            throw new NoSuchElementException("Radio label must be defined if the identifier does not reference an <input> tag.");
        } else {
            if (baseElement.getTagName().equalsIgnoreCase("input") && baseElement.getAttribute("role").equalsIgnoreCase("radio")) {
                System.out.println("Found radio button: "+ baseElement);
                return  baseElement;
            } else {
                throw new NoSuchElementException("Provided <input> identifier did not resolve a radio button.");
            }
        }
    }

    public WebElement getElementByLabel(String labelText) {
        WebElement baseElement = super.getElement();
        if (!baseElement.getTagName().equalsIgnoreCase("input")) {
            System.out.println("Finding <input> in the current dom element "+ baseElement);
            baseElement = baseElement.findElement(By.xpath(".//label[text()='" + labelText + "']/preceding-sibling::input"));
        }

        System.out.println("Found table: "+ baseElement);
        return baseElement;
    }

    @Override
    public void clickByLabel(String labelText) {
        new UIElement(new Identifier(By.xpath(".//label[text()='" + labelText + "']/preceding-sibling::input"))).click();
    }

    @Override
    public void clickYes() {
        clickByLabel("Yes");
    }

    @Override
    public void clickNo() {
        clickByLabel("No");
    }
}
