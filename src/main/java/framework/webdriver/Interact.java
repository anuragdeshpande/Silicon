package framework.webdriver;

import framework.elements.Identifier;
import framework.elements.alertwindow.UIConfirmationWindow;
import framework.elements.checkbox.UICheckbox;
import framework.elements.radiobutton.UIRadioButton;
import framework.webdriver.utils.WaitUtils;
import org.openqa.selenium.Keys;
import framework.elements.selectbox.UISelect;
import framework.elements.selectbox.UISelectBox;
import framework.elements.textbox.UITextbox;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class Interact {

    private WebDriver driver;
    private boolean isGuidewire;

    public boolean isGuidewire() {
        return isGuidewire;
    }

    public void setGuidewire(boolean guidewire) {
        isGuidewire = guidewire;
    }

    public Interact(WebDriver driver) {
        this.driver = driver;
    }

    public Actions getActions() {
        return new Actions(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public UIRadioButton withRadioButton(Identifier identifier) { return new UIRadioButton(identifier); }

    public UITextbox withTextbox(Identifier identifier) {
        return new UITextbox(identifier);
    }

    public UITextbox withTextArea(Identifier identifier) {
        return new UITextbox(identifier);
    }

    public UISelect withSelectBox(Identifier identifier) {
        return new UISelectBox(identifier);
    }
    public UISelect withOptionalSelectBox(Identifier identifier) {
        return new UISelectBox(identifier);
    }

    public UIElement withElement(Identifier identifier) {
        return new UIElement(identifier);
    }

    public UIElement withOptionalElement(Identifier identifier) {
        return new UIElement(identifier, true);
    }

    public UICheckbox withCheckbox(Identifier identifier) {
        return new UICheckbox(identifier);
    }

    public UIConfirmationWindow withConfirmationWindow() {
        throw new NotImplementedException();
    }

    public UIConfirmationWindow withOptionalConfirmationWindow(){
        throw new NotImplementedException();
    }

    public List<WebElement> withMultiValuedElement(Identifier identifier){
        List<WebElement> elements = new ArrayList<>();
        WaitUtils waitUtils = new WaitUtils(BrowserFactory.getCurrentBrowser().driver);
        try {
            elements = waitUtils.waitUntilElementsAreVisible(identifier.getReference(), 10);
        } catch (TimeoutException t) {
            try {
                elements = waitUtils.waitUntilElementsAreClickable(identifier.getReference(), 20);
            } catch (TimeoutException e) {
                e.printStackTrace();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("*** See Stack Trace ***");
        }

        return elements;
    }

    public void pressKeys(CharSequence... keys){
        Actions actions = BrowserFactory.getCurrentGuidewireBrowser().getActions();
        if(this.driver != null){
           actions = new Actions(this.driver);
        }

        actions.sendKeys(Keys.chord(keys)).build().perform();
    }
}
