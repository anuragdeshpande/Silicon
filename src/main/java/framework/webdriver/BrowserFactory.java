package framework.webdriver;

import framework.guidewire.GuidewireInteract;
import framework.utils.PropertiesFileLoader;
import framework.webdriver.utils.WebDriverOptionsManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    private static WebDriverOptionsManager optionsManager = new WebDriverOptionsManager();
    private static ThreadLocal<WebDriver> pool = new ThreadLocal<>();
    private static boolean isRemote = false;
    private static String remoteHubURL = "";

    static {
        try {
            Properties properties = PropertiesFileLoader.load("config.properties");

            // First priority, Jenkins Build
            remoteHubURL = System.getProperty("hubUrl");
            if (remoteHubURL != null) {
                isRemote = true;
                System.out.println(Thread.currentThread().getId() + ": Will be opening Remote Web Drivers at: " + remoteHubURL);
            }

            // Second Priority, Local configuration file.
            if (properties.getProperty("runtimeEnvironment").equalsIgnoreCase("Grid")) {
                remoteHubURL = properties.getProperty("hubUrl");
                if (remoteHubURL == null || remoteHubURL.trim().length() == 0) {
                    Assert.fail("Invalid HUB URL in your local config file.");
                }
                isRemote = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized void setDriver() throws MalformedURLException {
        if (isRemote) {
            pool.set(ThreadGuard.protect(new RemoteWebDriver(new URL(remoteHubURL), optionsManager.getChromeOptions())));
            pool.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } else {
            ChromeDriverManager.chromedriver().setup();
            pool.set(ThreadGuard.protect(new ChromeDriver(optionsManager.getChromeOptions())));
            pool.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }

    public static synchronized Interact getCurrentBrowser() {
        return new Interact(createDriver());
    }

    public static synchronized GuidewireInteract getCurrentGuidewireBrowser() {
        return new GuidewireInteract(createDriver());
    }

    private static synchronized WebDriver createDriver() {
        if (pool.get() == null) {
            try {
                setDriver();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return pool.get();
    }

    public static synchronized void closeCurrentBrowser() {
        WebDriver webDriver = pool.get();
        if (webDriver != null) {
            webDriver.quit();
            pool.remove();
        }
    }


}
