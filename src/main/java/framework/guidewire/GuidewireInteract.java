package framework.guidewire;

import framework.elements.Identifier;
import framework.elements.alertwindow.UIConfirmationWindow;
import framework.elements.ui_element.UIElement;
import framework.guidewire.gw8.elements.GWElement;
import framework.guidewire.gw8.elements.gw_radio_button.GWRadioButton;
import framework.guidewire.gw8.elements.gw_select_box.GWSelectBox;
import framework.guidewire.gw8.elements.gw_table.GWTable;
import framework.guidewire.pages.GWIDs;
import framework.webdriver.BrowserFactory;
import framework.webdriver.Interact;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GuidewireInteract extends Interact {
    public GuidewireInteract(WebDriver driver) {
        super(driver);
    }

    public void withTabArrow(Identifier identifier) {
        UIElement uiElement = new UIElement(identifier);
        Dimension size = uiElement.getElement().getSize();
        BrowserFactory.getCurrentBrowser().getActions().moveToElement(uiElement.getElement(), size.getWidth() - 12, 10).click().build().perform();
        System.out.println("Tab Arrow Clicked: "+identifier.getReference());
    }

    public static boolean hasErrorMessageOnScreen(){
        try{
            GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
            WebDriver driver = interact.getDriver();
            WebElement errorElement = driver.findElement(GWIDs.ERROR_MESSAGE.getReference());
            return errorElement != null && errorElement.isEnabled();
        } catch (Exception e){
            return false;
        }

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
        new UIElement(GWIDs.QUICK_JUMP).click();
        new UIElement(identifier).click();
        return new GWSelectBox(identifier);
    }

    @Override
    public GWSelectBox withOptionalSelectBox(Identifier identifier) {
        new UIElement(GWIDs.QUICK_JUMP).click();
        UIElement uiElement = new UIElement(identifier, true);
        if(uiElement.isPresent()){
            uiElement.click();
        }

        return new GWSelectBox(identifier);
    }

    @Override
    public GWElement withElement(Identifier identifier) {
        return new GWElement(identifier);
    }

    public GWTable withTable(Identifier identifier) {
        return new GWTable(identifier);
    }

    @Override
    public UIConfirmationWindow withConfirmationWindow() {
        return new UIConfirmationWindow(GWIDs.CONFIRMATION_WINDOW, false);
    }

    @Override
    public UIConfirmationWindow withOptionalConfirmationWindow() {
        return new UIConfirmationWindow(GWIDs.CONFIRMATION_WINDOW, true);
    }
}
