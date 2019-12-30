package framework.guidewire.elements.gw_table;

import framework.elements.Identifier;
import framework.elements.textbox.UITextbox;
import framework.elements.ui_element.UIElement;
import framework.guidewire.GuidewireInteract;
import framework.guidewire.elements.gw_select_box.GWSelectBox;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GWRow extends UIElement implements IGWRow{
    private WebElement element;

    public GWRow(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public void click() {
        this.getCell(0).click();
    }

    @Override
    public GWCell getCell(int cellNum) {
        try {
            WebElement element = this.element.findElements(By.tagName("td")).get(cellNum);
//            System.out.println("Returning Cell: " + cellNum + " contains: "+ element.getText());
            return new GWCell(element);
        } catch (ArrayIndexOutOfBoundsException ae){
//            System.out.println("Cell "+ cellNum +" is out of bounds. returning null");
            return null;
        }
    }

    @Override
    public GWCell getCellByText(String cellText) {
        List<WebElement> cells = this.element.findElements(By.tagName("td"));
        for (WebElement cell : cells) {
            if (cell.getText().equalsIgnoreCase(cellText)) {
//                System.out.println("Returning Cell with contents: "+ cellText);
                return new GWCell(cell);
            }
        }

//        System.out.println("Could not find cell with text: "+cellText);
        return null;
    }

    @Override
    public boolean hasValueAtCell(String whatToSearch, int cellNumber) {
        return this.element.findElements(By.tagName("td")).get(cellNumber).getText().equalsIgnoreCase(whatToSearch);
    }

    @Override
    public void clickCheckBox() {
        this.getCell(0).clickCheckbox();
//        System.out.println("CheckBox Clicked");
    }

    @Override
    public void clickSelectButton() {
        this.element.findElement(By.linkText("Select")).click();
//        System.out.println("Select Button Clicked");
    }

    @Override
    public void clickButtonWithText(String buttonText) {
        BrowserFactory.getCurrentGuidewireBrowser().getActions().click(this.element.findElement(By.linkText(buttonText))).moveByOffset(1, 1).build().perform();
//        System.out.println("Clicked button with text: "+buttonText);
    }

    @Override
    public void clickRadioWithLabel(String label) {
        this.element.findElement(By.xpath(".//td/label[contains(text(),'" + label + "')]/preceding-sibling::input")).click();
//        System.out.println("Radio Button Clicked");
    }

    public List<GWCell> getCells() {
        ArrayList<GWCell> cells = new ArrayList<>();
        this.element.findElements(By.tagName("td")).forEach(cell -> cells.add(new GWCell(cell)));
//        System.out.println("Returning "+cells.size()+" Cells");
        return cells;
    }

    @Override
    public GWSelectBox getSelectBoxCell(int cellNumber, Identifier selectBoxIdentifierByName) {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        WebDriver driver = interact.getDriver();
        try{
            driver.findElement(selectBoxIdentifierByName.getReference());
        } catch (Exception e){
            this.getCell(cellNumber).click();
        }

        return new GWSelectBox(selectBoxIdentifierByName);
    }

    @Override
    public UITextbox getTextBoxCell(int cellNumber, Identifier textBoxIdentifierByName) {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        try{
            interact.getDriver().findElement(textBoxIdentifierByName.getReference());
        } catch (Exception e){
            this.getCell(cellNumber).click();
        }

        return new UITextbox(textBoxIdentifierByName);
    }

    public GWSelectBox getDoubleClickSelectBoxCell(int cellNumber, Identifier selectBoxIdentifierByName) {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        try{
            interact.getDriver().findElement(selectBoxIdentifierByName.getReference());
        } catch (Exception e){
            this.getCell(cellNumber).doubleClick();
        }

        return new GWSelectBox(selectBoxIdentifierByName);
    }

    public UITextbox getDoubleClickTextBoxCell(int cellNumber, Identifier textBoxIdentifierByName) {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        try{
            interact.getDriver().findElement(textBoxIdentifierByName.getReference());
        } catch (Exception e){
            this.getCell(cellNumber).doubleClick();
        }

        return new UITextbox(textBoxIdentifierByName);
    }

    @Override
    public boolean isPresent() {
        return this.element != null;
    }
}
