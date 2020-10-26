package framework.elements.table.responsiblities.row;

import framework.elements.ui_element.UIElement;
import org.openqa.selenium.By;

import java.util.List;

public interface ITableRowXPathFilter<T> {
    List<T> getRowsMatchingXPathFilter(By xPathFilter);
    T getRowMatchingXPathFilter(By xPathFilter);
    List<UIElement> getElementsMatchingXPathFilter(By xPathFilter);
    UIElement getElementMatchingXPathFilter(By xPathFilter);
}
