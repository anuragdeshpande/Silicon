package framework.webdriver.utils;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserWindowManipulator {

    private final WebDriver driver;
    private final JavascriptExecutor js;

    public BrowserWindowManipulator() {
        this.driver = BrowserFactory.getCurrentBrowser().getDriver();
        js = (JavascriptExecutor) driver;
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }

    public void maximize(){
        driver.manage().window().maximize();
    }

    public void scrollToTop(){
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }

    public void scrollToBottom(){
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToLeft(){
        js.executeScript("window.scrollTo(-document.body.scrollWidth, 0)");
    }

    public void scrollToRight(){
        js.executeScript("window.scrollTo(document.body.scrollWidth, 0)");
    }

    public void scrollTo(int xPixels, int yPixels){
        js.executeScript("window.scrollBy("+xPixels+","+yPixels+")");
    }

    public void scrollBy(int xPixels, int yPixels){
        js.executeScript("window.scrollTo("+xPixels+","+yPixels+")");
    }

    public void scrollIntoView(By locatorReference){
        UIElement uiElement = new UIElement(new Identifier(locatorReference));
        js.executeScript("arguments[0].scrollIntoView();", uiElement.getElement());
    }

    public void scrollIntoView(WebElement element){
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
}
