package framework.elements.ui_element;

import framework.constants.ReactionTime;
import framework.customExceptions.ElementNotFoundException;
import framework.customExceptions.NotInitializedException;
import framework.elements.Identifier;
import framework.elements.UninitializedIdentifier;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;
import framework.webdriver.DriverFactory;
import framework.webdriver.PauseTest;
import framework.webdriver.utils.WaitUtils;
import org.openqa.selenium.*;
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
        PauseTest.waitForPageToLoad();
        if(identifier instanceof UninitializedIdentifier){
            throw new NotInitializedException("This element has not yet been migrated. Please get the latest reference from the UI");
        }
        this.identifier = identifier;
        this.elementLocation = identifier.getReference();
        this.element = findElement(identifier);

    }

    public UIElement(Identifier identifier, ReactionTime reactionTime) {
        if(identifier instanceof UninitializedIdentifier){
            throw new NotInitializedException("This element has not yet been migrated. Please get the latest reference from the UI");
        }
        this.isOptional = true;
        this.identifier = identifier;
        this.elementLocation = identifier.getReference();
        this.element = findOptional(identifier, reactionTime);

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
            return this.isOptional ? findOptional(identifier, ReactionTime.MOMENTARY) : findElement(identifier);
        }
    }

    private WebElement findElement(Identifier identifier) {
        WaitUtils waitUtils = new WaitUtils(BrowserFactory.getCurrentBrowser().getDriver());
        WebElement element = null;

        try {
            element = waitUtils.waitUntilElementIsVisible(identifier.getReference(), ((int) (DriverFactory.getReactionTime().getTime()))/2);
        } catch (TimeoutException t) {
            try {
                element = waitUtils.waitUntilElementIsClickable(identifier.getReference(), ((int) (DriverFactory.getReactionTime().getTime()))/2);
            } catch (TimeoutException e) {
                throw new ElementNotFoundException("Element: "+identifier.getFriendlyName()+" was not found after waiting for approx 30 seconds", e);
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


    private WebElement findOptional(Identifier identifier, ReactionTime reactionTime) {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        if(System.getProperty("jenkinsBuildNumber") != null){
            interact.withDOM().injectInfoMessage("Waiting for Optional Element: "+identifier.getFriendlyName()+" for "+reactionTime.getTime()+" "+reactionTime.getTimeUnit().name());
        }
        try {
            WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
            driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
            WebElement element = driver.findElement(identifier.getReference());
            reactionTime = ReactionTime.STANDARD_WAIT_TIME;
            driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());

//            System.out.println("Optional Element found: " + elementLocation);
            this.element = element;
            interact.withDOM().clearBannerMessage();
            return element;
        } catch (Exception e) {
            System.out.println("Optional Element ("+identifier.getFriendlyName()+") not found at location: " + elementLocation);
            if(System.getProperty("jenkinsBuildNumber") != null) {
                interact.withDOM().clearBannerMessage();
            }
            return null;
        }

    }

    @Override
    public LocalDate toDate(String pattern) {
        return LocalDate.parse(screenGrab(), DateTimeFormatter.ofPattern(pattern));
    }

    public void scrollToTop(){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementById(\""+getElement().getAttribute("id")+"\").scrollTop -= document.getElementById(\""+getElement().getAttribute("id")+"\").scrollHeight");
    }

    public void scrollToTop(String id){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementById(\""+id+"\").scrollTop -= document.getElementById(\""+id+"\").scrollHeight");
    }

    public void scrollToBottom(){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementById(\""+getElement().getAttribute("id")+"\").scrollTop += document.getElementById(\""+getElement().getAttribute("id")+"\").scrollHeight");
    }

    public void scrollToBottom(String id){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementById(\""+id+"\").scrollTop += document.getElementById(\""+id+"\").scrollHeight");
    }

    public void scrollIntoView(){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementById(\""+getElement().getAttribute("id")+"\").scrollIntoViewIfNeeded()");
    }

    public void scrollIntoView(String id){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementById(\""+id+"\").scrollIntoViewIfNeeded()");
    }
}
