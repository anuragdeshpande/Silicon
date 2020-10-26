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
import framework.webdriver.interactionContracts.*;
import framework.webdriver.utils.BrowserStorageAccess;
import framework.webdriver.utils.DOMManipulator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.apache.commons.lang3.NotImplementedException;
import java.util.ArrayList;

public class Interact
        implements ICanGetDriver,
        ICanGetSeleniumActions,
        ICanInteractWithElement<UIElement>,
        ICanInteractWithRadioButton<UIRadioButton>,
        ICanInteractWithTextBox<UITextbox>,
        ICanInteractWithTextArea<UITextbox>,
        ICanInteractWithSelectBox<UISelect>,
        ICanInteractWithCheckbox<UICheckbox>,
        ICanInteractWithConfirmationWindow<UIConfirmationWindow>,
        ICanInteractWithDom<DOMManipulator>,
        ICanInteractWithBrowserStorage<BrowserStorageAccess>,
        ICanSendKeyCombinations{

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

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public Actions getActions() {
        return new Actions(driver);
    }

    @Override
    public UIElement withElement(Identifier identifier) {
        return new UIElement(identifier);
    }

    @Override
    public UIElement withOptionalElement(Identifier identifier, ReactionTime reactionTime) {
        return new UIElement(identifier, reactionTime);
    }

    @Override
    public UIRadioButton withRadioButton(Identifier identifier) { return new UIRadioButton(identifier); }

    @Override
    public UIRadioButton withOptionalRadioButton(Identifier identifier, ReactionTime reactionTime) {
        return new UIRadioButton(identifier, reactionTime);
    }

    @Override
    public UITextbox withTextbox(Identifier identifier) {
        return new UITextbox(identifier);
    }

    @Override
    public UITextbox withOptionalTextbox(Identifier identifier, ReactionTime reactionTime) {
        return new UITextbox(identifier, reactionTime);
    }

    @Override
    public UITextbox withTextArea(Identifier identifier) {
        return new UITextbox(identifier);
    }

    @Override
    public UITextbox withOptionalTextArea(Identifier identifier, ReactionTime reactionTime) {
        return new UITextbox(identifier, reactionTime);
    }

    @Override
    public UISelect withSelectBox(Identifier identifier) {
        return new UISelectBox(identifier);
    }

    @Override
    public UISelect withOptionalSelectBox(Identifier identifier, ReactionTime reactionTime) {
        return new UISelectBox(identifier, reactionTime);
    }

    @Override
    public UICheckbox withCheckbox(Identifier identifier) {
        return new UICheckbox(identifier);
    }

    @Override
    public UICheckbox withOptionalCheckbox(Identifier identifier, ReactionTime reactionTime) {
        return new UICheckbox(identifier, reactionTime);
    }

    @Override
    public UIConfirmationWindow withConfirmationWindow() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public UIConfirmationWindow withOptionalConfirmationWindow(ReactionTime reactionTime){
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public DOMManipulator withDOM(){
        return new DOMManipulator();
    }

    @Override
    public BrowserStorageAccess withBrowserStorage(){
        return new BrowserStorageAccess();
    }

    @Override
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
