package framework.elements.table;

import java.util.List;

public interface IUITable {
    void clickAdd();

    void clickRemove();

    UITableRow getRowWithText(String value);

    void clickNextPage();

    void clickPreviousPage();

    void clickLastPage();

    void clickFirstPage();

    void clickToolbarButtonWithText(String buttonText);

    void clickRandomCheckbox();
}
