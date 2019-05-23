package framework.guidewire.gw8.elements;

import framework.elements.Identifier;
import framework.elements.enums.ElementType;
import framework.elements.ui_element.UIElement;
import framework.guidewire.GuidewireInteract;
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
            if (GuidewireInteract.hasErrorMessageOnScreen()) {
                if (identifier.shouldCheckForWarning()) {
                    UIElement closeButton = new UIElement(new Identifier(By.linkText("Close"), ElementType.BUTTON), true);
                    if (closeButton.isPresent()) {
                        closeButton.click();
                        this.getElement().click();
                    }
                } else {
                    Assert.fail("Error Message On Screen: " + GuidewireInteract.getErrorMessageFromScreen());
                }

            }
        } else {
            Assert.fail("Element is not Clickable");
        }
    }
}
