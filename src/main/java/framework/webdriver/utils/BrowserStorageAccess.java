package framework.webdriver.utils;

import framework.webdriver.ThreadFactory;

import java.util.HashMap;

public class BrowserStorageAccess {

    private final static HashMap<Long, HashMap<String, String>> cache = new HashMap<>();

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
        cache(key, value);
    }

    /**
     * @param key key whose value needs to be returned
     * @return returns the value as a String. if the value stored is a complex object, it is upto the user to
     * deserialize the data being returned.
     */
    public synchronized String get(String key) {
        synchronized (cache) {
            return cache.get(ThreadFactory.getID()).get(key);
        }
    }

    /**
     * @param key
     */
    public synchronized void remove(String key) {
        synchronized (cache){
            long currentThread = ThreadFactory.getID();
            if (cache.containsKey(currentThread)) {
                cache.get(currentThread).remove(key);
            }
        }
    }

    public static BrowserStorageAccess getInstance() {
        return new BrowserStorageAccess();
    }

    private void cache(String key, String value) {
        synchronized (cache){
            long threadID = ThreadFactory.getID();
            if (cache.containsKey(threadID)) {
                cache.get(threadID).put(key, value);
            } else {
                HashMap<String, String> valueMap = new HashMap<>();
                valueMap.put(key, value);
                cache.put(threadID, valueMap);
            }
        }
    }
}
