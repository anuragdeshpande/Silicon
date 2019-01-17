package com.idfbins.qa.guidewire;

import com.aventstack.extentreports.Status;
import framework.BaseOperations;
import framework.webdriver.DriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClassTwo extends BaseOperations {

    private static final Logger _logger = LogManager.getLogger(TestClassTwo.class.getName());

    @Test()
    public void passTest() {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        logger().log(Status.INFO, "Something 3");

        driver.close();
        driver.quit();
    }

    @Test()
    public void failTest() {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        logger().log(Status.INFO, "Something 1");
        Assert.fail("This test fails");

        driver.close();
        driver.quit();
    }

    @Test()
    public void exceptionTest() throws Exception {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        logger().log(Status.INFO, "Something 2");


        throw new Exception("Throwing This");

    }
}
