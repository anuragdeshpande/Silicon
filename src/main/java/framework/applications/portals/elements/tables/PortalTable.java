package framework.applications.portals.elements.tables;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.table.responsiblities.cell.ITableCellXPathFilter;
import framework.elements.table.responsiblities.row.ICanGetRows;
import framework.elements.table.responsiblities.row.ITableRowOperations;
import framework.elements.table.responsiblities.row.ITableRowXPathFilter;
import framework.elements.ui_element.UIElement;
import framework.logger.RegressionLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PortalTable extends UIElement
        implements ITableRowOperations,
        ICanGetRows<PortalTableRow>,
        ITableRowXPathFilter<PortalTableRow>,
        ITableCellXPathFilter<PortalTableCell>{


    public PortalTable(Identifier identifier) {
        super(identifier);
    }

    public PortalTable(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }

    protected PortalTable(WebElement element) {
        super(element);
    }

    @Override
    public int getRowCount() {
        try{
            return getElement().findElements(By.xpath("//tr")).size();
        } catch (Exception e){
            RegressionLogger.getTestLogger().fail(e);
            RegressionLogger.getTestLogger().info("Returning 0 as row count");
            return 0;
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public List<PortalTableCell> getCellsMatchingXPathFilter(By xpathFilter) {
        ArrayList<PortalTableCell> cells = new ArrayList<>();
        try{
            getElement().findElements(xpathFilter).forEach(element -> cells.add(new PortalTableCell(element)));
            return cells;
        } catch (Exception e){
            RegressionLogger.getTestLogger().fail(e);
            throw  e;
        }
    }

    @Override
    public PortalTableCell getCellMatchingXPathFilter(By xPathFilter) {
        try{
            return new PortalTableCell(getElement().findElement(xPathFilter));
        } catch (Exception e){
            RegressionLogger.getTestLogger().fail(e);
            throw e;
        }
    }

    @Override
    public List<PortalTableRow> getRowsMatchingXPathFilter(By xPathFilter) {
        ArrayList<PortalTableRow> cells = new ArrayList<>();
        try{
            getElement().findElements(xPathFilter).forEach(element -> cells.add(new PortalTableRow(element)));
            return cells;
        } catch (Exception e){
            RegressionLogger.getTestLogger().fail(e);
            throw  e;
        }
    }

    @Override
    public PortalTableRow getRowMatchingXPathFilter(By xPathFilter) {
        try{
            return new PortalTableRow(getElement().findElement(xPathFilter));
        } catch (Exception e){
            RegressionLogger.getTestLogger().fail(e);
            throw e;
        }
    }

    @Override
    public List<UIElement> getElementsMatchingXPathFilter(By xPathFilter) {
        ArrayList<UIElement> elements = new ArrayList<>();
        try{
            getElement().findElements(xPathFilter).forEach(element -> elements.add(new UIElement(element)));
            return elements;
        } catch (Exception e){
            RegressionLogger.getTestLogger().fail(e);
            throw e;
        }
    }

    @Override
    public UIElement getElementMatchingXPathFilter(By xPathFilter) {
        try {
            return new UIElement(new Identifier((xPathFilter)));
        } catch (Exception e) {
            RegressionLogger.getTestLogger().fail(e);
            throw e;
        }
    }

    @Override
    public List<PortalTableRow> getRows() {
        ArrayList<PortalTableRow> tableRows = new ArrayList<>();
        try{
            getElement().findElements(By.xpath(".//tbody/tr")).forEach(element -> tableRows.add(new PortalTableRow(element)));
            return tableRows;
        } catch (Exception e){
            RegressionLogger.getTestLogger().fail(e);
            throw e;
        }
    }
}
