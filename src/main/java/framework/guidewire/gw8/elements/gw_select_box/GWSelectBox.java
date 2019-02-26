package framework.guidewire.gw8.elements.gw_select_box;

import framework.elements.Identifier;
import framework.elements.selectbox.UISelectBox;
import framework.elements.ui_element.UIElement;
import framework.guidewire.pages.GWIDs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GWSelectBox extends UISelectBox implements IGWSelectBoxOperations{

    public GWSelectBox(Identifier identifier) {
        super(identifier);
    }

    @Override
    public List<String> getOptions() {
        ArrayList<String> listOptions = new ArrayList<>();
        new UIElement(GWIDs.LIST_OPTIONS).getElement().findElements(By.tagName("li")).forEach(element -> {
            listOptions.add(element.getText());
        });

        return listOptions;
    }
}
