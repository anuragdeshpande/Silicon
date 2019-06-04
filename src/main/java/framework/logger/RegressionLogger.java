package framework.logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import framework.ReportManager;
import framework.webdriver.BrowserFactory;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class RegressionLogger {

    private Logger logger;
    private ExtentTest extentLogger;
    private boolean isSuite;

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
        try {
            info("Screen shot Captured:" + screenShotTitle);
            this.extentLogger.addScreenCaptureFromPath(getScreenshotPath(), screenShotTitle);
        } catch (IOException e) {
            warn(screenShotTitle);
            warn("Could not save on demand screen shot", e);
        }
    }

    @SuppressWarnings("Duplicates")
    private String getScreenshotPath() {
        WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationFilePath = ReportManager.REPORT_DIRECTORY_LOCATION + "\\" + LocalDate.now() + ".png";
        try {
            File destFile = new File(destinationFilePath);
            FileUtils.moveFile(scrFile, destFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return destinationFilePath;
    }
}
