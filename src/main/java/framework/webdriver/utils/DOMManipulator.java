package framework.webdriver.utils;

import framework.webdriver.BrowserFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class DOMManipulator {

    public DOMManipulator(){

    }

    /* message injections */

    /**
     * Creates a info bar message at the top of the browser page
     * @param message String text to show in the message bar
     */
    public synchronized void injectInfoMessage(String message){
        injectBannerMessage(message, "#cce5ff", "#004085");
    }

    /**
     * Creates a Danger bar message at the top of the browser page
     * @param message String text to show in the message bar
     */
    public synchronized void injectDangerMessage(String message){
        injectBannerMessage(message, "#721c24", "#f8d7da");
    }

    /**
     * Creates a Warning bar message at the top of the browser page
     * @param message String text to show in the message bar
     */
    public synchronized void injectWarningMessage(String message){
        injectBannerMessage(message, "#856404", "#fff3cd");
    }

    /**
     * Creates a Success bar message at the top of the browser page
     * @param message String text to show in the message bar
     */
    public synchronized void injectSuccessMessage(String message){
        injectBannerMessage(message, "#155724", "#d4edda");
    }

    /* end message injections */

    private synchronized void injectBannerMessage(String message, String backgroundColor, String textColor){
        // Clearing any existing banner messages.
        clearBannerMessage();

        // Adding the New Banner Message
        String builder = "let node = document.createElement('div');" +
                "node.id = 'BannerMessage';" +
                "node.innerText = '" + message + "';" +
                "node.setAttribute('style', 'text-align: center;position: relative; z-index: 5000;text-transform: uppercase;padding: 5px 0; color: " + textColor + "; background: " + backgroundColor + "; letter-spacing: 5px; font-weight: bold;');" +
                "let body = document.getElementsByTagName('Body')[0];" +
                "body.insertBefore(node, body.childNodes[0]);";
        WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
        if(driver != null){
            ((JavascriptExecutor) driver).executeScript(builder);
        }
    }

    public synchronized void clearBannerMessage(){
        String builder = "let banner = document.getElementById('BannerMessage');" +
                "if(banner !== null)banner.parentNode.removeChild(banner)";

        WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
        if(driver != null){
            ((JavascriptExecutor) driver).executeScript(builder);
        }
    }
}
