package framework.elements.table;

import java.util.List;

public interface IUITableRow {
    UITableCell getCell(int cellNum);

    UITableCell getCellByText(String cellText);

    boolean hasValueAtCell(String whatToSearch, int cellNumber);

    void clickCheckBox();

    void clickSelectButton();

    void clickButtonWithText(String buttonText);

    void clickRadioWithLabel(String label);
}
