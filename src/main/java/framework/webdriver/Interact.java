package framework.webdriver;

import framework.elements.Identifier;
import framework.elements.radiobutton.UIRadioButton;
import framework.elements.ui_element.UIElement;
import framework.elements.alertwindow.UIConfirmationWindow;
import framework.elements.checkbox.UICheckbox;
import framework.elements.selectbox.UISelectBox;
import framework.elements.table.UITable;
import framework.elements.textbox.UITextbox;
import framework.webdriver.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

//    public UITable withTable(Identifier identifier) {
////        return new UITable(identifier);
////    }

    public UITextbox withTexbox(Identifier identifier) {
        return new UITextbox(identifier);
    }

    public UITextbox withTextArea(Identifier identifier) {
        return new UITextbox(identifier);
    }

    public UISelectBox withSelectBox(Identifier identifier) {
        throw new NotImplementedException();
    }
    public UISelectBox withOptionalSelectBox(Identifier identifier) {
        throw new NotImplementedException();
    }

    public UIElement withElement(Identifier identifier) {
        return new UIElement(identifier);
    }

    public UIElement withOptionalElement(Identifier identifier) {
        return new UIElement(identifier, true);
    }

    public void waitUntilElementVisible(Identifier identifier, int waitSeconds) {
        WebElement element = (new WebDriverWait(driver, waitSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(identifier.getReference()));
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

    public void pressKeys(CharSequence... keys){
        Actions actions = BrowserFactory.getCurrentGuidewireBrowser().getActions();
        if(this.driver != null){
           actions = new Actions(this.driver);
        }

        actions.sendKeys(Keys.chord(keys)).perform();
    }
}
