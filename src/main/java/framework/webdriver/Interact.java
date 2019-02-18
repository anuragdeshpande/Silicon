package framework.webdriver;

import framework.elements.Identifier;
import framework.elements.UIElement;
import framework.elements.alertwindow.UIConfirmationWindow;
import framework.elements.checkbox.UICheckbox;
import framework.elements.selectbox.UISelectBox;
import framework.elements.table.UITable;
import framework.elements.textbox.UITextbox;
import framework.webdriver.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class Interact {

    private WebDriver driver;
    private WaitUtils waitUtils;
    private boolean isGuidewire;

    public boolean isGuidewire() {
        return isGuidewire;
    }

    public void setGuidewire(boolean guidewire) {
        isGuidewire = guidewire;
    }

    public Interact(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(this.driver);
    }

    public Actions getActions() {
        return new Actions(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }



    public UITable withTable(Identifier identifier) {
        WebElement element = findElement(identifier);
        if (!element.getTagName().equalsIgnoreCase("table")) {
            element = element.findElements(By.tagName("table")).get(0);
        }
        return new UITable(element, identifier);
    }

    public UITextbox withTexbox(Identifier identifier) {
        WebElement element = findElement(identifier);
        return new UITextbox(element);
    }

    public UISelectBox withSelectBox(Identifier identifier) {
        throw new NotImplementedException();
    }
    public UISelectBox withOptionalSelectBox(Identifier identifier) {
        throw new NotImplementedException();
    }

    public UIElement withElement(Identifier identifier) {
        WebElement element = null;
        try {
            element = findElement(identifier);
        } catch (TimeoutException t) {
            System.out.println("Element Not found.  Returning shell element.");
        }
        return new UIElement(element);
    }

    public UIElement withOptionalElement(Identifier identifier) {
        WebElement element = null;
        try {
            element = findOptional(identifier);
        } catch (TimeoutException t) {
            System.out.println("Element Not found.  Returning shell element.");
        }
        return new UIElement(element);
    }

    public void waitUntilElementVisible(Identifier identifier, int waitSeconds) {
        WebElement element = (new WebDriverWait(driver, waitSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(identifier.getReference()));
    }

    public UICheckbox withCheckbox(Identifier identifier) {
        WebElement element = findElement(identifier);
        return new UICheckbox(element);
    }

    public UIConfirmationWindow withConfirmationWindow() {
        WebElement element = findElement(new Identifier(By.cssSelector("div[id*='messagebox-']")));
        return new UIConfirmationWindow(element);
    }

    protected WebElement findElement(Identifier identifier) {

        WebElement element = null;

        try {
            element = waitUtils.waitUntilElementIsVisible(identifier.getReference(), 10);
        } catch (TimeoutException t) {
            try {
                element = waitUtils.waitUntilElementIsClickable(identifier.getReference(), 20);
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
        return element;
    }

    protected WebElement findOptional(Identifier identifier) {

        WebElement element = null;

        try {
            element = waitUtils.waitUntilElementIsVisible(identifier.getReference(), 1);
        } catch (TimeoutException t) {
            try {
                element = waitUtils.waitUntilElementIsClickable(identifier.getReference(), 1);
            } catch (TimeoutException e) {
                System.out.println("Optional Element Not Found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
        return element;
    }

    protected List<WebElement> findOptionalElements(Identifier identifier) {

        List<WebElement> elements = null;

        try {
            elements = waitUtils.waitUntilElementsAreVisible(identifier.getReference(), 1);
        } catch (TimeoutException t) {
            try {
                elements = waitUtils.waitUntilElementsAreVisible(identifier.getReference(), 1);
            } catch (TimeoutException e) {
                System.out.println("Optional Element Not Found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
        return elements;
    }
}
