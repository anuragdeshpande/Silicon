package framework.webdriver;

import com.google.common.primitives.Longs;
import framework.constants.ReactionTime;
import framework.customExceptions.UnexpectedTerminationException;
import framework.utils.PropertiesFileLoader;
import framework.webdriver.utils.WebDriverOptionsManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final WebDriverOptionsManager optionsManager = new WebDriverOptionsManager();
    private static boolean isRemote = false;
    private static String remoteHubURL = "";
    private static ReactionTime reactionTime = ReactionTime.STANDARD_WAIT_TIME;

    private DriverFactory(){

    }

    public static DriverFactory getInstance(){
        return new DriverFactory();
    }

    static {
        try {
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

            // set the default timeout for the application
            String gwPageTimeout = properties.getProperty("GWPageTimeout");
            if(gwPageTimeout != null && !gwPageTimeout.isEmpty()){
                Long waitTime = Longs.tryParse(gwPageTimeout);
                reactionTime = ReactionTime.getInstance(waitTime, TimeUnit.SECONDS);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public WebDriver createBrowserWindow(){
        try {
            return _createBrowserWindow(optionsManager.getChromeOptions());
        } catch (MalformedURLException e) {
            throw new UnexpectedTerminationException("Malformed URL: "+e.getLocalizedMessage());
        }
    }

    public WebDriver createBrowserWindow(ChromeOptions options) {
        try{
            return _createBrowserWindow(options);
        } catch (MalformedURLException e){
            throw new UnexpectedTerminationException("Malformed URL: "+e.getLocalizedMessage());
        }
    }

    public static ReactionTime getReactionTime(){
        return reactionTime;
    }

    private WebDriver _createBrowserWindow(ChromeOptions options) throws MalformedURLException{
        ChromeDriverManager.chromedriver().setup();
        WebDriver driver;
        if(isRemote){
            driver = new RemoteWebDriver(new URL(remoteHubURL), options);
        } else {
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());

        return driver;
    }
}
