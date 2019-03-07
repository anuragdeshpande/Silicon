package framework.guidewire;

import framework.elements.Identifier;
import framework.elements.selectbox.UISelectBox;
import framework.elements.table.UITable;
import framework.elements.ui_element.UIElement;
import framework.guidewire.gw8.elements.gw_radio_button.GWRadioButton;
import framework.guidewire.gw8.elements.gw_select_box.GWSelectBox;
import framework.guidewire.gw8.elements.gw_table.GWTable;
import framework.guidewire.pages.GWIDs;
import framework.webdriver.BrowserFactory;
import framework.webdriver.Interact;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

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

    @Override
    public GWRadioButton withRadioButton(Identifier identifier) {
        return new GWRadioButton(identifier);
    }

    @Override
    public GWSelectBox withSelectBox(Identifier identifier) {
        new UIElement(GWIDs.ESCAPE_CLICKER).click();
        new UIElement(identifier).click();
        return new GWSelectBox(identifier);
    }

    @Override
    public GWSelectBox withOptionalSelectBox(Identifier identifier) {
        new UIElement(GWIDs.ESCAPE_CLICKER).click();
        UIElement uiElement = new UIElement(identifier, true);
        if(uiElement.isPresent()){
            uiElement.click();
        }

        return new GWSelectBox(identifier);
    }

    public GWTable withTable(Identifier identifier) {
        return new GWTable(identifier);
    }
}
