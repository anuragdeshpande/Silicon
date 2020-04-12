package framework.applications.portals.elements.tables;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.table.responsiblities.cell.ITableCellXPathFilter;
import framework.elements.ui_element.UIElement;
import framework.logger.RegressionLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PortalTableRow extends UIElement implements ITableCellXPathFilter<PortalTableCell> {
    public PortalTableRow(Identifier identifier) {
        super(identifier);
    }

    public PortalTableRow(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }

    protected PortalTableRow(WebElement element) {
        super(element);
    }

    @Override
    public List<PortalTableCell> getCellsMatchingXPathFilter(By xpathFilter) {
        ArrayList<PortalTableCell> cells = new ArrayList<>();
        try{
            getElement().findElements(xpathFilter).forEach(element -> {
                cells.add(new PortalTableCell(element));
            });
            return cells;
        } catch (Exception e){
            RegressionLogger.getTestLogger().error(e);;
            throw e;
        }
    }

    @Override
    public PortalTableCell getCellMatchingXPathFilter(By xPathFilter) {
        try{
            return new PortalTableCell(getElement().findElement(xPathFilter));
        } catch (Exception e){
            RegressionLogger.getTestLogger().error(e);
            throw e;
        }
    }
}
