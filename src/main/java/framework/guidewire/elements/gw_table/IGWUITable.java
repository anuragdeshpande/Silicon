package framework.guidewire.elements.gw_table;

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
