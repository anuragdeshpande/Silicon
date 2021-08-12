package framework.webdriver;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.checkbox.UICheckbox;
import framework.elements.radiobutton.UIRadioButton;
import framework.elements.selectbox.UISelectBox;
import framework.elements.textbox.UITextbox;
import framework.elements.ui_element.UIElement;
import framework.webdriver.interactionContracts.*;
import framework.webdriver.utils.BrowserStorageAccess;
import framework.webdriver.utils.BrowserWindowManipulator;
import framework.webdriver.utils.DOMManipulator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Optional;

public class Interact
        implements ICanGetDriver,
        ICanGetSeleniumActions,
        ICanInteractWithElement<UIElement>,
        ICanInteractWithRadioButton<UIRadioButton>,
        ICanInteractWithTextBox<UITextbox>,
        ICanInteractWithTextArea<UITextbox>,
        ICanInteractWithSelectBox<UISelectBox>,
        ICanInteractWithCheckbox<UICheckbox>,
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

    public BrowserWindowManipulator withWindow(){
        return new BrowserWindowManipulator();
    }

    @Override
    public UIRadioButton withRadioButton(Identifier identifier) {
        return new UIRadioButton(identifier);
    }

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
    public UISelectBox withSelectBox(Identifier identifier) {
        return new UISelectBox(identifier);
    }

    @Override
    public UISelectBox withOptionalSelectBox(Identifier identifier, ReactionTime reactionTime) {
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

    public Alert withAlertWindow(){
        return getDriver().switchTo().alert();
    }

    public Optional<Alert> withOptionalAlertWindow(ReactionTime reactionTime){
        try{
            PauseTest.createSpecialInstance(reactionTime.getTime()).until(ExpectedConditions.alertIsPresent(), "Waiting for alert windows if any");
            return Optional.of(getDriver().switchTo().alert());
        } catch (NoAlertPresentException | TimeoutException e){
            return Optional.empty();
        }
    }
}
