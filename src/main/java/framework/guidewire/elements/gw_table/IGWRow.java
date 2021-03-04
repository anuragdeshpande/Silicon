package framework.guidewire.elements.gw_table;

public interface IGWRow {
    GWCell getCell(int cellNum);

    GWCell getCellByText(String cellText);

    boolean hasValueAtCell(String whatToSearch, int cellNumber);

    GWCell getCellAtColumnLabel(String columnLabel);
}
