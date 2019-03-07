package framework.elements.table;

import framework.elements.ui_element.UIElement;
import framework.elements.checkbox.UICheckbox;
import framework.utils.NumberUtils;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class UITableCell extends UIElement implements IUITableCell {

    private WebElement element;

    public UITableCell(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public String getText() {
        throw new NotImplementedException();
    }

    @Override
    public void clickSelect() {
        throw new NotImplementedException();
    }

    @Override
    public void click() {
        this.element.click();
    }

    @Override
    public void clickLink() {
       throw new NotImplementedException();
    }

    @Override
    public void fillTextBox(String text) {
        throw new NotImplementedException();
    }

    @Override
    public void fillTextArea(String text) {
        throw new NotImplementedException();
    }

    @Override
    public void clickCheckbox() {
        throw new NotImplementedException();
    }


}
