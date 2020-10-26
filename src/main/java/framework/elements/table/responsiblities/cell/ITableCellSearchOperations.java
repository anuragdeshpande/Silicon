package framework.elements.table.responsiblities.cell;

/**
 *
 * @param <T> Implementation class representing the returned cells
 */
public interface ITableCellSearchOperations<T> {
    T getCell(int cellNum);
    T getCellByText(String cellText);
    boolean hasValueAtCell(String whatToSearch, int cellNumber);
}
