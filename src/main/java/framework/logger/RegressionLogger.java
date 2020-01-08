package framework.logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import framework.Listener;
import framework.ReportManager;
import framework.constants.StringConstants;
import framework.webdriver.BrowserFactory;
import framework.webdriver.utils.BrowserStorageAccess;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class RegressionLogger {

    private Logger logger;
    private ExtentTest extentLogger;
    private boolean isSuite;
    private String testName;
    private String testClassName;

    public RegressionLogger(Logger logger, ExtentTest extentLogger, boolean isSuite) {
        this.logger = logger;
        if (this.logger == null) {
            this.logger = LogManager.getLogger("LocalRegressionLogs-" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        }
        this.extentLogger = extentLogger;
        this.isSuite = isSuite;
    }

    public void debug(Object message) {
        if (isSuite) {
            logger.debug(message);
            extentLogger.log(Status.DEBUG, message.toString());
        } else {
            System.out.println(message);
        }

    }

    public void debug(Object message, Throwable e) {
        if (isSuite) {
            logger.debug(message, e);
            extentLogger.log(Status.DEBUG, message.toString());
            extentLogger.log(Status.DEBUG, e);
        } else {
            System.out.println(message);
            e.printStackTrace();
        }

    }


    public void info(Object message) {
        if (isSuite) {
            logger.info(message);
            extentLogger.log(Status.INFO, message.toString());
        } else {
            System.out.println(message);
        }

    }

    public void info(Object message, Throwable e) {
        if (isSuite) {
            logger.info(message, e);
            extentLogger.log(Status.INFO, message.toString());
            extentLogger.log(Status.INFO, e);
        } else {
            System.out.println(message);
            e.printStackTrace();
        }

    }


    public void warn(Object message) {
        if (isSuite) {
            logger.warn(message);
            extentLogger.log(Status.WARNING, message.toString());
        } else {
            System.out.println(message);
        }

    }

    public void warn(Object message, Throwable e) {
        if (isSuite) {
            logger.warn(message, e);
            extentLogger.log(Status.WARNING, message.toString());
            extentLogger.log(Status.WARNING, e);
        } else {
            System.out.println(message);
            e.printStackTrace();
        }

    }

    public void fatal(Object message) {
        if (isSuite) {
            logger.fatal(message);
            extentLogger.log(Status.FATAL, message.toString());
        } else {
            System.out.println(message);
        }

    }

    public void fatal(Object message, Throwable e) {
        if (isSuite) {
            logger.fatal(message, e);
            extentLogger.log(Status.FATAL, message.toString());
            extentLogger.log(Status.FATAL, e);
        } else {
            System.out.println(message);
            e.printStackTrace();
        }

    }

    public void trace(Object message) {
        if (isSuite) {
            logger.trace(message);
            extentLogger.log(Status.DEBUG, message.toString());
        } else {
            System.out.println(message);
        }

    }

    public void trace(Object message, Throwable e) {
        if (isSuite) {
            logger.trace(message, e);
            extentLogger.log(Status.DEBUG, message.toString());
            extentLogger.log(Status.DEBUG, e);
        } else {
            System.out.println(message);
            e.printStackTrace();
        }

    }

    public void error(Object message) {
        if (isSuite) {
            logger.error(message);
            extentLogger.log(Status.ERROR, message.toString());
        } else {
            System.out.println(message);
        }

    }

    public void error(Object message, Throwable e) {
        if (isSuite) {
            logger.error(message, e);
            extentLogger.log(Status.ERROR, message.toString());
            extentLogger.log(Status.ERROR, e);
        } else {
            System.out.println(message);
            e.printStackTrace();
        }

    }

    public void captureScreenshot(String screenShotTitle) {
        if(this.isSuite){
            try {
                info("Screen shot Captured:" + screenShotTitle);
                this.extentLogger.addScreenCaptureFromPath(getScreenshotPath(), screenShotTitle);
            } catch (IOException e) {
                warn(screenShotTitle);
                warn("Could not save on demand screen shot", e);
            }
        } else {
            System.out.println("Skipping screen shot capture as Running test locally");
        }

    }


    public String getTestName(){
        return BrowserStorageAccess.getInstance().get(StringConstants.TEST_NAME);
    }

    public String getTestClassName(){
        return BrowserStorageAccess.getInstance().get(StringConstants.TEST_CLASS_NAME);
    }

    @SuppressWarnings("Duplicates")
    private String getScreenshotPath() {
        WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationFilePath = ReportManager.REPORT_DIRECTORY_LOCATION + "\\" + LocalDateTime.now()+"_"+Thread.currentThread().getId() + ".png";
        try {
            File destFile = new File(destinationFilePath);
            FileUtils.moveFile(scrFile, destFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return destinationFilePath;
    }

    public synchronized static RegressionLogger getTestLogger(){
        String testName = BrowserStorageAccess.getInstance().get(StringConstants.TEST_NAME);
        ExtentTest extentTest = ReportManager.getTest(testName);
        return new RegressionLogger(Listener.logger, extentTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getTestClassLogger(){
        ExtentTest extentClassTest = ReportManager.getClass(BrowserStorageAccess.getInstance().get(StringConstants.TEST_CLASS_NAME));
        return new RegressionLogger(Listener.logger, extentClassTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getXMLTestLogger(){
        ExtentTest extentXMLTest = ReportManager.getXMLTest(BrowserStorageAccess.getInstance().get(StringConstants.XML_TEST_NAME));
        return new RegressionLogger(Listener.logger, extentXMLTest, ReportManager.isInitiated());
    }
}
