package framework.webdriver.utils;

import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverOptionsManager {

    public ChromeOptions getChromeOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
        chromeOptions.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
        chromeOptions.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        chromeOptions.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        chromeOptions.addArguments("--disable-gpu");

        if(System.getProperty("Headless")!= null && System.getProperty("Headless").equalsIgnoreCase("true")){
            chromeOptions.addArguments("--headless");
        }

//        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

        return chromeOptions;
    }

}
