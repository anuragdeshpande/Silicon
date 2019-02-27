package framework.elements.table;

import framework.elements.ui_element.UIElement;
import framework.elements.checkbox.UICheckbox;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UITableCell extends UIElement implements IUITableCell {

    private WebElement element;

    public UITableCell(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void clickSelect() {

    }

    @Override
    public void clickLink() {
        WebElement linkElement = element.findElement(By.tagName("a"));
        String linkText = linkElement.getText();
        linkElement.click();
        System.out.println("Clicked on link: "+linkText);
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
            System.out.println("Text Filled: "+text);
            BrowserFactory.getCurrentBrowser().getDriver().findElement(By.id("QuickJump-inputEl")).click();
        }

        System.out.println("Could not find any text boxes, not doing anything");
    }

    @Override
    public void fillTextArea(String text) {
        this.element.click();
        List<WebElement> textareas = BrowserFactory.getCurrentBrowser().getDriver().findElements(By.cssSelector("textarea[id*='textarea-']"));
        fillText(text, textareas);
    }

    @Override
    public void clickCheckbox() {
        this.element.findElement(By.tagName("img")).click();
        System.out.println("Clicked on the checkbox");
    }


}
