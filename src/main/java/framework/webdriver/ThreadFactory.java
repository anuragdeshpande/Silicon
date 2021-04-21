package framework.webdriver;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadFactory {
    private final static HashMap<Long, HashMap<String, Object>> driverPool = new HashMap<>();
    private static final ThreadLocal<WebDriver> webDriverStore = new ThreadLocal<>();
    private static final AtomicLong nextId = new AtomicLong(0);
    private static final ThreadLocal<Long> threadId = ThreadLocal.withInitial(nextId::getAndIncrement);

    private ThreadFactory() {

    }

    private static final ThreadFactory instance = new ThreadFactory();

    public static ThreadFactory getInstance() {
        return instance;

    }

    public static long getID() {
        return threadId.get();
    }

    public WebDriver getDriver() {
        return webDriverStore.get();
    }

    public void setDriver(WebDriver driver) {
        webDriverStore.set(driver);
        driverPool.computeIfAbsent(getID(), k -> new HashMap<>());
        driverPool.get(getID()).put("Driver", driver);
    }

    public void closeBrowser() {
        webDriverStore.get().close();
        webDriverStore.remove();
        driverPool.remove(getID());
    }

    public void closeAllBrowserWindows() {
        driverPool.forEach((integer, storage) -> {
            ((WebDriver) storage.get("Driver")).close();
        });
    }

    public HashMap<String, Object> getStorage() {
        driverPool.computeIfAbsent(getID(), k -> new HashMap<>());
        return driverPool.get(getID());
    }


}
