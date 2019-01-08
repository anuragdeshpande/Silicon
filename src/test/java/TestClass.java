import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import framework.webdriver.DriverManager;

public class TestClass {

    @Test()
    public void mytest() {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        driver.close();
        driver.quit();
    }

    @Test()
    public void failTest() {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        Assert.fail("This test fails");
        driver.close();
        driver.quit();
    }

    @Test()
    public void exceptionTest() throws Exception {
        WebDriver driver = DriverManager.chromeDriverSupplier.get();
        driver.get("http://cc8dev.idfbins.com/cc");
        throw new Exception("Throwing This");
    }
}
