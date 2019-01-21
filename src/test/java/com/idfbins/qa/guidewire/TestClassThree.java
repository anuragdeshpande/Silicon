package com.idfbins.qa.guidewire;

import com.aventstack.extentreports.Status;
import framework.BaseOperations;
import framework.webdriver.DriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClassThree extends BaseOperations {

    private static final Logger _logger = LogManager.getLogger(TestClassThree.class.getName());

    @Test()
    public void passTestThree() {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        logger().log(Status.INFO, "Turkey");

        driver.close();
        driver.quit();
    }

    @Test()
    public void failTestThree() {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        logger().log(Status.INFO, "Turkey 2");
        Assert.fail("This test fails");

        driver.close();
        driver.quit();
    }

    @Test()
    public void exceptionTestThree() throws Exception {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        logger().log(Status.INFO, "Turkey 3");

        throw new Exception("Throwing This");
    }
}
