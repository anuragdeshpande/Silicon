package framework.elements.selectbox;

import framework.elements.Identifier;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UISelectBox extends UISelect {

    private List<WebElement> listElements;
    private Select select;

    public UISelectBox(Identifier identifier) {
        super(identifier);
        this.select = new Select(this.getElement());
    }

    public UISelectBox(Identifier identifier, boolean isOptional) {
        super(identifier, isOptional);
        if (this.isPresent()) {
            this.select = new Select(this.getElement());
        }
    }

    public List<WebElement> getElementOptions(){
        return this.select.getOptions();
    }

    @Override
    public void select(String selection) {
        this.select.selectByVisibleText(selection);
    }

    @Override
    public String selectRandom() {
        String selection = this.listElements.get(new Random().nextInt(this.listElements.size())).getText();
        this.select.selectByVisibleText(selection);
        return selection;
    }

    @Override
    public String select(int itemNumber) {
        String selection = this.listElements.get(itemNumber).getText();
        this.select.selectByVisibleText(selection);
        return selection;
    }

    @Override
    public String selectByPartial(String selection) {
        for (WebElement listItem : this.listElements) {
            if (listItem.getText().contains(selection)) {
                String selectedText = listItem.getText();
                this.select.selectByVisibleText(selectedText);
                System.out.println("Clicked on partial match for: "+selection+" on list option: "+selectedText);
                return selectedText;
            }
        }
        System.out.println("Could not find a partial match for: "+selection);
        return null;
    }

    @Override
    public boolean hasOption(String selection) {
        return this.getOptions().contains(selection);
    }

    @Override
    public List<String> getOptions() {
        List<String> listStrings = new ArrayList<>();

        for (WebElement element : this.listElements) {
            listStrings.add(element.getText());
        }

        System.out.println("Returning "+listStrings.size()+" options: "+ listStrings);
        return listStrings;
    }

    @Override
    public String selectFirstExisting(String[] selections) {
        for (String selection: selections) {
            if (this.hasOption(selection)) {
                this.select.selectByVisibleText(selection);
                return selection;
            }
        }
        System.out.println("No selected options exist, returning null.");
        return null;
    }
}
