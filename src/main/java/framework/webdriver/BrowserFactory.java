package framework.webdriver;

import framework.guidewire.GuidewireInteract;
import framework.utils.EnvironmentUtils;
import framework.utils.PropertiesFileLoader;
import framework.webdriver.utils.WebDriverOptionsManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Properties;

public class BrowserFactory {
    private static WebDriverOptionsManager optionsManager = new WebDriverOptionsManager();
    private static HashSet<WebDriver> driverSet = new HashSet<>();
    private static ThreadLocal<WebDriver> pool = new ThreadLocal<>();
    private static boolean isRemote = false;
    private static String remoteHubURL = "";
    static {
        Properties properties = PropertiesFileLoader.load("config.properties");

        // First priority, Jenkins Build
        remoteHubURL = System.getProperty("hubUrl");
        if (remoteHubURL != null) {
            isRemote = true;
        }

        // Second Priority, Local configuration file.
        if (properties.getProperty("runtimeEnvironment").equalsIgnoreCase("Grid")) {
            remoteHubURL = properties.getProperty("hubUrl");
            if (remoteHubURL == null || remoteHubURL.trim().length() == 0) {
                Assert.fail("Invalid HUB URL in your local config file.");
            }
            isRemote = true;
        }
    }

    public static synchronized void setDriver() throws MalformedURLException {
        System.out.println("Trying to create New Driver");
        if(isRemote){
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(remoteHubURL), optionsManager.getChromeOptions());
            pool.set(ThreadGuard.protect(remoteWebDriver));
            driverSet.add(remoteWebDriver);
        } else {
            ChromeDriverManager.chromedriver().setup();
            ChromeDriver actualWebDriver = new ChromeDriver(optionsManager.getChromeOptions());
            pool.set(ThreadGuard.protect(actualWebDriver));
            driverSet.add(actualWebDriver);
        }
    }

    public static synchronized Interact getCurrentBrowser(){
        return new Interact(createDriver());
    }

    public static synchronized GuidewireInteract getCurrentGuidewireBrowser(){
        if(pool.get() == null){
            System.out.println("Cannot find driver: Trying to create new one");
        }
        return new GuidewireInteract(createDriver());
    }

    private static synchronized WebDriver createDriver() {
        if(pool.get() == null){
            try {
                setDriver();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return pool.get();
    }

    public static synchronized void closeCurrentBrowser(){
        WebDriver webDriver = pool.get();
        if(webDriver != null){
            webDriver.quit();
        }
    }

    public static  synchronized void closeAllBrowsers(){
        driverSet.forEach(webDriver -> {
            webDriver.quit();
        });
    }


}
