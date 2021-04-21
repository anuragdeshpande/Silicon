package framework.logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import framework.ReportManager;
import framework.constants.StringConstants;
import framework.customExceptions.UnexpectedTerminationException;
import framework.reports.models.TestDetailsDTO;
import framework.webdriver.BrowserFactory;
import framework.webdriver.ThreadFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class RegressionLogger {

    private final ExtentTest extentLogger;
    private final boolean isSuite;

    public RegressionLogger(ExtentTest extentLogger, boolean isSuite) {
        this.extentLogger = extentLogger;
        this.isSuite = isSuite;
    }

    public synchronized static RegressionLogger getFirstAvailableLogger(){
        String testName = ((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_NAME));
        String className = ((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_CLASS_NAME));
        String xmlName = ((String) ThreadFactory.getInstance().getStorage().get(StringConstants.XML_TEST_NAME));
        TestDetailsDTO dto = new TestDetailsDTO();
        dto.setClassName(className);
        dto.setTestName(testName);

        if(ReportManager.getTest(dto) != null){
            return getTestLogger();
        }

        if(ReportManager.getClass(className) != null){
            return getTestClassLogger();
        }

        if(ReportManager.getXMLTest(xmlName) != null){
            return getXMLTestLogger();
        }

        throw new UnexpectedTerminationException("Could not find a suitable logger at the moment (XMLTestLogger: "+xmlName+", ClassTestLogger: "+className+", TestLogger: "+dto.getTestName()+"), please check if the test is initialized.");

    }

    public synchronized static RegressionLogger getTestLogger(String testMethodName, String testClassName) {
        TestDetailsDTO dto = new TestDetailsDTO();
        dto.setTestName(testMethodName);
        dto.setClassName(testClassName);
        ExtentTest extentTest = ReportManager.getTest(dto);
        return new RegressionLogger(extentTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getTestLogger() {
        TestDetailsDTO dto = new TestDetailsDTO();
        dto.setTestName(((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_NAME)));
        dto.setClassName(((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_CLASS_NAME)));
        ExtentTest extentTest = ReportManager.getTest(dto);
        return new RegressionLogger(extentTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getTestClassLogger(String testClassName) {
        ExtentTest extentClassTest = ReportManager.getClass(testClassName);
        return new RegressionLogger(extentClassTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getTestClassLogger() {
        ExtentTest extentClassTest = ReportManager.getClass(((String) ThreadFactory.getInstance().getStorage().get(StringConstants.TEST_CLASS_NAME)));
        return new RegressionLogger(extentClassTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getXMLTestLogger(String xmlTestName) {
        ExtentTest extentXMLTest = ReportManager.getXMLTest(xmlTestName);
        return new RegressionLogger(extentXMLTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getXMLTestLogger() {
        ExtentTest extentXMLTest = ReportManager.getXMLTest(((String) ThreadFactory.getInstance().getStorage().get(StringConstants.XML_TEST_NAME)));
        return new RegressionLogger(extentXMLTest, ReportManager.isInitiated());
    }

    public void info(Object message) {
        if (isSuite) {
            extentLogger.log(Status.INFO, message.toString());
        } else {
            System.out.println(message);
        }

    }

    public void info(Object message, Throwable e) {
        if (isSuite) {
            extentLogger.log(Status.INFO, message.toString());
            extentLogger.log(Status.INFO, e);
        } else {
            System.out.println(message);
            e.printStackTrace();
        }

    }

    public void fail(Object message) {
        if (isSuite) {
            extentLogger.log(Status.FAIL, message.toString());
        } else {
            System.out.println(message);
        }

    }

    public void fail(Object message, Throwable e) {
        if (isSuite) {
            extentLogger.log(Status.FAIL, message.toString());
            extentLogger.log(Status.FAIL, e);
        } else {
            System.out.println(message);
            e.printStackTrace();
        }

    }

    public void warn(Object message) {
        if (isSuite) {
            extentLogger.log(Status.WARNING, message.toString());
        } else {
            System.out.println(message);
        }

    }

    public void addTag(String tagName) {
        if (isSuite) {
            extentLogger.log(Status.INFO, "Tagging: " + tagName);
            extentLogger.assignCategory(tagName);
        } else {
            System.out.println("Could not tag since the run is not a suite");
        }
    }

    public void warn(Object message, Throwable e) {
        if (isSuite) {
            extentLogger.log(Status.WARNING, message.toString());
            extentLogger.log(Status.WARNING, e);
        } else {
            System.out.println(message);
            e.printStackTrace();
        }

    }

    public void captureScreenshot(String screenShotTitle) {
        if (this.isSuite) {
            info("Screen shot Captured:" + screenShotTitle);
            this.extentLogger.addScreenCaptureFromPath(getScreenshotPath(), screenShotTitle);
        } else {
            System.out.println("Skipping screen shot capture as Running test locally");
        }
    }

    public void captureScreenshot(WebDriver driver, String screenShotTitle) {
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
        WebDriver driver = BrowserFactory.getCurrentBrowser().getDriver();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationFilePath = ReportManager.REPORT_DIRECTORY_LOCATION + "\\" + LocalDateTime.now() + "_" + ThreadFactory.getID() + ".png";
        try {
            File destFile = new File(destinationFilePath);
            FileUtils.moveFile(scrFile, destFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return destinationFilePath;
    }

    @SuppressWarnings("Duplicates")
    private String getScreenshotPath(WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationFilePath = ReportManager.REPORT_DIRECTORY_LOCATION + "\\" + LocalDateTime.now() + "_" + ThreadFactory.getID() + ".png";
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
