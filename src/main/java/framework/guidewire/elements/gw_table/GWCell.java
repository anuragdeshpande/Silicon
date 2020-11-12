package framework.guidewire.elements.gw_table;

import framework.elements.checkbox.UICheckbox;
import framework.elements.ui_element.UIElement;
import framework.webdriver.BrowserFactory;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class GWCell extends UIElement  {

    private WebElement element;

    public GWCell(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public void click() {
        element.click();
    }

    public UICheckbox getCheckbox(){
        return new UICheckbox(this.element.findElement(By.cssSelector("input[type=checkbox]")));
    }

    public String getText() {
        return this.element.getText();
    }

    public void clickSelect() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    public void clickLink() {
        WebElement linkElement = element.findElement(By.tagName("a"));
        String linkText = linkElement.getText();
        linkElement.click();
//        System.out.println("Clicked on link: "+linkText);
    }

    public void fillTextArea(String text) {
        this.element.click();
        List<WebElement> textareas = BrowserFactory.getCurrentBrowser().getDriver().findElements(By.cssSelector("textarea[id*='textarea-']"));
        fillText(text, textareas);
    }

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
