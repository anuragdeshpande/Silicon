package framework.webdriver.utils;

import framework.webdriver.BrowserFactory;
import org.openqa.selenium.JavascriptExecutor;

public class DOMManipulator {

    public DOMManipulator(){

    }

    public void injectBannerMessage(String message){
        StringBuilder builder = new StringBuilder();
        builder.append("let node = document.createElement('div');");
        builder.append("node.id = 'BannerMessage';");
        builder.append("node.innerText = '").append(message).append("';");
        builder.append("node.setAttribute('style', 'text-align: center;text-transform: uppercase;padding: 5px 0; color: white; background: black; letter-spacing: 5px; font-weight: bold;');");
        builder.append("let body = document.getElementsByTagName('Body')[0];");
        builder.append("body.insertBefore(node, body.childNodes[0]);");
        ((JavascriptExecutor) BrowserFactory.getCurrentBrowser().getDriver()).executeScript(builder.toString());
    }

    public void clearBannerMessage(){
        StringBuilder builder = new StringBuilder();
        builder.append("let banner = document.getElementById('BannerMessage');");
        builder.append("banner.parentNode.removeChild(banner)");
        ((JavascriptExecutor) BrowserFactory.getCurrentBrowser().getDriver()).executeScript(builder.toString());
    }
}
