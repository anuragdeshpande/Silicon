package framework.webdriver;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.alertwindow.UIConfirmationWindow;
import framework.elements.checkbox.UICheckbox;
import framework.elements.radiobutton.UIRadioButton;
import framework.elements.selectbox.UISelect;
import framework.elements.selectbox.UISelectBox;
import framework.elements.textbox.UITextbox;
import framework.elements.ui_element.UIElement;
import framework.webdriver.utils.BrowserStorageAccess;
import framework.webdriver.utils.DOMManipulator;
import framework.webdriver.utils.WaitUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    public UISelect withOptionalSelectBox(Identifier identifier, ReactionTime reactionTime) {
        return new UISelectBox(identifier, reactionTime);
    }

    public UIElement withElement(Identifier identifier) {
        return new UIElement(identifier);
    }

    public UIElement withOptionalElement(Identifier identifier, ReactionTime reactionTime) {
        return new UIElement(identifier, reactionTime);
    }

    public UICheckbox withCheckbox(Identifier identifier) {
        return new UICheckbox(identifier);
    }

    public UIConfirmationWindow withConfirmationWindow() {
        throw new NotImplementedException();
    }

    public UIConfirmationWindow withOptionalConfirmationWindow(ReactionTime reactionTime){
        throw new NotImplementedException();
    }

    public DOMManipulator withDOM(){
        return new DOMManipulator();
    }

    public BrowserStorageAccess withBrowserStorage(){
        return new BrowserStorageAccess();
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
        final Actions actions = BrowserFactory.getCurrentGuidewireBrowser().getActions();
        ArrayList<CharSequence> controlModifiers = new ArrayList<>();
        ArrayList<CharSequence> nonModifiers = new ArrayList<>();

        for(CharSequence keyChar: keys){
            if(Keys.ALT == keyChar || Keys.CONTROL == keyChar || Keys.SHIFT == keyChar){
                controlModifiers.add(keyChar);
            } else {
                nonModifiers.add(keyChar);
            }
        }
        controlModifiers.forEach(actions:: keyDown);
        nonModifiers.forEach(actions::sendKeys);
        controlModifiers.forEach(actions:: keyUp);
        actions.build().perform();

    }
}
