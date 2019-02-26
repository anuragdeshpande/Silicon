package framework.guidewire;

import framework.elements.Identifier;
import framework.elements.selectbox.UISelectBox;
import framework.elements.ui_element.UIElement;
import framework.guidewire.gw8.elements.gw_select_box.GWSelectBox;
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
    }

    @Override
    public UISelectBox withSelectBox(Identifier identifier) {
        new UIElement(GWIDs.ESCAPE_CLICKER).click();
        new UIElement(identifier).click();
        return new GWSelectBox(identifier);
    }

    @Override
    public UISelectBox withOptionalSelectBox(Identifier identifier) {
        new UIElement(GWIDs.ESCAPE_CLICKER).click();
        UIElement uiElement = new UIElement(identifier, true);
        if(uiElement.isPresent()){
            uiElement.click();
        }

        return new GWSelectBox(identifier);
    }
}
