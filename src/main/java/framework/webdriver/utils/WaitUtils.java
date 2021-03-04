package framework.webdriver.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private WebDriver driver;
    private int defaultWait = 5;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriverWait newWait() {
        return new WebDriverWait(this.driver, this.defaultWait);
    }

    protected WebDriverWait newWait(int waitInSeconds) {
        return new WebDriverWait(this.driver, timeFixer(waitInSeconds), 100);
    }

    protected int timeFixer(int time) {
    	int tmp = (time > 300 && time < 1000) ? 1000 : time;
    	tmp = tmp % 1000 == 0 ? (time / 1000 == 0 ? 1 : time / 1000) : tmp;
//      System.out.println("Wait up to: " + tmp + "s");
    	return tmp;
    }

    public WebElement waitUntilElementIsClickable(By locator, int waitInSeconds) {
        newWait(waitInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }

    public WebElement waitUntilElementIsVisible(By locator, int waitInSeconds) {
        newWait(waitInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }
}
