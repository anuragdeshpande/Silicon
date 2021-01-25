package framework.guidewire;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.enums.ElementType;
import framework.elements.ui_element.UIElement;
import framework.guidewire.elements.GWElement;
import framework.guidewire.elements.gw_radio_button.GWRadioButton;
import framework.guidewire.elements.gw_table.GWTable;
import framework.guidewire.pages.GWIDs;
import framework.webdriver.Interact;
import framework.webdriver.PauseTest;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GuidewireInteract extends Interact {
    public GuidewireInteract(WebDriver driver) {
        super(driver);
    }

    public void withTabArrow(Identifier identifier) {
        GuidewireInteract.clickQuickJump();
        UIElement uiElement = new UIElement(identifier);
        uiElement.getElement().findElement(By.className("gw-icon--expand")).click();
        PauseTest.waitForPageToLoad();
    }

    /**
     * this is designed to catch the error messages immediately after clicking.
     * if the error message is shown on the screen with in 10 Milliseconds from the click, the function returns false.
     */
    public static boolean hasErrorMessageOnScreen(){
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    /**
     * Exercise caution while using this method. As soon as the QuickJump is clicked, the focus on the current
     * element is lost.
     */
    public static void clickQuickJump(){
        new GWElement(GWIDs.QUICK_JUMP).getElement().click();
    }


    public static String getErrorMessageFromScreen(){
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public GWRadioButton withRadioButton(Identifier identifier) {
        return new GWRadioButton(identifier);
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

    public static boolean isPageLoading(){
        UIElement pageLoader = new UIElement(new Identifier(By.id("gw-click-overlay"), ElementType.ELEMENT), ReactionTime.MOMENTARY);
        return pageLoader.isPresent() && pageLoader.getElement().getAttribute("class").contains("gw-click-overlay");
    }

}
