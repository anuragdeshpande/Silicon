package framework.elements;

import framework.webdriver.BrowserFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UIElement implements IUIElementOperations {

    private WebElement element;

    public UIElement(WebElement element) {
        this.element = element;
    }

    @Override
    public void click() {
        if (this.isPresent()) {
            this.element.click();
        } else {
            Assert.fail("Element is not Clickable");
        }
    }

    @Override
    public void doubleClick() {
        if (this.isPresent()) {
            this.element.click();
            this.element.click();
        } else {
            Assert.fail("Element is not Clickable");
        }
    }

    @Override
    public void hover() {
        BrowserFactory.getCurrentBrowser().getActions().moveToElement(this.element, 1, 1).build().perform();
    }

    @Override
    public String screenGrab() {
        if (this.element != null) {
            return this.element.getText();
        } else {
            return "";
        }
    }

    @Override
    public boolean isPresent() {
        return this.element != null && this.element.isEnabled();
    }

    public void clickTabArrow() {
        Dimension size = this.element.getSize();
        BrowserFactory.getCurrentBrowser().getActions().moveToElement(this.element, size.getWidth() - 12, 10).click().build().perform();
    }

    @Override
    public void refreshElement() {}

    @Override
    public WebElement getElement() {
        return this.element;
    }


}
