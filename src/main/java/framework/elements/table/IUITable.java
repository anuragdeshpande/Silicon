package framework.elements.table;

import java.util.List;

public interface IUITable {
    List<UITableRow> getRows();

    void clickAdd();

    void clickRemove();

    UITableRow getRowWithText(String value);

    void clickNextPage();

    void clickPreviousPage();

    void clickLastPage();

    void clickFirstPage();

    void clickToolbarButtonWithText(String buttonText);
}
