package framework.guidewire.gw8.elements.gw_table;

import framework.elements.Identifier;
import framework.elements.table.UITableCell;
import framework.elements.textbox.UITextbox;
import framework.guidewire.gw8.elements.gw_select_box.GWSelectBox;

import java.util.List;

public interface IGWRow {
    GWSelectBox getSelectBoxCell(int cellNumber, Identifier selectBoxIdentifierByName);
    UITextbox getTextBoxCell(int cellNumber, Identifier textBoxIdentifierByName);
   GWCell getCell(int cellNum);

    GWCell getCellByText(String cellText);

    boolean hasValueAtCell(String whatToSearch, int cellNumber);

    void clickCheckBox();

    void clickSelectButton();

    void clickButtonWithText(String buttonText);

    void clickRadioWithLabel(String label);
}
