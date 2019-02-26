package framework.elements.table;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UITableRow extends UIElement implements IUITableRow {

    private WebElement element;

    public UITableRow(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public List<UITableCell> getCells() {
        ArrayList<UITableCell> cells = new ArrayList<>();
        this.element.findElements(By.tagName("td")).forEach(cell -> {
            cells.add(new UITableCell(cell));
        });

        return cells;

    }

    @Override
    public UITableCell getCell(int cellNum) {
        WebElement element = this.element.findElements(By.tagName("td")).get(cellNum);
        if (element != null) {
            return new UITableCell(element);
        } else {
            return null;
        }
    }

    @Override
    public UITableCell getCellByText(String cellText) {
        List<WebElement> cells = this.element.findElements(By.tagName("td"));
        for (WebElement cell : cells) {
            if (cell.getText().equalsIgnoreCase(cellText)) {
                return new UITableCell(cell);
            }
        }
        return null;
    }

    @Override
    public boolean hasValueAtCell(String whatToSearch, int cellNumber) {
        return false;
    }

    @Override
    public void clickCheckBox() {
        this.getCell(0).clickCheckbox();
    }

    @Override
    public void clickSelectButton() {
        this.element.findElement(By.linkText("Select")).click();
    }

    @Override
    public void clickButtonWithText(String buttonText) {
        BrowserFactory.getCurrentBrowser().getActions().click(this.element.findElement(By.linkText(buttonText))).moveByOffset(1, 1).build().perform();
    }

    @Override
    public void clickRadioWithLabel(String label) {
        this.element.findElement(By.xpath(".//td/label[contains(text(),'" + label + "')]/preceding-sibling::input")).click();
    }

    @Override
    public WebElement getElement() {
        return this.element;
    }
}
