package framework.guidewire.elements.gw_table;

import framework.elements.table.IUITableCell;
import framework.elements.ui_element.UIElement;
import framework.guidewire.elements.gw_checkbox.GWCheckBox;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class GWCell extends UIElement implements IGWCell, IUITableCell {

    private WebElement element;

    public GWCell(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public void click() {
        element.click();
    }

    /**
     * @deprecated Use mark/unmark methods instead.
     */
    @Override
    @Deprecated
    public void clickCheckbox() {
        new GWCheckBox(this.element).click();
    }

    @Override
    public void markCheckBox() {
        new GWCheckBox(this.element).mark();
    }

    @Override
    public void unMarkCheckBox() {
        new GWCheckBox(this.element).unmark();
    }

    @Override
    public boolean isMarked() {
        return new GWCheckBox(this.element).isChecked();
    }

    @Override
    public String getText() {
        return this.element.getText();
    }

    @Override
    public void clickSelect() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickLink() {
        WebElement linkElement = element.findElement(By.tagName("a"));
        String linkText = linkElement.getText();
        linkElement.click();
//        System.out.println("Clicked on link: "+linkText);
    }

    @Override
    public void fillTextArea(String text) {
        this.element.click();
        List<WebElement> textareas = BrowserFactory.getCurrentBrowser().getDriver().findElements(By.cssSelector("textarea[id*='textarea-']"));
        fillText(text, textareas);
    }

    @Override
    public void fillTextBox(String text) {
        this.element.click();
        List<WebElement> textboxes = BrowserFactory.getCurrentBrowser().getDriver().findElements(By.cssSelector("input[id*='input-']"));
        fillText(text, textboxes);
    }

    private void fillText(String text, List<WebElement> textboxes) {
        WebElement selectedTextbox = null;
        int currentTextbox = 0;
        for (WebElement textbox : textboxes) {
            Integer idValue = Integer.valueOf(textbox.getAttribute("id").split("-")[1]);
            if (idValue > currentTextbox) {
                currentTextbox = idValue;
                selectedTextbox = textbox;
            }
        }
        if (selectedTextbox != null) {
            selectedTextbox.sendKeys(Keys.chord(Keys.CONTROL + "a"));
            selectedTextbox.sendKeys(text);
//            System.out.println("Text Filled: "+text);
            BrowserFactory.getCurrentBrowser().getDriver().findElement(By.id("QuickJump-inputEl")).click();
        }

//        System.out.println("Could not find any text boxes, not doing anything");
    }

    @Override
    public void doubleClick() {
        Actions actions = BrowserFactory.getCurrentGuidewireBrowser().getActions();
        actions.doubleClick(this.element).perform();
        actions.doubleClick(this.element).perform();
    }

    @Override
    public boolean isPresent() {
        return this.element != null;
    }

    @Override
    public String screenGrab() {
        return this.element.getText();
    }
}
