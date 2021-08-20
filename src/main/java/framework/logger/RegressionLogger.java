package framework.logger;

import ch.qos.logback.classic.Logger;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import framework.ReportManager;
import framework.constants.StringConstants;
import framework.customExceptions.UnexpectedTerminationException;
import framework.events.FrameworkEvents;
import framework.logger.eventMessaging.IMaintainEventNames;
import framework.logger.eventMessaging.LogEventState;
import framework.logger.eventMessaging.LoggingEvent;
import framework.reports.models.TestDetailsDTO;
import framework.webdriver.BrowserFactory;
import framework.webdriver.ThreadFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class RegressionLogger {

    private final static Logger regressionLogger = (Logger) LoggerFactory.getLogger("RegressionLogger");
    private final ExtentTest extentLogger;
    private final boolean isSuite;

    public RegressionLogger(final ExtentTest extentLogger, final boolean isSuite) {
        this.extentLogger = extentLogger;
        this.isSuite = isSuite;
    }

    public synchronized static RegressionLogger getFirstAvailableLogger() {
        final String testName = ((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_NAME));
        final String className = ((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_CLASS_NAME));
        final String xmlName = ((String) ThreadFactory.getInstance().getStorage().get(StringConstants.XML_TEST_NAME));
        final TestDetailsDTO dto = new TestDetailsDTO();
        dto.setClassName(className);
        dto.setTestName(testName);

        if (ReportManager.getTest(dto) != null) {
            return getTestLogger();
        }

        if (ReportManager.getClass(className) != null) {
            return getTestClassLogger();
        }

        if (ReportManager.getXMLTest(xmlName) != null) {
            return getXMLTestLogger();
        }

        throw new UnexpectedTerminationException("Could not find a suitable logger at the moment (XMLTestLogger: " + xmlName + ", ClassTestLogger: " + className + ", TestLogger: " + dto.getTestName() + "), please check if the test is initialized.");

    }

    public synchronized static RegressionLogger getTestLogger(final String testMethodName, final String testClassName) {
        final TestDetailsDTO dto = new TestDetailsDTO();
        dto.setTestName(testMethodName);
        dto.setClassName(testClassName);
        final ExtentTest extentTest = ReportManager.getTest(dto).getExtentTest();
        return new RegressionLogger(extentTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getTestLogger() {
        final TestDetailsDTO dto = new TestDetailsDTO();
        dto.setTestName(((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_NAME)));
        dto.setClassName(((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_CLASS_NAME)));
        final ExtentTest extentTest = ReportManager.getTest(dto).getExtentTest();
        return new RegressionLogger(extentTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getTestClassLogger(final String testClassName) {
        final ExtentTest extentClassTest = ReportManager.getClass(testClassName).getExtentTest();
        return new RegressionLogger(extentClassTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getTestClassLogger() {
        final ExtentTest extentClassTest = ReportManager.getClass(((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_CLASS_NAME))).getExtentTest();
        return new RegressionLogger(extentClassTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getXMLTestLogger(final String xmlTestName) {
        final ExtentTest extentXMLTest = ReportManager.getXMLTest(xmlTestName).getExtentTest();
        return new RegressionLogger(extentXMLTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getXMLTestLogger() {
        final ExtentTest extentXMLTest = ReportManager.getXMLTest(((String) ThreadFactory.getInstance().getStorage().get(StringConstants.XML_TEST_NAME))).getExtentTest();
        return new RegressionLogger(extentXMLTest, ReportManager.isInitiated());
    }

    public static void print(final Object message) {
        final String testName = ((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_NAME));
        final String className = ((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_CLASS_NAME));
        System.out.println("[" + className + ": " + testName + "] " + message);
        regressionLogger.info("[" + className + ": " + testName + "] " + message);
    }

    public void info(final Object message) {
        print(message);
        if (isSuite) {
            extentLogger.log(Status.INFO, message.toString());
        }

    }

    public void info(final Object message, final Throwable e) {
        print(message);
        if (isSuite) {
            extentLogger.log(Status.INFO, message.toString());
            extentLogger.log(Status.INFO, e);
        } else {
            e.printStackTrace();
        }

    }

    public void fail(final Object message) {
        print(message);
        if (isSuite) {
            extentLogger.log(Status.FAIL, message.toString());
        }
    }

    public void fail(final Object message, final Throwable e) {
        print(message);
        if (isSuite) {
            extentLogger.log(Status.FAIL, message.toString());
            extentLogger.log(Status.FAIL, e);
        } else {
            e.printStackTrace();
        }

    }

    public void warn(final Object message) {
        print(message);
        if (isSuite) {
            extentLogger.log(Status.WARNING, message.toString());
        }

    }

    public void addTag(final String tagName) {
        if (isSuite) {
            extentLogger.log(Status.INFO, "Tagging: " + tagName);
            logInstantEvent(FrameworkEvents.TAG, "TagValue=" + tagName);
            extentLogger.assignCategory(tagName);
        } else {
            System.out.println("Could not tag since the run is not a suite");
        }
    }

    public void warn(final Object message, final Throwable e) {
        print(message);
        if (isSuite) {
            extentLogger.log(Status.WARNING, message.toString());
            extentLogger.log(Status.WARNING, e);
        } else {
            e.printStackTrace();
        }

    }

    public void captureScreenshot(final String screenShotTitle) {
        if (this.isSuite) {
            info("Screen shot Captured:" + screenShotTitle);
            final WebDriver driver;
            driver = BrowserFactory.getCurrentBrowser().getDriver();
            final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            final String destinationFilePath = ReportManager.REPORT_DIRECTORY_LOCATION + File.separator + getTestName() + "customScreenshot" + ".png";
            try {
                final File destFile = new File(destinationFilePath);
                FileUtils.moveFile(scrFile, destFile);
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            final String path = destinationFilePath.replace("\\\\qa\\regression_logs\\", "http://qa.idfbins.com/regression_logs/").replaceAll("\\\\", "/");
            this.extentLogger.addScreenCaptureFromPath(path);
        } else {
            System.out.println("Skipping screen shot capture as Running test locally");
        }
    }

    public void captureScreenshot(final WebDriver driver, final String screenShotTitle) {
        if (this.isSuite) {
            info("Screen shot Captured:" + screenShotTitle);
            this.extentLogger.addScreenCaptureFromPath(getScreenshotPath(driver), screenShotTitle);
        } else {
            System.out.println("Skipping screen shot capture as Running test locally");
        }
    }

    public String getTestName() {
        return ((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_NAME));
    }

    public String getTestClassName() {
        return ((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_CLASS_NAME));
    }

    @SuppressWarnings("Duplicates")
    private String getScreenshotPath() {
        final WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
        final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        final String destinationFilePath = ReportManager.REPORT_DIRECTORY_LOCATION + "\\" + LocalDateTime.now() + "_" + ThreadFactory.getID() + ".png";
        try {
            final File destFile = new File(destinationFilePath);
            FileUtils.moveFile(scrFile, destFile);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return destinationFilePath;
    }

    @SuppressWarnings("Duplicates")
    private String getScreenshotPath(final WebDriver driver) {
        final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        final String destinationFilePath = ReportManager.REPORT_DIRECTORY_LOCATION + "\\" + LocalDateTime.now() + "_" + ThreadFactory.getID() + ".png";
        try {
            final File destFile = new File(destinationFilePath);
            FileUtils.moveFile(scrFile, destFile);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return destinationFilePath;
    }

    public <T extends IMaintainEventNames> LoggingEvent startEvent(final T eventName) {
        final LoggingEvent event = LoggingEvent.newEvent(eventName.getEventName());
        event.startEvent();
        if (System.getProperty("logEventsInReports", "false").equalsIgnoreCase("true")) {
            print(event.getCurrentStateMessage());
        }

        ReportManager.getClass(getTestClassName()).addEvent(eventName, event);
        return event;
    }

    public <T extends IMaintainEventNames> void logInstantEvent(final T eventName, final String eventMessage) {
        LoggingEvent.newEvent(eventName.getEventName()).updateEvent(eventMessage);
    }

    public String endEvent(final LoggingEvent event, final String... additionalTags) {
        if (event.getCurrentState() == LogEventState.STARTED) {
            event.endEvent(additionalTags);
        }
        if (System.getProperty("logEventsInReports", "false").equalsIgnoreCase("true")) {
            print(event.getCurrentStateMessage());
        }

        return event.getCurrentStateMessage();
    }

    public void enableEventLogging() {
        System.setProperty("logEventsInReports", "true");

    }

    public void disableEventLogging() {
        System.setProperty("logEventsInReports", "false");
    }

    public <T extends IMaintainEventNames> LoggingEvent getEvent(final T eventName) {
        return ReportManager.getClass(getTestClassName()).getEvents().get(eventName);
    }
}
