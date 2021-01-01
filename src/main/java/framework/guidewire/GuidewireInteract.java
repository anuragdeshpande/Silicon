package framework.guidewire;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.alertwindow.UIConfirmationWindow;
import framework.elements.ui_element.UIElement;
import framework.guidewire.elements.GWElement;
import framework.guidewire.elements.gw_checkbox.GWCheckBox;
import framework.guidewire.elements.gw_radio_button.GWRadioButton;
import framework.guidewire.elements.gw_select_box.GWMultiSelect;
import framework.guidewire.elements.gw_select_box.GWSelectBox;
import framework.guidewire.elements.gw_table.GWTable;
import framework.guidewire.pages.GWIDs;
import framework.webdriver.BrowserFactory;
import framework.webdriver.Interact;
import framework.webdriver.PauseTest;
import framework.webdriver.utils.WaitUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class GuidewireInteract extends Interact {
    public GuidewireInteract(WebDriver driver) {
        super(driver);
    }

    public void withTabArrow(Identifier identifier) {
        UIElement uiElement = new UIElement(identifier);
        Dimension size = uiElement.getElement().getSize();
        BrowserFactory.getCurrentBrowser().getActions().moveToElement(uiElement.getElement(), (size.getWidth())/2 - 6, 10).click().build().perform();
//        System.out.println("Tab Arrow Clicked: "+identifier.getReference());
    }

    /**
     * this is designed to catch the error messages immediately after clicking.
     * if the error message is shown on the screen with in 10 Milliseconds from the click, the function returns false.
     */
    public static boolean hasErrorMessageOnScreen(){
        try{
            GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
            WebDriver driver = interact.getDriver();
            ReactionTime reactionTime = ReactionTime.MOMENTARY;
            driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
            WebElement errorElement = driver.findElement(GWIDs.ERROR_MESSAGE.getReference());
            reactionTime = ReactionTime.STANDARD_WAIT_TIME;
            driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
            return errorElement != null && errorElement.isEnabled();
        } catch (Exception e){
            return false;
        }

    }

    /**
     * Exercise caution while using this method. As soon as the QuickJump is clicked, the focus on the current
     * element is lost.
     */
    public static void clickQuickJump(){
        new GWElement(GWIDs.QUICK_JUMP).getElement().click();
    }

    public static String getErrorMessageFromScreen(){
        return BrowserFactory.getCurrentGuidewireBrowser().getDriver().findElement(GWIDs.ERROR_MESSAGE.getReference()).getText();
    }

    @Override
    public GWRadioButton withRadioButton(Identifier identifier) {
        return new GWRadioButton(identifier);
    }

    @Override
    public GWSelectBox withSelectBox(Identifier identifier) {
        new GWElement(identifier).click();
        return new GWSelectBox(identifier);
    }

    @Override
    public GWSelectBox withOptionalSelectBox(Identifier identifier, ReactionTime reactionTime) {
        GWSelectBox uiElement = new GWSelectBox(identifier, reactionTime);
        if(uiElement.isPresent()){
            uiElement.click();
        }

        return uiElement;
    }

    @Override
    public GWElement withElement(Identifier identifier) {
        return new GWElement(identifier);
    }

    @Override
    public UIElement withOptionalElement(Identifier identifier, ReactionTime reactionTime) {
        return new GWElement(identifier, reactionTime);
    }

    public GWTable withTable(Identifier identifier) {
        return new GWTable(identifier);
    }

    @Override
    public UIConfirmationWindow withConfirmationWindow() {
        return new UIConfirmationWindow(GWIDs.CONFIRMATION_WINDOW, ReactionTime.IMMEDIATE);
    }

    @Override
    public UIConfirmationWindow withOptionalConfirmationWindow(ReactionTime reactionTime) {
        return new UIConfirmationWindow(GWIDs.CONFIRMATION_WINDOW, reactionTime);
    }

    @Override
    public GWCheckBox withCheckbox(Identifier identifier) {
        return new GWCheckBox(identifier);
    }

    public GWMultiSelect withMultiSelect(Identifier identifier){
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        WebElement multiSelect = interact.withElement(identifier).getElement();
        return new GWMultiSelect(multiSelect);
    }

    public GWMultiSelect withMultiSelect(Identifier identifier, ReactionTime reactionTime){
        return new GWMultiSelect(identifier, reactionTime);
    }

    public List<WebElement> withMultiValuedElement(Identifier identifier){
        List<WebElement> elements = new ArrayList<>();
        WaitUtils waitUtils = new WaitUtils(BrowserFactory.getCurrentBrowser().getDriver());
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

    public static void waitForPageToLoad(){
        _waitForPageToLoad(10, 1, "Waiting for Quick Jump to be clickable");
    }

    public static void waitForPageToLoad(String message){
        _waitForPageToLoad(10, 1, message);
    }

    public static void waitForPageToLoad(long timeToWait, long pollingInterval){
        _waitForPageToLoad(timeToWait, pollingInterval, "Waiting for Quick Jump to be clickable");
    }

    public static void waitForPageToLoad(long timeToWait, long pollingInterval, String message){
        _waitForPageToLoad(timeToWait, pollingInterval, message);
    }

    private static void _waitForPageToLoad(long timeToWait, long pollingInterval, String message){
        PauseTest.createSpecialInstance(timeToWait, pollingInterval).until(ExpectedConditions.elementToBeClickable(GWIDs.QUICK_JUMP.getReference()), message);
    }

}
