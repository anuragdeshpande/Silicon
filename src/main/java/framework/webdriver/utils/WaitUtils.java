package framework.webdriver.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private final WebDriver driver;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriverWait newWait(int waitInSeconds) {
        return new WebDriverWait(this.driver, waitInSeconds, 10);
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
