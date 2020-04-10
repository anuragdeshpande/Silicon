package framework.elements.table;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.commons.lang3.NotImplementedException;

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
//            System.out.println("Finding <table> in the current dom element "+ baseElement);
            baseElement = baseElement.findElements(By.tagName("table")).get(0);
        }

//        System.out.println("Found table: "+ baseElement);
        return baseElement;
    }

    public List<UITableRow> getRows() {
        List<UITableRow> rows = new ArrayList<>();

        this.getElement().findElements(By.tagName("tr")).forEach((row) -> {
            rows.add(new UITableRow(row));
        });

//        System.out.println("Found : "+rows.size()+" Rows");
        return rows;
    }

    @Override
    public void clickAdd() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickRemove() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public UITableRow getRowWithText(String value) {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickNextPage() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickPreviousPage() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickLastPage() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickFirstPage() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickToolbarButtonWithText(String buttonText) {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickRandomCheckbox() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }
}
