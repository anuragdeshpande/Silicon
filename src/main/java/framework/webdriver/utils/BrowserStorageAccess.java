package framework.webdriver.utils;

import framework.webdriver.BrowserFactory;
import org.openqa.selenium.JavascriptExecutor;

public class BrowserStorageAccess {

    private JavascriptExecutor executor;

    public BrowserStorageAccess(){
        this.executor = ((JavascriptExecutor) BrowserFactory.getCurrentBrowser().getDriver());
    }

    /**
     * Uses local browser storage to store a key value pair.
     * Complex data values can be serialized to String
     *
     *  1. Browser storage is synchronized, multiple/ simultaneous calls to storage will be
     *    executed sequentially
 *     2. DO NOT store sensitive information in the storage
 *     3. This storage has little to no form of data protection
 *     4. Can store only 5MB of data in totality
     *
     * @param key unique key in the store
     * @param value value associated with the key
     */
    public synchronized void store(String key, String value){
        executor.executeScript("window.localStorage.setItem('"+ key+"', '"+value+"')");
    }

    /**
     * @param key key whose value needs to be returned
     * @return returns the value as a String. if the value stored is a complex object, it is upto the user to
     * deserialize the data being returned.
     */
    public synchronized String get(String key){
        return (String)executor.executeScript("window.localStorage.getItem('"+key+"')");
    }

    /**
     *
     * @param key
     */
    public synchronized void remove(String key){
        executor.executeScript("window.localStorage.removeItem('"+key+"')");
    }

    public synchronized void clearStorage(){
        executor.executeScript("window.localStorage.clear()");
    }

    public static synchronized void clearAllStorage(){
        ((JavascriptExecutor) BrowserFactory.getCurrentBrowser().getDriver()).executeScript("window.localStorage.clear()");
    }

}
