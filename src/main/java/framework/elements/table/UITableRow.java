package framework.elements.table;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UITableRow extends UIElement implements IUITableRow {

    private WebElement element;

    public UITableRow(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public List<UITableCell> getCells() {
        ArrayList<UITableCell> cells = new ArrayList<>();
        this.element.findElements(By.tagName("td")).forEach(cell -> {
            cells.add(new UITableCell(cell));
        });

        System.out.println("Returning "+cells.size()+" Cells");
        return cells;

    }

    @Override
    public UITableCell getCell(int cellNum) {

     try {
         WebElement element = this.element.findElements(By.tagName("td")).get(cellNum);
         String cellContent = String.valueOf(element.getText());
         System.out.println("Returning Cell: " + cellNum + " contains: "+ cellContent);
         return new UITableCell(element);
     } catch (ArrayIndexOutOfBoundsException ae){
         System.out.println("Cell "+ cellNum +" is out of bounds. returning null");
         return null;
     }
    }

    @Override
    public UITableCell getCellByText(String cellText) {
        List<WebElement> cells = this.element.findElements(By.tagName("td"));
        for (WebElement cell : cells) {
            if (cell.getText().equalsIgnoreCase(cellText)) {
                System.out.println("Returning Cell with contents: "+ cellText);
                return new UITableCell(cell);
            }
        }

        System.out.println("Could not find cell with text: "+cellText);
        return null;
    }

    @Override
    public boolean hasValueAtCell(String whatToSearch, int cellNumber) {
        return this.element.findElements(By.tagName("td")).get(cellNumber).getText().equalsIgnoreCase(whatToSearch);
    }

    @Override
    public void clickCheckBox() {
        this.getCell(0).clickCheckbox();
        System.out.println("CheckBox Clicked");
    }

    @Override
    public void clickSelectButton() {
        this.element.findElement(By.linkText("Select")).click();
        System.out.println("Select Button Clicked");
    }

    @Override
    public void clickButtonWithText(String buttonText) {
        BrowserFactory.getCurrentBrowser().getActions().click(this.element.findElement(By.linkText(buttonText))).moveByOffset(1, 1).build().perform();
        System.out.println("Clicked button with text: "+buttonText);
    }

    @Override
    public void clickRadioWithLabel(String label) {
        this.element.findElement(By.xpath(".//td/label[contains(text(),'" + label + "')]/preceding-sibling::input")).click();
        System.out.println("Radio Button Clicked");
    }

    @Override
    public WebElement getElement() {
        return this.element;
    }
}
