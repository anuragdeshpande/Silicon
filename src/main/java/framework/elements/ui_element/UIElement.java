package framework.elements.ui_element;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.webdriver.BrowserFactory;
import framework.webdriver.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UIElement implements IUIElementOperations {
    private WebElement element;
    private boolean isOptional = false;
    protected Identifier identifier;

    // although reference is part of identifier, declaring again for child classes to override if there is a need.
    protected By elementLocation;

    public UIElement(Identifier identifier) {
        this.identifier = identifier;
        this.elementLocation = identifier.getReference();
        this.element = findElement(elementLocation);
    }

    public UIElement(Identifier identifier, ReactionTime reactionTime) {
        this.isOptional = true;
        this.identifier = identifier;
        this.elementLocation = identifier.getReference();
        this.element = findOptional(elementLocation, reactionTime);
    }

    public UIElement(WebElement element) {
        this.element = element;
    }

    @Override
    public void click() {

        if (this.isPresent()) {
            this.getElement().click();
//            System.out.println("Clicked Element: " + this.elementLocation);
        } else {
            Assert.fail("Element is not Clickable");
        }
    }

    @Override
    public void doubleClick() {
        if (this.isPresent()) {
            this.getElement().click();
            this.getElement().click();
//            System.out.println("Double Clicked the element: " + elementLocation);
        } else {
            Assert.fail("Element is not Clickable");
        }
    }

    @Override
    public void hover() {
        BrowserFactory.getCurrentBrowser().getActions().moveToElement(this.getElement(), 1, 1).build().perform();
//        System.out.println("Hovering on the element: " + elementLocation);
    }

    @Override
    public String screenGrab() {
        String clipText = "";
        if (this.getElement() != null) {
            switch (this.element.getTagName().toLowerCase()) {
                case "input":
                case "textarea":
                    clipText = this.element.getAttribute("value");
                    break;
                default:
                    clipText = this.element.getText();
            }
        }

//        System.out.println("Clipping Screen Text: " + clipText);
        return clipText;
    }

    @Override
    public boolean isPresent() {
        return this.getElement() != null;
    }

    @Override
    public WebElement getElement() {
        try {
            this.element.isEnabled();
            return element;
        } catch (Exception e) {
            if(this.elementLocation == null){
                throw new RuntimeException("The original element was a WebElement. Please change implementation to use Identifer from the framework");
            }
            return this.isOptional ? findOptional(elementLocation, ReactionTime.MOMENTARY) : findElement(elementLocation);
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
//                System.out.println("Optional Element: " + elementLocation.toString() + " Not Found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }

        this.element = element;
        return element;
    }

    private WebElement findOptional(By elementLocation, ReactionTime reactionTime) {
        try {
            WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
            driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
            WebElement element = driver.findElement(elementLocation);
            reactionTime = ReactionTime.STANDARD_WAIT_TIME;
            driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());

//            System.out.println("Optional Element found: " + elementLocation);
            this.element = element;
            return element;
        } catch (Exception e) {
            System.out.println("Optional Element not found at location: " + elementLocation);
            return null;
        }
    }

    @Override
    public LocalDate toDate(String pattern) {
        return LocalDate.parse(screenGrab(), DateTimeFormatter.ofPattern(pattern));
    }
}
