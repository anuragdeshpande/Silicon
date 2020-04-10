package framework.elements.table;

import framework.elements.ui_element.UIElement;
import org.openqa.selenium.WebElement;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class UITableRow extends UIElement implements IUITableRow {

    private WebElement element;

    public UITableRow(WebElement element) {
        super(element);
        this.element = element;
    }

    public List<UITableCell> getCells() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public UITableCell getCell(int cellNum) {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public UITableCell getCellByText(String cellText) {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public boolean hasValueAtCell(String whatToSearch, int cellNumber) {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickCheckBox() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickSelectButton() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickButtonWithText(String buttonText) {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickRadioWithLabel(String label) {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public WebElement getElement() {
        return this.element;
    }
}
