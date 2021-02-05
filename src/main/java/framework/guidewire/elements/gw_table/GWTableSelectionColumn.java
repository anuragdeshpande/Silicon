package framework.guidewire.elements.gw_table;

import com.github.javafaker.Faker;
import framework.elements.Identifier;
import framework.elements.checkbox.UICheckbox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

public class GWTableSelectionColumn {

    private final GWTable table;
    private final Identifier identifier;

    public GWTableSelectionColumn(GWTable table, Identifier tableIdentifier) {
        this.table = table;
        this.identifier = tableIdentifier;
    }

    public void selectRandomCheckbox() {
        List<WebElement> name = this.table.getElement().findElements(By.xpath(".//input[@type='checkbox'][contains(@name, '_Checkbox')]"))
                .stream().filter(webElement -> webElement.getAttribute("Name").matches("LV-\\d-_Checkbox")).collect(Collectors.toList());
        WebElement webElement = name.get(new Faker(Locale.US).number().numberBetween(0, name.size()));
        UICheckbox checkbox = new UICheckbox(webElement);
        checkbox.mark();
    }
}
