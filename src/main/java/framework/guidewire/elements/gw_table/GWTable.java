package framework.guidewire.elements.gw_table;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.guidewire.elements.GWElement;
import framework.guidewire.pages.GWIDs;
import framework.utils.NumberUtils;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GWTable extends UIElement implements IGWUITable{

    private static final By TOOLBAR_REFERENCE = By.xpath("//ancestor::div[2]/preceding-sibling::div[2]");
    private static final By FIRST_PAGE_REFERENCE = By.xpath("//a[@data-qtip='First Page']");
    private static final By LAST_PAGE_REFERENCE = By.xpath("//a[@data-qtip='Last Page']");
    private static final By NEXT_PAGE_REFERENCE = By.xpath("//a[@data-qtip='Next Page']");
    private static final By PREVIOUS_PAGE_REFERENCE = By.xpath("//a[@data-qtip='Previous Page']");

    private HashMap<String, Integer> columnLabelMap;

    public GWTable(Identifier identifier) {
        super(identifier);
//
//        if(!identifier.getReference().toString().endsWith("LV") && !identifier.getReference().toString().endsWith("-body")){
//            Assert.fail("Cannot parse a table if it is not ending in LV or -body");
//        }

        columnLabelMap = new HashMap<>();
        if(identifier.getReference().toString().endsWith("LV")){
            super.elementLocation = By.id(identifier.getReference().toString().replaceAll("By.id: ", "").concat("-body"));
        }
        List<WebElement> labels = getElement().findElement(By.xpath("//div/parent::div")).findElements(By.className("x-column-header-text"));
        for (int i = 0; i < labels.size(); ++i) {
            String label = labels.get(i).getText().trim();
            label = label.equals("") ? "blank-"+(i-1) : label;
            columnLabelMap.put(label, i-1);
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
        WebElement element = this.getElement().findElement(TOOLBAR_REFERENCE).findElement(NEXT_PAGE_REFERENCE);
        element.click();
        System.out.println("Next Page Clicked");
    }

    @Override
    public void clickPreviousPage() {
        WebElement element = this.getElement().findElement(TOOLBAR_REFERENCE).findElement(PREVIOUS_PAGE_REFERENCE);
        element.click();
        System.out.println("Previous Page Clicked");
    }

    @Override
    public void clickLastPage() {
        WebElement element = this.getElement().findElement(TOOLBAR_REFERENCE).findElement(LAST_PAGE_REFERENCE);
        element.click();
        System.out.println("Last Page Clicked");
    }

    @Override
    public void clickFirstPage() {
        WebElement element = this.getElement().findElement(TOOLBAR_REFERENCE).findElement(FIRST_PAGE_REFERENCE);
        element.click();
        System.out.println("First Page Clicked");
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
                for (WebElement cell : row.findElements(By.tagName("td"))) {
                    if (cell.getText().toUpperCase().contains(value.toUpperCase())) {
//                        System.out.println("Found the row with the text: "+value);
                        return new GWRow(row, columnLabelMap);
                    }
                }
            }


            try{
                if (!this.getElement().findElement(TOOLBAR_REFERENCE).findElement(NEXT_PAGE_REFERENCE).getAttribute("class").contains("x-btn-disabled")) {
                    System.out.println("Multuple pages found in the table, searching on the next page");
                    clickNextPage();
                    new GWElement(GWIDs.QUICK_JUMP).click();
                } else {
                    System.out.println("Last Page reached");
                    isLastPage = true;
                }
            } catch (NoSuchElementException nse){
                isLastPage = true;
            }
        } while (!isLastPage);

        System.out.println("Could not find a match in the entire table for "+value+", returning null");
        return null;
    }

    public GWRow getLastRow(){
        List<GWRow> rows = this.getRows();
        return rows.get(rows.size() -1);
    }

    public boolean hasRowWithText(String value){
        return getRowWithText(value) != null;
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

    @Override
    public List<GWCell> getCells(String columnName) {
        ArrayList<GWCell> cells = new ArrayList<>();
        getRows().forEach(gwRow -> {
            cells.add(gwRow.getCell(columnLabelMap.get(columnName)));
        });

        return cells;
    }

    public void clickCheckBoxWithLabel(String label) {
        new GWCell(this.getElement().findElement(By.xpath("//label[contains(text(),'" + label + "')]//ancestor::tr/td/div/img//parent::div//parent::td"))).clickCheckbox();
    }

    public List<GWRow> getRows() {
        List<GWRow> rows = new ArrayList<>();

        this.getElement().findElements(By.tagName("tr")).forEach((row) -> {
            rows.add(new GWRow(row, columnLabelMap));
        });

//        System.out.println("Found : "+rows.size()+" Rows");
        return rows;
    }

    public int getRowCount(){
        return this.getElement().findElements(By.tagName("tr")).size();
    }

    public GWRow getRow(int rowNumber){
        WebElement row = this.getElement().findElements(By.tagName("tr")).get(rowNumber);
        return new GWRow(row, columnLabelMap);
    }

    public HashMap<String, Integer> getColumnLabels(){
        return this.columnLabelMap;
    }
}
