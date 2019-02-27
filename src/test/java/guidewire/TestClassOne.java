package guidewire;

import framework.BaseOperations;
import framework.annotations.AutomatedTest;
import framework.annotations.AutomationHistory;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestClassOne extends BaseOperations {

    @BeforeMethod
    public void testMethod(){
        logger.info("This is the before method for the test");
    }

    @AutomatedTest(Author = "Denver Hysell", Team = "ACES", Centers = {"GuidewireCenter"}, FeatureNumber = "F42", PI = "PI5", Iteration = "I2", StoryOrDefectNumber = "US155559", Themes = {"Chickens"})
    @AutomationHistory(StoryOrDefectNumbers = {"US1555556", "US1555557", "US155558"})
    @Test
    public void testTwo() {
        GuidewireInteract interact = BrowserFactory.getNewGuidewireChromeBrowser();
        interact.getDriver().manage().window().maximize();
        interact.getDriver().get("http://cc8uat/cc/GuidewireCenter.do");
        logger.trace("this is trace message");
        logger.info("this is info message");
        logger.warn("this is warning message");
        logger.error("This Test is about to fail");
        logger.fatal("this is fatal message");
        logger.debug("this is debug message");
        Assert.fail("Test Fail");
    }
}
