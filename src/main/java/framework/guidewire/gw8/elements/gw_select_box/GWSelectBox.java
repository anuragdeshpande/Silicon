package framework.guidewire.gw8.elements.gw_select_box;

import framework.elements.Identifier;
import framework.elements.selectbox.UISelect;
import framework.elements.ui_element.UIElement;
import framework.guidewire.pages.GWIDs;
import framework.utils.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GWSelectBox extends UISelect implements IGWSelectBoxOperations {

    private List<WebElement> listElements;

    public GWSelectBox(Identifier identifier) {
        super(identifier);
    }

    public List<WebElement> getElementOptions(){
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
        listElements = getElementOptions();
        for (WebElement listItem : this.getElementOptions()) {
            if (listItem.getText().equalsIgnoreCase(selection)) {
                listItem.click();
                break;
            }
        }

        System.out.println("Selected: "+selection);
        new UIElement(GWIDs.QUICK_JUMP).click();
    }

    @Override
    public String selectRandom() {
        listElements = getElementOptions();

        List<WebElement> validItems = new ArrayList<>();
        int count = 0;

        for (WebElement select : this.getElementOptions()) {
            if (!select.getText().equalsIgnoreCase("New...")
                    && !select.getText().equalsIgnoreCase("<none>")) {
                validItems.add(select);
                count++;
            }
        }
        if (validItems.size() > 0) {
            WebElement selectionElement = validItems.get(NumberUtils.getRandomNumberInRange(0, count - 1));
            String selectionText = selectionElement.getText();
            selectionElement.click();

            System.out.println("Selected: "+selectionText);
            new UIElement(GWIDs.QUICK_JUMP).click();
            return selectionText;
        } else {
            System.out.println("Could not select option at random: returning null");
            new UIElement(GWIDs.QUICK_JUMP).click();
            return null;
        }
    }

    @Override
    public String select(int itemNumber) {
        listElements = getElementOptions();
        WebElement selectElement = listElements.get(itemNumber);
        String selectedText = selectElement.getText();
        selectElement.click();

        System.out.println("Selected Item - "+itemNumber+": "+selectedText);
        new UIElement(GWIDs.QUICK_JUMP).click();
        return selectedText;
    }

    @Override
    public String selectByPartial(String selection) {
        listElements = getElementOptions();
        for (WebElement listItem : this.getElementOptions()) {
            if (listItem.getText().contains(selection)) {
                String selectedText = listItem.getText();
                listItem.click();

                System.out.println("Clicked on partial match for: "+selection+" on list option: "+selectedText);
                new UIElement(GWIDs.QUICK_JUMP).click();
                return selectedText;
            }
        }

        System.out.println("Could not find a partial match for: "+selection);
        new UIElement(GWIDs.QUICK_JUMP).click();
        return null;
    }

    @Override
    public boolean hasOption(String selection) {
        listElements = getElementOptions();
        for (WebElement listItem : this.getElementOptions()) {
            if (listItem.getText().equalsIgnoreCase(selection)) {
                System.out.println("Found the option: "+selection);
                return true;
            }
        }
        System.out.println("Could not find the selection: "+selection);
        return false;
    }

    @Override
    public String selectFirstExisting(String[] selections) {
        listElements = getElementOptions();
        for (String selection: selections) {
            for (WebElement listElement: this.getElementOptions()) {
                if (selection.equalsIgnoreCase(listElement.getText())) {
                    this.select(selection);
                    new UIElement(GWIDs.QUICK_JUMP).click();
                    System.out.println("Clicked on the first matching option: "+selection);
                    return selection;
                }
            }
        }

        new UIElement(GWIDs.QUICK_JUMP).click();
        System.out.println("Could not find the first matching option: did not click on anything, returning null");
        return null;
    }

}
