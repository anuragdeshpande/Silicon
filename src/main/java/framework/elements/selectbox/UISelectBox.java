package framework.elements.selectbox;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.guidewire.pages.GWIDs;
import framework.utils.NumberUtils;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UISelectBox extends UIElement implements IUISelectBox {

    protected List<WebElement> listElements;

    public UISelectBox(Identifier identifier) {
        super(identifier);
    }

    protected List<WebElement> getElementOptions(){
        return new ArrayList<>(this.getElement().findElements(By.tagName("li")));
    }

    @Override
    public void select(String selection) {
        for (WebElement listItem : this.getElementOptions()) {
            if (listItem.getText().equalsIgnoreCase(selection)) {
                listItem.click();
                break;
            }
        }

        System.out.println(Thread.currentThread().getId() + ": Selected: "+selection);
    }

    @Override
    public String selectRandom() {
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

            System.out.println(Thread.currentThread().getId() + ": Selected: "+selectionText);
            return selectionText;
        } else {
            System.out.println(Thread.currentThread().getId() + ": Could not select option at random: returning null");
            return null;
        }
    }

    @Override
    public String select(int itemNumber) {
        WebElement selectElement = listElements.get(itemNumber);
        String selectedText = selectElement.getText();
        selectElement.click();

        System.out.println(Thread.currentThread().getId() + ": Selected Item - "+itemNumber+": "+selectedText);
        return selectedText;
    }

    @Override
    public String selectByPartial(String selection) {
        for (WebElement listItem : this.getElementOptions()) {
            if (listItem.getText().contains(selection)) {
                String selectedText = listItem.getText();
                listItem.click();

                System.out.println(Thread.currentThread().getId() + ": Clicked on partial match for: "+selection+" on list option: "+selectedText);
                return selectedText;
            }
        }

        System.out.println(Thread.currentThread().getId() + ": Could not find a partial match for: "+selection);
        return null;
    }

    @Override
    public boolean hasOption(String selection) {
        for (WebElement listItem : this.getElementOptions()) {
            if (listItem.getText().equalsIgnoreCase(selection)) {
                System.out.println(Thread.currentThread().getId() + ": Found the option: "+selection);
                return true;
            }
        }
        System.out.println(Thread.currentThread().getId() + ": Could not find the selection: "+selection);
        return false;
    }

    @Override
    public List<String> getOptions() {
        List<String> listStrings = new ArrayList<>();

        for (WebElement element : this.getElementOptions()) {
            listStrings.add(element.getText());
        }

        System.out.println(Thread.currentThread().getId() + ": Returning "+listStrings.size()+" options: "+ listStrings);
        return listStrings;
    }

    @Override
    public String selectFirstExisting(String[] selections) {
        for (String selection: selections) {
            for (WebElement listElement: this.getElementOptions()) {
                if (selection.equalsIgnoreCase(listElement.getText())) {
                    this.select(selection);
                    System.out.println(Thread.currentThread().getId() + ": Clicked on the first matching option: "+selection);
                    return selection;
                }
            }
        }

        System.out.println(Thread.currentThread().getId() + ": Could not find the first matching option: did not click on anything, returning null");
        return null;
    }
}
