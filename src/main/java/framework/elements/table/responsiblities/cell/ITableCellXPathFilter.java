package framework.elements.table.responsiblities.cell;

import org.openqa.selenium.By;

import java.util.List;

public interface ITableCellXPathFilter<T> {
    List<T> getCellsMatchingXPathFilter(By xpathFilter);
    T getCellMatchingXPathFilter(By xPathFilter);
}
