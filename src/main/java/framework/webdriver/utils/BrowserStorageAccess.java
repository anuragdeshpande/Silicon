package framework.webdriver.utils;

import framework.webdriver.ThreadFactory;

import java.util.HashMap;

public class BrowserStorageAccess {
    public BrowserStorageAccess() {
    }

    /**
     * Uses local browser storage to store a key value pair.
     *
     * <b><u>IF A KEY ALREADY EXISTS, IT WILL BE OVERRIDDEN</u></b>
     * <p>
     * Complex data values can be serialized to String
     * <p>
     * 1. Browser storage is synchronized, multiple/ simultaneous calls to storage will be
     * executed sequentially
     * 2. DO NOT store sensitive information in the storage
     * 3. This storage has little to no form of data protection
     * 4. Can store only 5MB of data in totality
     *
     * <b>ANY STORED VARIABLES WILL BE DROPPED AS SOON AS THE BROWSER IS CLOSED</b>
     *
     * @param key   unique key in the store
     * @param value value associated with the key
     */
    public synchronized void store(String key, String value) {
        ThreadFactory.getInstance().getStorage().put(key, value);
    }

    /**
     * @param key key whose value needs to be returned
     * @return returns the value as an Object. if the value stored is a complex object, it is upto the user to
     * deserialize the data being returned.
     */
    public synchronized Object get(String key) {
        return ThreadFactory.getInstance().getStorage().get(key);
    }
    /**
     * @param key
     */
    public synchronized void remove(String key) {
        HashMap<String, Object> storage = ThreadFactory.getInstance().getStorage();
        storage.remove(key);
    }

    public static BrowserStorageAccess getInstance() {
        return new BrowserStorageAccess();
    }
}
