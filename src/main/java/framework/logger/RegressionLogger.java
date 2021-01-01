package framework.logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import framework.ReportManager;
import framework.constants.StringConstants;
import framework.customExceptions.IncorrectCallException;
import framework.reports.models.TestDetailsDTO;
import framework.webdriver.BrowserFactory;
import framework.webdriver.utils.BrowserStorageAccess;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.NotImplementedException;
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

    public void addTag(String tagName){
        if(isSuite){
            extentLogger.log(Status.INFO, "Tagging: "+tagName);
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
        if(this.isSuite){
            info("Screen shot Captured:" + screenShotTitle);
            this.extentLogger.addScreenCaptureFromPath(getScreenshotPath(), screenShotTitle);
        } else {
            System.out.println("Skipping screen shot capture as Running test locally");
        }

    }


    public String getTestName(){
        if(System.getProperty("LithiumSafe", "false").equalsIgnoreCase("true")) {
            return BrowserStorageAccess.getInstance().get(StringConstants.TEST_NAME);
        } else {
            throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
        }
    }

    public String getTestClassName(){
        if(System.getProperty("LithiumSafe", "false").equalsIgnoreCase("true")) {
            return BrowserStorageAccess.getInstance().get(StringConstants.TEST_CLASS_NAME);
        } else {
            throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
        }
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

    public synchronized static RegressionLogger getTestLogger(String testMethodName, String testClassName){
        TestDetailsDTO dto = new TestDetailsDTO();
        dto.setTestName(testMethodName);
        dto.setClassName(testClassName);
        ExtentTest extentTest = ReportManager.getTest(dto);
        return new RegressionLogger(extentTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getTestLogger(){
        if(System.getProperty("LithiumSafe", "false").equalsIgnoreCase("true")){
            TestDetailsDTO dto = new TestDetailsDTO();
            dto.setTestName(BrowserStorageAccess.getInstance().get(StringConstants.TEST_NAME));
            dto.setClassName(BrowserStorageAccess.getInstance().get(StringConstants.TEST_CLASS_NAME));
            ExtentTest extentTest = ReportManager.getTest(dto);
            return new RegressionLogger(extentTest, ReportManager.isInitiated());
        } else {
            throw new IncorrectCallException("Project is marked as unsafe for Lithium Framework. Please call getTestLogger(String testMethodName, String testClassName)");
        }

    }

    public synchronized static RegressionLogger getTestClassLogger(String testClassName){
        ExtentTest extentClassTest = ReportManager.getClass(testClassName);
        return new RegressionLogger(extentClassTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getTestClassLogger(){
        if(System.getProperty("LithiumSafe", "false").equalsIgnoreCase("true")) {
            ExtentTest extentClassTest = ReportManager.getClass(BrowserStorageAccess.getInstance().get(StringConstants.TEST_CLASS_NAME));
            return new RegressionLogger(extentClassTest, ReportManager.isInitiated());
        } else {
            throw new IncorrectCallException("Project is marked as unsafe for Lithium Framework. Please call getTestClassLogger(String testClassName)");
        }
    }

    public synchronized static RegressionLogger getXMLTestLogger(String xmlTestName){
        ExtentTest extentXMLTest = ReportManager.getXMLTest(xmlTestName);
        return new RegressionLogger(extentXMLTest, ReportManager.isInitiated());
    }

    public synchronized static RegressionLogger getXMLTestLogger(){
        if(System.getProperty("LithiumSafe", "false").equalsIgnoreCase("true")) {
            ExtentTest extentXMLTest = ReportManager.getXMLTest(BrowserStorageAccess.getInstance().get(StringConstants.XML_TEST_NAME));
            return new RegressionLogger(extentXMLTest, ReportManager.isInitiated());
        } else {
            throw new IncorrectCallException("Project is marked as unsafe for Lithium Framework. Please call getXMLTestLogger(String xmlTestName)");
        }
    }
}
