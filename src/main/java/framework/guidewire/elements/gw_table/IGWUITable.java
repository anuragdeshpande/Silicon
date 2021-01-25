package framework.guidewire.elements.gw_table;

import java.util.List;
import java.util.Optional;

public interface IGWUITable {
    void clickAdd();

    void clickRemove();

    GWRow getRowWithText(String value);
    Optional<GWRow> getOptionalRowWithText(String value);

    void clickNextPage();

    void clickPreviousPage();

    void clickLastPage();

    void clickFirstPage();

    void clickToolbarButtonWithText(String buttonText);

    List<GWCell> getCells(String columnName);
}
