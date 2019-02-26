package framework.elements.table;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.utils.NumberUtils;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UITable extends UIElement implements IGWUITable {

    public UITable(Identifier identifier) {
        super(identifier);
    }

    @Override
    public WebElement getElement() {
        WebElement baseElement = super.getElement();
        if (!baseElement.getTagName().equalsIgnoreCase("table")) {
            baseElement = baseElement.findElements(By.tagName("table")).get(0);
        }

        return baseElement;
    }

    @Override
    public List<UITableRow> getRows() {
        List<UITableRow> rows = new ArrayList<>();

        this.getElement().findElements(By.tagName("tr")).forEach((row) -> {
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
            for (WebElement row : this.getElement().findElements(By.tagName("tr"))) {
                for (WebElement cell : row.findElements(By.tagName("td"))) {
                    if (cell.getText().toUpperCase().contains(value.toUpperCase())) {
                        return new UITableRow(row);
                    }
                }
            }

            if (!this.getElement().findElement(TOOLBAR_REFERENCE).findElement(NEXT_PAGE_REFERENCE).getAttribute("class").contains("x-btn-disabled")) {
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
        this.getElement().findElement(TOOLBAR_REFERENCE).findElement(NEXT_PAGE_REFERENCE).click();
    }

    @Override
    public void clickPreviousPage() {
        this.getElement().findElement(TOOLBAR_REFERENCE).findElement(PREVIOUS_PAGE_REFERENCE).click();
    }

    @Override
    public void clickLastPage() {
        this.getElement().findElement(TOOLBAR_REFERENCE).findElement(LAST_PAGE_REFERENCE).click();
    }

    @Override
    public void clickFirstPage() {
        this.getElement().findElement(TOOLBAR_REFERENCE).findElement(FIRST_PAGE_REFERENCE).click();
    }

    @Override
    public void clickToolbarButtonWithText(String buttonText) {

    }

    public void clickRandomCheckbox() {
        List<WebElement> checkboxes = this.getElement().findElements(By.tagName("img"));
        WebElement checkbox = checkboxes.get(NumberUtils.getRandomNumberInRange(0, checkboxes.size() - 1));

        BrowserFactory.getCurrentBrowser().getActions().clickAndHold(checkbox)
                .moveByOffset(1, 1)
                .release(checkbox)
                .build()
                .perform();
    }
}
