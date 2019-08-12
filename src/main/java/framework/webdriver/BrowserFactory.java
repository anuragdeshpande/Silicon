package framework.webdriver;

import framework.constants.ReactionTime;
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
import java.util.HashSet;
import java.util.Properties;

public class BrowserFactory {
    private static WebDriverOptionsManager optionsManager = new WebDriverOptionsManager();
    public static HashSet<WebDriver> driverPool = new HashSet<>();
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
//                System.out.println(Thread.currentThread().getId() + ": Will be opening Remote Web Drivers at: " + remoteHubURL);
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

    private static synchronized void setDriver() throws MalformedURLException {
        if (isRemote) {
            pool.set(ThreadGuard.protect(new RemoteWebDriver(new URL(remoteHubURL), optionsManager.getChromeOptions())));
        } else {
            ChromeDriverManager.chromedriver().clearPreferences();
            ChromeDriverManager.chromedriver().setup();
            WebDriver driver = ThreadGuard.protect(new ChromeDriver(optionsManager.getChromeOptions()));
            pool.set(driver);
            driverPool.add(driver);
        }
        ReactionTime reactionTime = ReactionTime.STANDARD_WAIT_TIME;
        pool.get().manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
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

    public static synchronized void closeAllWindows(){
        if(driverPool.size() > 0){
            driverPool.iterator().forEachRemaining(WebDriver::quit);
        }
    }

    public static synchronized void reloadDriver(){
        WebDriver driver = getCurrentBrowser().getDriver();
        ReactionTime reactionTime = ReactionTime.STANDARD_WAIT_TIME;
        driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
    }

    public static synchronized void changeImplicitWaitTo(ReactionTime reactionTime){
        WebDriver driver = getCurrentBrowser().getDriver();
        driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());
    }


}
