package framework.elements.table;

import framework.elements.ui_element.UIElement;
import framework.elements.checkbox.UICheckbox;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
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
        element.findElement(By.tagName("a")).click();
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
            selectedTextbox.clear();
            selectedTextbox.sendKeys(text);
            BrowserFactory.getCurrentBrowser().getDriver().findElement(By.id("QuickJump-inputEl")).click();
        }
    }

    @Override
    public void fillTextArea(String text) {
        this.element.click();
        List<WebElement> textareas = BrowserFactory.getCurrentBrowser().getDriver().findElements(By.cssSelector("textarea[id*='textarea-']"));
        fillText(text, textareas);
    }

    @Override
    public void clickCheckbox() {
        new UICheckbox(this.element.findElement(By.tagName("img"))).click();
    }


}
