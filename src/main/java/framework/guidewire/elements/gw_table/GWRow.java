package framework.guidewire.elements.gw_table;

import framework.customExceptions.IncorrectCallException;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GWRow extends UIElement implements IGWRow{
    private WebElement element;
    private HashMap<String, String> columnLabelMap;

    public GWRow(WebElement element, HashMap<String, String> columnLabelMap) {
        super(element);
        this.element = element;
        this.columnLabelMap = columnLabelMap;
    }

    @Override
    public GWCell getCell(int cellNum) {
        try {
            WebElement element = this.element.findElements(By.tagName("td")).get(cellNum);
//            System.out.println("Returning Cell: " + cellNum + " contains: "+ element.getText());
            return new GWCell(element);
        } catch (ArrayIndexOutOfBoundsException ae){
//            System.out.println("Cell "+ cellNum +" is out of bounds. returning null");
            return null;
        }
    }

    public GWCell getCell(String id){
        try {
            List<WebElement> elements = this.element.findElements(By.tagName("td"));
            for (WebElement element : elements) {
                if(element.getAttribute("id").matches(id)){
                    return new GWCell(element);
                }
            }
        } catch (ArrayIndexOutOfBoundsException ae){
            Assert.fail("Incorrect Index: "+ae.getLocalizedMessage());
            throw ae;
        }

        Assert.fail("Couldn't find cell with ID: "+id);
        throw new RuntimeException();
    }

    @Override
    public GWCell getCellByText(String cellText) {
        List<WebElement> cells = this.element.findElements(By.tagName("td"));
        for (WebElement cell : cells) {
            if (cell.getText().equalsIgnoreCase(cellText)) {
//                System.out.println("Returning Cell with contents: "+ cellText);
                return new GWCell(cell);
            }
        }

//        System.out.println("Could not find cell with text: "+cellText);
        return null;
    }

    @Override
    public boolean hasValueAtCell(String whatToSearch, int cellNumber) {
        return this.element.findElements(By.tagName("td")).get(cellNumber).getText().equalsIgnoreCase(whatToSearch);
    }

    @Override
    public GWCell getCellAtColumnLabel(String columnLabel) {
        if(columnLabelMap == null){
            throw new IncorrectCallException("Please call buildColumnMap() when reading the table \"interact.withTable().buildColumnMap()\"");
        }
        return this.getCell(columnLabelMap.get(columnLabel));
    }

    public List<GWCell> getCells() {
        ArrayList<GWCell> cells = new ArrayList<>();
        this.element.findElements(By.tagName("td")).forEach(cell -> cells.add(new GWCell(cell)));
//        System.out.println("Returning "+cells.size()+" Cells");
        return cells;
    }

    @Override
    public boolean isPresent() {
        return this.element != null;
    }
}
