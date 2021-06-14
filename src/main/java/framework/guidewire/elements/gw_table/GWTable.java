package framework.guidewire.elements.gw_table;

import framework.customExceptions.IncorrectCallException;
import framework.customExceptions.PotentialSystemIssueException;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.guidewire.elements.GWElement;
import framework.guidewire.pages.GWIDs;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.poi.util.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class GWTable extends UIElement implements IGWUITable {

    private static final By FIRST_PAGE_REFERENCE = By.xpath(".//div[contains(@id, '_ListPaging-first')]");
    private static final By LAST_PAGE_REFERENCE = By.xpath(".//div[contains(@id, '_ListPaging-last')]");
    private static final By NEXT_PAGE_REFERENCE = By.xpath(".//div[contains(@id, '_ListPaging-next')]");
    private static final By PREVIOUS_PAGE_REFERENCE = By.xpath(".//div[contains(@id, '_ListPaging-prev')]");
    private boolean hasHeaderRow;
    private HashMap<String, String> columnLabelMap = null;

    public GWTable(Identifier identifier) {
        super(identifier);
        hasHeaderRow = true;
        if (identifier.shouldIgnoreLVCheckForTable() != hasHeaderRow) {
            hasHeaderRow = identifier.shouldIgnoreLVCheckForTable();
        }
        if (!identifier.getReference().toString().toUpperCase(Locale.ROOT).endsWith("LV") && identifier.shouldIgnoreLVCheckForTable()) {
            throw new IncorrectCallException("Table IDs must always end with LV");
        }
    }

    public GWTable(Identifier identifier, boolean hasNoHeaderRow) {
        super(identifier);
        columnLabelMap = new HashMap<>();
        hasHeaderRow = hasNoHeaderRow;
        if (identifier.shouldIgnoreLVCheckForTable()) {
            hasHeaderRow = true;
        }
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
        if (hasHeaderRow) {
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

    /**
     * Gets the row that matches the string being passed, if it is found in one of the columns.
     * For Guidewire tables, to combine multiple consecutive columns
     *
     * @param value
     * @return
     */
    @Override
    public GWRow getRowWithText(String value) {
        boolean isLastPage = false;

        do {
            for (WebElement row : this.getElement().findElements(By.xpath(".//tr[not(contains(@class, 'gw-header-row')) and not(contains(@class, 'gw-table-row'))]"))) {
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

    /**
     * Attempts to find a row that meets the conditional predicate passed
     *
     * @param predicate Group of conditions that need to be satisfied by the given row
     * @return Returns {@link Optional<GWRow>} if a row exists.
     */
    public Optional<GWRow> getRow(Predicate<GWRow> predicate) {
        return this.getRows().stream().filter(predicate).findFirst();
    }

    /**
     * Attempts to find row(s) that meets the conditional predicate passed
     *
     * @param predicate Group of conditions that need to be satisfied by the given row
     * @return Returns {@link Stream<GWRow>} if rows exists.
     */
    public Optional<Stream<GWRow>> getRows(Predicate<GWRow> predicate) {
        return Optional.of(this.getRows().stream().filter(predicate));
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
            for (WebElement row : this.getElement().findElements(By.xpath(".//tr[not(contains(@class, 'gw-header-row'))]"))) {
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

    public GWRow getLastNthRow(int n) {
        List<GWRow> rows = this.getRows();
        return rows.get(rows.size() - (n + 1));
    }

    public boolean hasRowWithText(String value) {
        try {
            return getRowWithText(value) != null;
        } catch (PotentialSystemIssueException pse) {
            return false;
        }
    }

    @Override
    public List<GWCell> getCells(String columnName) {
        if (columnLabelMap == null) {
            throw new IncorrectCallException("First call buildColumnMap() when reading the table \"interact.withTable().buildColumnMap()\"");
        }
        ArrayList<GWCell> cells = new ArrayList<>();
        getRows().forEach(gwRow -> {
            cells.add(gwRow.getCell(columnLabelMap.get(columnName)));
        });

        return cells;
    }

    public List<GWRow> getRows() {
        List<GWRow> rows = new ArrayList<>();
        this.getElement().findElements(By.xpath(".//tr[not(contains(@class, 'gw-header-row'))]")).forEach((row) -> rows.add(new GWRow(row, columnLabelMap)));
        return rows;
    }

    public int getRowCount() {
        return getRows().size();
    }

    public GWRow getRow(int rowNumber) {
        return getRows().get(rowNumber);
    }

    public HashMap<String, String> getColumnLabels() {
        if (columnLabelMap == null) {
            throw new IncorrectCallException("First call buildColumnMap() when reading the table \"interact.withTable().buildColumnMap()\"");
        }
        if (hasHeaderRow) {
            return this.columnLabelMap;
        } else {
            throw new IncorrectCallException("Table is marked to not have header row. Cannot click Next");
        }
    }

    public GWTableSelectionColumn getSelectionColumn() {
        return new GWTableSelectionColumn(this, identifier);
    }

    public GWTable buildColumnMap() {
        columnLabelMap = new HashMap<>();
        List<WebElement> labels = getElement().findElements(By.xpath(".//table//tr[contains(@class, 'gw-header-row')]/td"));
        for (WebElement webElement : labels) {
            String label = webElement.getText().trim();
            String id = webElement.getAttribute("id");
            if (identifier.getReferenceValue().contains("LV-") && !identifier.shouldIgnoreLVCheckForTable()) {
                String[] headers = id.replaceAll("Header", "").split("LV-");
                headers[0] = headers[0] + "LV-\\d{0,}-";
                id = StringUtil.join(headers, "");
            } else {
                int lastIndexOf = webElement.getAttribute("id").lastIndexOf("-");
                String part1 = id.substring(0, lastIndexOf+1);
                String part2 = id.substring(lastIndexOf).replace("Header", "");
                id = part1 + "\\d{0,}"+part2;
            }
            columnLabelMap.put(label, id);
        }

        return this;
    }
}
