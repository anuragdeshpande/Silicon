package framework.guidewire.elements.gw_table;

import framework.elements.Identifier;
import framework.elements.textbox.UITextbox;
import framework.guidewire.elements.gw_select_box.GWSelectBox;

public interface IGWRow {
    GWSelectBox getSelectBoxCell(int cellNumber, Identifier selectBoxIdentifierByName);

    UITextbox getTextBoxCell(int cellNumber, Identifier textBoxIdentifierByName);

    GWCell getCell(int cellNum);

    GWCell getCellByText(String cellText);

    boolean hasValueAtCell(String whatToSearch, int cellNumber);

    void clickCheckBox(int cellNumber);

    void clickSelectButton();

    void clickButtonWithText(String buttonText);

    void clickRadioWithLabel(String label);

    GWCell getCellAtColumnLabel(String columnLabel);
}
