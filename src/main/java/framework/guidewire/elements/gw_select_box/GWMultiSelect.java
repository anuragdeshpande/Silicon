package framework.guidewire.elements.gw_select_box;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.security.Key;
import java.util.List;

public class GWMultiSelect extends UIElement {

    public GWMultiSelect(Identifier identifier) {
        super(identifier);
    }

    public GWMultiSelect(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }

    public GWMultiSelect(WebElement element) {
        super(element);
    }


    public void select(String... optionsToSelect){
        WebElement multiSelectElement = getElement();
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        List<WebElement> multiSelectOptions = multiSelectElement.findElements(By.tagName("li"));
        interact.getActions().keyDown(Keys.CONTROL).build().perform();
        multiSelectOptions.forEach(multiSelectOption -> {
            for (String optionToSelect : optionsToSelect) {
                if(multiSelectOption.getText().equalsIgnoreCase(optionToSelect)){
                    multiSelectOption.click();
                }
            }
        });
        interact.getActions().keyUp(Keys.CONTROL).build().perform();


    }
}
