package framework.guidewire.gw8.elements.gw_table;

import framework.elements.Identifier;
import framework.elements.table.UITable;
import framework.elements.table.UITableRow;
import framework.utils.NumberUtils;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GWTable extends UITable implements IGWUITable{

    private static final By TOOLBAR_REFERENCE = By.xpath("./ancestor::div[2]/preceding-sibling::div[2]");
    private static final By FIRST_PAGE_REFERENCE = By.cssSelector("a[data-qtip='First Page']");
    private static final By LAST_PAGE_REFERENCE = By.cssSelector("a[data-qtip='Last Page']");
    private static final By NEXT_PAGE_REFERENCE = By.cssSelector("a[data-qtip='Next Page']");
    private static final By PREVIOUS_PAGE_REFERENCE = By.cssSelector("a[data-qtip='Previous Page']");

    public GWTable(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void clickAdd() {
        super.clickAdd();
    }

    @Override
    public void clickRemove() {
        super.clickRemove();
    }

    @Override
    public void clickNextPage() {
        this.getElement().findElement(TOOLBAR_REFERENCE).findElement(NEXT_PAGE_REFERENCE).click();
        System.out.println("Next Page Clicked");
    }

    @Override
    public void clickPreviousPage() {
        this.getElement().findElement(TOOLBAR_REFERENCE).findElement(PREVIOUS_PAGE_REFERENCE).click();
        System.out.println("Previous Page Clicked");
    }

    @Override
    public void clickLastPage() {
        this.getElement().findElement(TOOLBAR_REFERENCE).findElement(LAST_PAGE_REFERENCE).click();
        System.out.println("Last Page Clicked");
    }

    @Override
    public void clickFirstPage() {
        this.getElement().findElement(TOOLBAR_REFERENCE).findElement(FIRST_PAGE_REFERENCE).click();
        System.out.println("First Page Clicked");
    }

    @Override
    public void clickToolbarButtonWithText(String buttonText) {
        super.clickToolbarButtonWithText(buttonText);
    }

    @Override
    public UITableRow getRowWithText(String value) {
        boolean isLastPage = false;

        do {
            for (WebElement row : this.getElement().findElements(By.tagName("tr"))) {
                for (WebElement cell : row.findElements(By.tagName("td"))) {
                    if (cell.getText().toUpperCase().contains(value.toUpperCase())) {
                        System.out.println("Found the row with the text: "+value);
                        return new UITableRow(row);
                    }
                }
            }

            if (!this.getElement().findElement(TOOLBAR_REFERENCE).findElement(NEXT_PAGE_REFERENCE).getAttribute("class").contains("x-btn-disabled")) {
                System.out.println("Multuple pages found in the table, searching on the next page");
                clickNextPage();
            } else {
                System.out.println("Last Page reached");
                isLastPage = true;
            }
        } while (!isLastPage);

        System.out.println("Could not find a match in the entire table, returning null");
        return null;
    }

    @Override
    public void clickRandomCheckbox() {
        System.out.println("Searching for all the checkboxes in the table");
        List<WebElement> checkboxes = this.getElement().findElements(By.tagName("img"));
        int randomCheckBoxNumber = NumberUtils.getRandomNumberInRange(0, checkboxes.size() - 1);
        WebElement checkbox = checkboxes.get(randomCheckBoxNumber);

        BrowserFactory.getCurrentBrowser().getActions().clickAndHold(checkbox)
                .moveByOffset(1, 1)
                .release(checkbox)
                .build()
                .perform();

        System.out.println("Clicked on random checkbox : "+randomCheckBoxNumber);
    }
}
