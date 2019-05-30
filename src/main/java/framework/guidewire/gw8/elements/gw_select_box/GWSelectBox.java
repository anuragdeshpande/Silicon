package framework.guidewire.gw8.elements.gw_select_box;

import com.github.javafaker.Faker;
import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.selectbox.UISelect;
import framework.elements.ui_element.UIElement;
import framework.guidewire.gw8.elements.GWElement;
import framework.guidewire.pages.GWIDs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GWSelectBox extends UISelect implements IGWSelectBoxOperations {

    private List<WebElement> listElements;

    public GWSelectBox(Identifier identifier) {
        super(identifier);
    }

    public List<WebElement> getElementOptions() {
        return new UIElement(GWIDs.LIST_OPTIONS).getElement().findElements(By.tagName("li"));
    }

    @Override
    public List<String> getOptions() {
        return getElementOptions().parallelStream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Override
    public void select(String selection) {
        listElements = getElementOptions();
        Optional<WebElement> webElement = listElements.parallelStream()
                .filter(li -> li.getText().equalsIgnoreCase(selection)).findFirst();
        if (webElement.isPresent()) {

            webElement.get().click();
            System.out.println("Selected: " + selection);
        } else {
            System.out.println("Could not find the selection, cancelling select operation");
            new UIElement(GWIDs.QUICK_JUMP).click();
        }

    }

    @Override
    public String selectRandom() {
        listElements = getElementOptions().parallelStream()
                .filter(we -> we.getText().equalsIgnoreCase("New...") && we.getText().equalsIgnoreCase("<none>"))
                .collect(Collectors.toList());

        if (!listElements.isEmpty()) {
            WebElement element = listElements.get(new Faker().number().numberBetween(0, listElements.size()));
            String selectionText = element.getText();
            element.click();
            System.out.println("Selected: " + selectionText);
            return selectionText;
        } else {
            System.out.println("No items other than new choice: returning null");
            new GWElement(GWIDs.QUICK_JUMP, ReactionTime.IMMEDIATE).click();
            return null;
        }

    }

    @Override
    public String select(int itemNumber) {
        listElements = getElementOptions();
        WebElement selectElement = listElements.get(itemNumber);
        String selectedText = selectElement.getText();
        selectElement.click();

        System.out.println("Selected Item - " + itemNumber + ": " + selectedText);
        return selectedText;
    }

    @Override
    public String selectByPartial(String selection) {
        listElements = getElementOptions();
        List<WebElement> collect = listElements.parallelStream().filter(we -> we.getText().contains(selection)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            WebElement element = collect.get(0);
            String selectedText = element.getText();
            element.click();
            System.out.println("Clicked on partial match for: " + selection + " on list option: " + selectedText);
            return selectedText;
        }

        System.out.println("Could not find a partial match for: " + selection);
        new GWElement(GWIDs.QUICK_JUMP, ReactionTime.IMMEDIATE).click();
        return null;
    }

    @Override
    public boolean hasOption(String selection) {
        listElements = getElementOptions();
        boolean anyMatch = listElements.parallelStream().anyMatch(e -> e.getText().equalsIgnoreCase(selection));
        if (!anyMatch) {
            System.out.println("Could not find the selection: " + selection);
        }
        return anyMatch;

    }

    @Override
    public String selectFirstExisting(String[] selections) {
        listElements = getElementOptions();
        List<WebElement> filteredList = listElements.parallelStream().filter(e -> Arrays.stream(selections).anyMatch(s -> e.getText().equalsIgnoreCase(s))).collect(Collectors.toList());

        if (!filteredList.isEmpty()) {
            WebElement element = filteredList.get(0);
            String selection = element.getText();
            element.click();
            System.out.println("Clicked on the first matching option: " + selection);
            new GWElement(GWIDs.QUICK_JUMP, ReactionTime.IMMEDIATE).click();
            return selection;
        }

        new GWElement(GWIDs.QUICK_JUMP).click();
        System.out.println("Could not find the first matching option: did not click on anything, returning null");
        return null;
    }

}
