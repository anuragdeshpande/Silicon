package framework.guidewire.elements.gw_table;

import framework.customExceptions.IncorrectCallException;
import framework.customExceptions.PotentialSystemIssueException;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.guidewire.elements.GWElement;
import framework.guidewire.pages.GWIDs;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.*;

public class GWTable extends UIElement implements IGWUITable {

    private static final By FIRST_PAGE_REFERENCE = By.xpath(".//div[contains(@id, '_ListPaging-first')]");
    private static final By LAST_PAGE_REFERENCE = By.xpath(".//div[contains(@id, '_ListPaging-last')]");
    private static final By NEXT_PAGE_REFERENCE = By.xpath(".//div[contains(@id, '_ListPaging-next')]");
    private static final By PREVIOUS_PAGE_REFERENCE = By.xpath(".//div[contains(@id, '_ListPaging-prev')]");
    private final boolean hasHeaderRow;

    private final HashMap<String, String> columnLabelMap;

    public GWTable(Identifier identifier) {
        super(identifier);
        hasHeaderRow = true;
        if (!identifier.getReference().toString().toUpperCase(Locale.ROOT).endsWith("LV")) {
            throw new IncorrectCallException("Table IDs must always end with LV");
        }

        columnLabelMap = new HashMap<>();
        List<WebElement> labels = getElement().findElements(By.xpath(".//table//tr[contains(@class, 'gw-header-row')]/td"));
        for (int i = 0; i < labels.size(); ++i) {
            String label = labels.get(i).getText().trim();
            label = label.equals("") ? "blank-" + (i - 1) : label;
            columnLabelMap.put(label, labels.get(i).getAttribute("id"));
        }
    }

    public GWTable(Identifier identifier, boolean hasNoHeaderRow) {
        super(identifier);
        columnLabelMap = new HashMap<>();
        hasHeaderRow = false;
    }

    @Override
    public void clickAdd() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickRemove() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickNextPage() {
        if (hasHeaderRow) {
            WebElement element = this.getElement().findElement(NEXT_PAGE_REFERENCE);
            element.click();
            System.out.println("Next Page Clicked");
        } else {
            throw new IncorrectCallException("Table is marked to not have header row. Cannot click Next");
        }

    }

    @Override
    public void clickPreviousPage() {
        if (hasHeaderRow) {
            WebElement element = this.getElement().findElement(PREVIOUS_PAGE_REFERENCE);
            element.click();
            System.out.println("Previous Page Clicked");
        } else {
            throw new IncorrectCallException("Table is marked to not have header row. Cannot click Next");
        }

    }

    @Override
    public void clickLastPage() {
        if (hasHeaderRow) {
            WebElement element = this.getElement().findElement(LAST_PAGE_REFERENCE);
            element.click();
            System.out.println("Last Page Clicked");
        } else {
            throw new IncorrectCallException("Table is marked to not have header row. Cannot click Next");
        }
    }

    @Override
    public void clickFirstPage() {
        if(hasHeaderRow) {
            WebElement element = this.getElement().findElement(FIRST_PAGE_REFERENCE);
            element.click();
            System.out.println("First Page Clicked");
        } else {
            throw new IncorrectCallException("Table is marked to not have header row. Cannot click Next");
        }
    }

    @Override
    public void clickToolbarButtonWithText(String buttonText) {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public GWRow getRowWithText(String value) {
        boolean isLastPage = false;

        do {
            for (WebElement row : this.getElement().findElements(By.tagName("tr"))) {
                // new code
                String rowContent = row.getText();
                if (rowContent.contains(value)) {
                    return new GWRow(row, columnLabelMap);
                }
            }


            try {
                if (this.getElement().findElement(NEXT_PAGE_REFERENCE).getAttribute("aria-disabled").equalsIgnoreCase("false")) {
                    System.out.println("Multiple pages found in the table, searching on the next page");
                    clickNextPage();
                    new GWElement(GWIDs.QUICK_JUMP).click();
                } else {
                    System.out.println("Last Page reached");
                    isLastPage = true;
                }
            } catch (NoSuchElementException nse) {
                isLastPage = true;
            }
        } while (!isLastPage);

        throw new PotentialSystemIssueException("Row containing: " + value + " was not found in the table");
    }

    @Override
    public Optional<GWRow> getOptionalRowWithText(String value) {
        try {
            return Optional.of(getRowWithText(value));
        } catch (PotentialSystemIssueException pse) {
            return Optional.empty();
        }
    }

    public GWRow getRowWithTextExcludingText(String value, String exclusion) {
        boolean isLastPage = false;

        do {
            for (WebElement row : this.getElement().findElements(By.tagName("tr"))) {
                for (WebElement cell : row.findElements(By.tagName("td"))) {
                    if (cell.getText().toUpperCase().contains(value.toUpperCase()) && !cell.getText().toUpperCase().contains(exclusion.toUpperCase())) {
                        return new GWRow(row, columnLabelMap);
                    }
                }
            }


            try {
                if (this.getElement().findElement(NEXT_PAGE_REFERENCE).getAttribute("aria-disabled").equalsIgnoreCase("false")) {
                    System.out.println("Multiple pages found in the table, searching on the next page");
                    clickNextPage();
                    new GWElement(GWIDs.QUICK_JUMP).click();
                } else {
                    System.out.println("Last Page reached");
                    isLastPage = true;
                }
            } catch (NoSuchElementException nse) {
                isLastPage = true;
            }
        } while (!isLastPage);

        System.out.println("Could not find a match in the entire table for " + value + ", returning null");
        return null;
    }

    public GWRow getLastRow() {
        List<GWRow> rows = this.getRows();
        return rows.get(rows.size() - 1);
    }

    public boolean hasRowWithText(String value) {
        return getRowWithText(value) != null;
    }

    @Override
    public List<GWCell> getCells(String columnName) {
        ArrayList<GWCell> cells = new ArrayList<>();
        getRows().forEach(gwRow -> {
            cells.add(gwRow.getCell(columnLabelMap.get(columnName)));
        });

        return cells;
    }

    public List<GWRow> getRows() {
        List<GWRow> rows = new ArrayList<>();

        this.getElement().findElements(By.tagName("tr")).forEach((row) -> {
            rows.add(new GWRow(row, columnLabelMap));
        });

//        System.out.println("Found : "+rows.size()+" Rows");
        return rows;
    }

    public int getRowCount() {
        return this.getElement().findElements(By.tagName("tr")).size();
    }

    public GWRow getRow(int rowNumber) {
        WebElement row = this.getElement().findElements(By.tagName("tr")).get(rowNumber);
        return new GWRow(row, columnLabelMap);
    }

    public HashMap<String, String> getColumnLabels() {
        if(hasHeaderRow){
            return this.columnLabelMap;
        } else {
            throw new IncorrectCallException("Table is marked to not have header row. Cannot click Next");
        }
    }

    public GWTableSelectionColumn getSelectionColumn() {
        return new GWTableSelectionColumn(this, identifier);
    }
}
