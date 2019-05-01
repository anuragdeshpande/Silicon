package framework.elements.ui_element;

import framework.elements.Identifier;
import framework.elements.enums.ElementType;
import framework.guidewire.pages.GWIDs;
import framework.webdriver.BrowserFactory;
import framework.webdriver.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UIElement implements IUIElementOperations {
    protected WebElement element;
    protected By elementLocation;
    protected boolean isOptional = false;
    protected ElementType elementType;

    public UIElement(Identifier identifier) {
        this.elementLocation = identifier.getReference();
        this.elementType = identifier.getElementType();
    }

    public UIElement(Identifier identifier, boolean isOptional) {
        this.elementLocation = identifier.getReference();
        this.isOptional = isOptional;
    }

    protected UIElement(WebElement element) {
    }

    @Override
    public void click() {

        if (this.isPresent()) {
            this.getElement().click();
            System.out.println("Clicked Element");
        } else {
            Assert.fail("Element is not Clickable");
        }
    }

    @Override
    public void doubleClick() {
        if (this.isPresent()) {
            this.getElement().click();
            this.getElement().click();
            System.out.println("Double Clicked the element");
        } else {
            Assert.fail("Element is not Clickable");
        }
    }

    @Override
    public void hover() {
        BrowserFactory.getCurrentBrowser().getActions().moveToElement(this.getElement(), 1, 1).build().perform();
        System.out.println("Hovering on the element");
    }

    @Override
    public String screenGrab() {
        String clipText = "";
        if (this.getElement() != null) {
            clipText = this.getElement().getText();
        }

        System.out.println("Clipping Screen Text: " + clipText);
        return clipText;
    }

    @Override
    public boolean isPresent() {
        boolean isPresent = this.getElement() != null && this.getElement().isEnabled();
        return isPresent;
    }

    @Override
    public WebElement getElement() {
        try {
            this.element.isEnabled();
            return element;
        } catch (Exception e) {
            return this.isOptional ? findOptional(this.elementLocation) : findElement(this.elementLocation);
        }
    }

    private WebElement findElement(By elementLocation) {
        WaitUtils waitUtils = new WaitUtils(BrowserFactory.getCurrentBrowser().getDriver());
        WebElement element = null;

        try {
            element = waitUtils.waitUntilElementIsVisible(elementLocation, 10);
        } catch (TimeoutException t) {
            try {
                element = waitUtils.waitUntilElementIsClickable(elementLocation, 20);
            } catch (TimeoutException e) {
                e.printStackTrace();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("*** See Stack Trace ***");
        }
        if (!element.isEnabled()) {
            System.out.println("Element is not Enabled");
        }

        this.element = element;
        return element;
    }

    private WebElement findOptional(By elementLocation) {
        WaitUtils waitUtils = new WaitUtils(BrowserFactory.getCurrentBrowser().getDriver());
        WebElement element = null;

        try {
            element = waitUtils.waitUntilElementIsVisible(elementLocation, 1);
        } catch (TimeoutException t) {
            try {
                element = waitUtils.waitUntilElementIsClickable(elementLocation, 1);
            } catch (TimeoutException e) {
                System.out.println("Optional Element: " + elementLocation.toString() + " Not Found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }

        this.element = element;
        return element;
    }


}
