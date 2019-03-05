package framework.guidewire.gw8.elements.gw_table;

import framework.elements.table.UITableCell;
import framework.elements.table.UITableRow;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GWRow extends UITableRow {

    public GWRow(WebElement element) {
        super(element);
    }

    @Override
    public GWCell getCell(int cellNum) {
        return new GWCell(super.getCell(cellNum).getElement());
    }

    @Override
    public GWCell getCellByText(String cellText) {
        return new GWCell(super.getCellByText(cellText).getElement());
    }
}
