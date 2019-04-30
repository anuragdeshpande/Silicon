package framework.elements.table;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.guidewire.gw8.elements.gw_table.IGWUITable;
import framework.utils.NumberUtils;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class UITable extends UIElement implements IUITable {

    public UITable(Identifier identifier) {
        super(identifier);
    }

    @Override
    public WebElement getElement() {
        WebElement baseElement = super.getElement();
        if (!baseElement.getTagName().equalsIgnoreCase("table")) {
            System.out.println(Thread.currentThread().getId() + ": Finding <table> in the current dom element "+ baseElement);
            baseElement = baseElement.findElements(By.tagName("table")).get(0);
        }

        System.out.println(Thread.currentThread().getId() + ": Found table: "+ baseElement);
        return baseElement;
    }

    public List<UITableRow> getRows() {
        List<UITableRow> rows = new ArrayList<>();

        this.getElement().findElements(By.tagName("tr")).forEach((row) -> {
            rows.add(new UITableRow(row));
        });

        System.out.println(Thread.currentThread().getId() + ": Found : "+rows.size()+" Rows");
        return rows;
    }

    @Override
    public void clickAdd() {
        throw new NotImplementedException();
    }

    @Override
    public void clickRemove() {
        throw new NotImplementedException();
    }

    @Override
    public UITableRow getRowWithText(String value) {
        throw new NotImplementedException();
    }

    @Override
    public void clickNextPage() {
        throw new NotImplementedException();
    }

    @Override
    public void clickPreviousPage() {
        throw new NotImplementedException();
    }

    @Override
    public void clickLastPage() {
        throw new NotImplementedException();
    }

    @Override
    public void clickFirstPage() {
        throw new NotImplementedException();
    }

    @Override
    public void clickToolbarButtonWithText(String buttonText) {
        throw new NotImplementedException();
    }

    @Override
    public void clickRandomCheckbox() {
        throw new NotImplementedException();
    }
}
