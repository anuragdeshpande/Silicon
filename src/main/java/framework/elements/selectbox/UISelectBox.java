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
            selectionElement.click();
            return selectionElement.getText();
        } else {
            return null;
        }
    }

    @Override
    public String select(int itemNumber) {
        WebElement selectElement = listElements.get(itemNumber);
        selectElement.click();
        BrowserFactory.getCurrentBrowser().getActions().sendKeys(Keys.ESCAPE).perform();
        return selectElement.getText();
    }

    @Override
    public String selectByPartial(String selection) {
        for (WebElement listItem : this.getElementOptions()) {
            if (listItem.getText().contains(selection)) {
                listItem.click();
                return listItem.getText();
            }
        }
        return null;
    }

    @Override
    public boolean hasOption(String selection) {
        for (WebElement listItem : this.getElementOptions()) {
            if (listItem.getText().equalsIgnoreCase(selection)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getOptions() {
        List<String> listStrings = new ArrayList<>();

        for (WebElement element : this.getElementOptions()) {
            listStrings.add(element.getText());
        }
        return listStrings;
    }

    @Override
    public String selectFirstExisting(String[] selections) {
        for (String selection: selections) {
            for (WebElement listElement: this.getElementOptions()) {
                if (selection.equalsIgnoreCase(listElement.getText())) {
                    this.select(selection);
                    return selection;
                }
            }
        }
        return null;
    }
}
