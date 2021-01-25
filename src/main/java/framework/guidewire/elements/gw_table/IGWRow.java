package framework.guidewire.elements.gw_table;

import framework.elements.Identifier;
import framework.elements.selectbox.UISelect;
import framework.elements.textbox.UITextbox;

public interface IGWRow {
    UISelect getSelectBoxCell(int cellNumber, Identifier selectBoxIdentifierByName);

    UITextbox getTextBoxCell(int cellNumber, Identifier textBoxIdentifierByName);

    GWCell getCell(int cellNum);

    GWCell getCellByText(String cellText);

    boolean hasValueAtCell(String whatToSearch, int cellNumber);

    void clickSelectButton();

    void clickButtonWithText(String buttonText);

    void clickRadioWithLabel(String label);

    GWCell getCellAtColumnLabel(String columnLabel);
}
