package framework.webdriver.utils;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

public class WebDriverOptionsManager {

    public ChromeOptions getChromeOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(Collections.singletonList("--start-maximized"));
        chromeOptions.addArguments(Collections.singletonList("--no-sandbox")); //https://stackoverflow.com/a/50725918/1689770
        chromeOptions.addArguments(Collections.singletonList("--disable-infobars")); //https://stackoverflow.com/a/43840128/1689770
        chromeOptions.addArguments(Collections.singletonList("--disable-dev-shm-usage")); //https://stackoverflow.com/a/50725918/1689770
        chromeOptions.addArguments(Collections.singletonList("--disable-browser-side-navigation")); //https://stackoverflow.com/a/49123152/1689770
        chromeOptions.addArguments(Collections.singletonList("--disable-gpu"));
//        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

        return chromeOptions;
    }

}
