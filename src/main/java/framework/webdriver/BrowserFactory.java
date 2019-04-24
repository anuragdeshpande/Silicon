package framework.webdriver;

import framework.guidewire.GuidewireInteract;
import framework.utils.EnvironmentUtils;
import framework.utils.PropertiesFileLoader;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.URL;
import java.util.Properties;

public class BrowserFactory {

    private BrowserFactory() {
        //Do-nothing..Do not allow to initialize this class from outside
    }

    private static BrowserFactory instance = new BrowserFactory();

    private ThreadLocal<WebDriver> pool = new ThreadLocal<WebDriver>() { // thread local pool object for webdriver
        @Override
        protected WebDriver initialValue() {
            Properties properties = PropertiesFileLoader.load("config.properties");

            // First priority, Jenkins Build
            String hubUrl = System.getProperty("hubUrl");
            if (hubUrl != null) {
                return getRemoteWebDriver(hubUrl);
            }

            // Second Priority, Local configuration file.
            if (properties.getProperty("runtimeEnvironment").equalsIgnoreCase("Grid")) {
                hubUrl = properties.getProperty("hubUrl");
                if (hubUrl == null || hubUrl.trim().length() == 0) {
                    Assert.fail("Invalid HUB URL in your local config file.");
                }
                return getRemoteWebDriver(hubUrl);
            }

            // Default, Local pool.
            ChromeDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
//            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
            chromeOptions.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
            chromeOptions.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
            chromeOptions.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
            chromeOptions.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
            return new ChromeDriver(chromeOptions);
        }

        private RemoteWebDriver getRemoteWebDriver(String url) {
            try {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
//                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
                chromeOptions.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
                chromeOptions.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
                chromeOptions.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
                chromeOptions.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
                chromeOptions.setCapability("idleTimeout", 350);
                return new RemoteWebDriver(new URL(url), chromeOptions);
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail("Could not open URL - " + url);
            }
            return null;
        }
    };

    public static Interact getCurrentBrowser() {
        return new Interact(instance.pool.get());
    }

    public static Interact getNewChromeBrowser() {
        return new Interact(instance.pool.get());
    }

    public static Interact getNewChromeBrowser(String url){
        Interact interact = new Interact(instance.pool.get());
        interact.getDriver().get(url);

        return interact;
    }

    public static GuidewireInteract getNewGuidewireChromeBrowser() {
        return new GuidewireInteract(instance.pool.get());
    }

    public static GuidewireInteract getCurrentGuidewireBrowser() {
        return new GuidewireInteract(instance.pool.get());
    }

    public static void closeCurrentBrowser() { // Quits the pool and closes the browser

        WebDriver driver = instance.pool.get();
        if(driver != null){
            driver.quit();
            instance.pool.remove();
        }

    }

}
