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
    protected List<WebElement> getElementOptions(){
        return new ArrayList<>(new UIElement(GWIDs.LIST_OPTIONS).getElement().findElements(By.tagName("li")));
    }

    @Override
    public List<String> getOptions() {
        ArrayList<String> listOptions = new ArrayList<>();
        new UIElement(GWIDs.LIST_OPTIONS).getElement().findElements(By.tagName("li")).forEach(element -> {
            listOptions.add(element.getText());
        });

        return listOptions;
    }

    @Override
    public void select(String selection) {
        super.listElements = getElementOptions();
        super.select(selection);
        new UIElement(GWIDs.QUICK_JUMP).click();
    }

    @Override
    public String selectRandom() {
        super.listElements = getElementOptions();
        String selection = super.selectRandom();
        new UIElement(GWIDs.QUICK_JUMP).click();
        return selection;
    }

    @Override
    public String select(int itemNumber) {
        super.listElements = getElementOptions();
        String selection = super.select(itemNumber);
        new UIElement(GWIDs.QUICK_JUMP).click();
        return selection;
    }

    @Override
    public String selectByPartial(String selection) {
        super.listElements = getElementOptions();
        super.selectByPartial(selection);
        new UIElement(GWIDs.QUICK_JUMP).click();
        return selection;
    }

    @Override
    public boolean hasOption(String selection) {
        super.listElements = getElementOptions();
        return super.hasOption(selection);
    }

    @Override
    public String selectFirstExisting(String[] selections) {
        super.listElements = getElementOptions();
        return super.selectFirstExisting(selections);
    }
}
