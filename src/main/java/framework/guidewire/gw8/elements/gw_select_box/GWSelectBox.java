package framework.guidewire.gw8.elements.gw_select_box;

import com.github.javafaker.Faker;
import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.selectbox.UISelect;
import framework.elements.ui_element.UIElement;
import framework.guidewire.gw8.elements.GWElement;
import framework.guidewire.pages.GWIDs;
import framework.webdriver.PauseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GWSelectBox extends UISelect implements IGWSelectBoxOperations {

    private List<WebElement> listElements;

    public GWSelectBox(Identifier identifier) {
        super(identifier);
    }

    private Stream<WebElement> getElementStream() {
        return new UIElement(GWIDs.LIST_OPTIONS).getElement()
                .findElements(By.tagName("li")).stream()
                .filter(we -> !we.getText().equalsIgnoreCase("New...") && !we.getText().equalsIgnoreCase("<none>"));
    }

    public List<WebElement> getElementOptions() {
        return getElementStream().collect(Collectors.toList());
    }

    @Override
    public List<String> getOptions() {
        return getElementStream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Override
    public void select(String selection) {
//        listElements = getElementOptions();
        Optional<WebElement> webElement = getElementStream()
                .filter(li -> li.getText().equalsIgnoreCase(selection)).findFirst();
        if (webElement.isPresent()) {
            selectElement(webElement.get());
            System.out.println("Selected: " + selection);
        } else {
            System.out.println("Could not find the selection, cancelling select operation");
            new UIElement(GWIDs.QUICK_JUMP).click();
        }

    }

    @Override
    public String selectRandom() {
        listElements = getElementOptions();
        if (!listElements.isEmpty()) {
            WebElement element = listElements.get(new Faker().number().numberBetween(0, listElements.size() - 1));
            String selectionText = element.getText();
            PauseTest.createSpecialInstance(1, 10).until(ExpectedConditions.visibilityOf(element));
            selectElement(element);
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
        selectElement(selectElement);

        System.out.println("Selected Item - " + itemNumber + ": " + selectedText);
        return selectedText;
    }

    @Override
    public String selectByPartial(String selection) {
        List<WebElement> collect = getElementStream().filter(we -> we.getText().contains(selection)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            WebElement element = collect.get(0);
            String selectedText = element.getText();
            selectElement(element);
            System.out.println("Clicked on partial match for: " + selection + " on list option: " + selectedText);
            return selectedText;
        }

        System.out.println("Could not find a partial match for: " + selection);
        new GWElement(GWIDs.QUICK_JUMP, ReactionTime.IMMEDIATE).click();
        return null;
    }

    @Override
    public boolean hasOption(String selection) {
        boolean anyMatch = getElementStream().anyMatch(e -> e.getText().equalsIgnoreCase(selection));
        if (!anyMatch) {
            System.out.println("Could not find the selection: " + selection);
        }
        return anyMatch;

    }

    @Override
    public String selectFirstExisting(String[] selections) {
        List<WebElement> filteredList = getElementStream().filter(e -> Arrays.stream(selections).anyMatch(s -> e.getText().equalsIgnoreCase(s))).collect(Collectors.toList());

        if (!filteredList.isEmpty()) {
            WebElement element = filteredList.get(0);
            String selection = element.getText();
            selectElement(element);
            System.out.println("Clicked on the first matching option: " + selection);
            new GWElement(GWIDs.QUICK_JUMP, ReactionTime.IMMEDIATE).click();
            return selection;
        }

        new GWElement(GWIDs.QUICK_JUMP).click();
        System.out.println("Could not find the first matching option: did not click on anything, returning null");
        return null;
    }

    private void selectElement(WebElement element) {
        PauseTest.createSpecialInstance(1, 5).until(ExpectedConditions.visibilityOf(element));
        element.click();
        new GWElement(GWIDs.QUICK_JUMP, ReactionTime.IMMEDIATE).click();
    }

}
