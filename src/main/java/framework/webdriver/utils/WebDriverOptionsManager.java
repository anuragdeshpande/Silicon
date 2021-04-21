package framework.webdriver.utils;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.Arrays;

public class WebDriverOptionsManager {

    public ChromeOptions getChromeOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
        chromeOptions.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
        chromeOptions.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        chromeOptions.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--incognito");

        if(System.getProperty("Headless")!= null && System.getProperty("Headless").equalsIgnoreCase("true")){
            chromeOptions.setHeadless(true);
        }

        // UI Element class will accept the alert window. By default the driver is set to IGNORE
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        return chromeOptions;
    }

    public ChromeOptions addAdditionalOption(String... argumentsToAdd){
        ChromeOptions chromeOptions = getChromeOptions();
        Arrays.stream(argumentsToAdd).forEach(chromeOptions::addArguments);

        return chromeOptions;
    }

    public ChromeOptions addAdditionalCapability(String capabilityKey, Object value){
        ChromeOptions chromeOptions = getChromeOptions();
        chromeOptions.setCapability(capabilityKey, value);

        return chromeOptions;
    }

}
