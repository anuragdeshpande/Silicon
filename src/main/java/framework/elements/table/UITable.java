package framework.elements.table;

import framework.elements.Identifier;
import framework.elements.UIElement;
import framework.utils.NumberUtils;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UITable extends UIElement implements IGWUITable {

    private WebElement element;
    private Identifier identifier;

    public UITable(WebElement element, Identifier identifier) {
        super(element);
        this.element = element;
        this.identifier = identifier;
    }

    private void refreshTableElement() {
        this.element = BrowserFactory.getCurrentBrowser().withTable(this.identifier).getElement();
    }

    @Override
    public List<UITableRow> getRows() {
        List<UITableRow> rows = new ArrayList<>();

        this.element.findElements(By.tagName("tr")).forEach((row) -> {
            rows.add(new UITableRow(row));
        });
        return rows;
    }

    @Override
    public void clickAdd() {

    }

    @Override
    public void clickRemove() {

    }

    @Override
    public UITableRow getRowWithText(String value) {

        boolean isLastPage = false;

        do {
            refreshTableElement();
            for (WebElement row : this.element.findElements(By.tagName("tr"))) {
                for (WebElement cell : row.findElements(By.tagName("td"))) {
                    if (cell.getText().toUpperCase().contains(value.toUpperCase())) {
                        return new UITableRow(row);
                    }
                }
            }

            if (!this.element.findElement(TOOLBAR_REFERENCE).findElement(NEXT_PAGE_REFERENCE).getAttribute("class").contains("x-btn-disabled")) {
                clickNextPage();
                //this.element = driver.findElement(By.id(""+ tableID +""));
            } else {
                isLastPage = true;
            }
        } while (!isLastPage);
        return null;
    }

    @Override
    public void clickNextPage() {
        this.element.findElement(TOOLBAR_REFERENCE).findElement(NEXT_PAGE_REFERENCE).click();
    }

    @Override
    public void clickPreviousPage() {
        this.element.findElement(TOOLBAR_REFERENCE).findElement(PREVIOUS_PAGE_REFERENCE).click();
    }

    @Override
    public void clickLastPage() {
        this.element.findElement(TOOLBAR_REFERENCE).findElement(LAST_PAGE_REFERENCE).click();
    }

    @Override
    public void clickFirstPage() {
        this.element.findElement(TOOLBAR_REFERENCE).findElement(FIRST_PAGE_REFERENCE).click();
    }

    @Override
    public void clickToolbarButtonWithText(String buttonText) {

    }

    public void clickRandomCheckbox() {
        List<WebElement> checkboxes = this.element.findElements(By.tagName("img"));
        WebElement checkbox = checkboxes.get(NumberUtils.getRandomNumberInRange(0, checkboxes.size() - 1));

        BrowserFactory.getCurrentBrowser().getActions().clickAndHold(checkbox)
                .moveByOffset(1, 1)
                .release(checkbox)
                .build()
                .perform();
    }
}
