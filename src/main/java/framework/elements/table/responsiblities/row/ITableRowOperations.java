package framework.elements.table.responsiblities.row;

public interface ITableRowOperations {
    int getRowCount();


    // Default method implementations
    default boolean hasRows(){
        return getRowCount() > 0;
    }
}
