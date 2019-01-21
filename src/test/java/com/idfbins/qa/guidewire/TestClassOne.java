package com.idfbins.qa.guidewire;

import com.aventstack.extentreports.Status;
import framework.BaseOperations;
import framework.webdriver.DriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class TestClassOne extends BaseOperations {

    private static final Logger _logger = LogManager.getLogger(TestClassOne.class.getName());

    @Test()
    public void passTestClassOne() {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        logger().log(Status.INFO, "Something 4");

        driver.close();
        driver.quit();
    }

/*    @Test()
    public void failTestClassOne() {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        logger().log(Status.INFO, "Something 5");
        Assert.fail("This test fails");

        driver.close();
        driver.quit();
    }

    @Test()
    public void exceptionTestClassOne() throws Exception {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        logger().log(Status.INFO, "Something 6");


        throw new Exception("Throwing This");

    }*/
}
