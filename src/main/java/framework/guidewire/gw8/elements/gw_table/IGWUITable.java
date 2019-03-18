package framework.guidewire.gw8.elements.gw_table;

import framework.elements.table.IUITable;
import framework.elements.table.UITableRow;
import org.openqa.selenium.By;

import java.util.List;

public interface IGWUITable {
    void clickAdd();

    void clickRemove();

    GWRow getRowWithText(String value);

    void clickNextPage();

    void clickPreviousPage();

    void clickLastPage();

    void clickFirstPage();

    void clickToolbarButtonWithText(String buttonText);

    void clickRandomCheckbox();

    List<GWCell> getCells(String columnName);
}
