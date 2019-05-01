package framework.guidewire.gw8.elements;

import framework.elements.Identifier;
import framework.elements.enums.ElementType;
import framework.elements.ui_element.UIElement;
import framework.guidewire.pages.GWIDs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class GWElement extends UIElement {
    public GWElement(Identifier identifier) {
        super(identifier);
    }

    public GWElement(Identifier identifier, boolean isOptional) {
        super(identifier, isOptional);
    }

    protected GWElement(WebElement element) {
        super(element);
    }

    @Override
    public void click() {
        if (this.isPresent()) {
            this.getElement().click();
            System.out.println("Clicked Element");
            if (elementType == ElementType.BUTTON) {
                UIElement uiElement = new UIElement(GWIDs.ERROR_MESSAGE, true);
                if (uiElement.isPresent()) {
                    UIElement closeButton = new UIElement(new Identifier(By.linkText("Close"), ElementType.BUTTON), true);
                    if (closeButton.isPresent()) {
                        closeButton.click();
                        this.getElement().click();
                    } else {
                        Assert.fail("Cannot go to Next Screen: " + uiElement.getElement().getText());
                    }
                }
            }
        } else {
            Assert.fail("Element is not Clickable");
        }
    }
}
