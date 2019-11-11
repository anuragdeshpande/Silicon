package framework.elements.table;

import framework.elements.ui_element.UIElement;
import org.openqa.selenium.WebElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class UITableRow extends UIElement implements IUITableRow {

    private WebElement element;

    public UITableRow(WebElement element) {
        super(element);
        this.element = element;
    }

    public List<UITableCell> getCells() {
        throw new NotImplementedException();
    }

    @Override
    public UITableCell getCell(int cellNum) {
        throw new NotImplementedException();
    }

    @Override
    public UITableCell getCellByText(String cellText) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasValueAtCell(String whatToSearch, int cellNumber) {
        throw new NotImplementedException();
    }

    @Override
    public void clickCheckBox() {
        throw new NotImplementedException();
    }

    @Override
    public void clickSelectButton() {
        throw new NotImplementedException();
    }

    @Override
    public void clickButtonWithText(String buttonText) {
        throw new NotImplementedException();
    }

    @Override
    public void clickRadioWithLabel(String label) {
        throw new NotImplementedException();
    }

    @Override
    public WebElement getElement() {
        return this.element;
    }
}
