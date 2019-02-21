package guidewire;

import framework.BaseOperations;
import framework.annotations.AutomatedTest;
import framework.annotations.AutomationHistory;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestClassOne extends BaseOperations {

    private static final Logger _logger = LogManager.getLogger(TestClassOne.class.getName());

    @AutomatedTest(Author = "Denver Hysell", Team = "ACES", Centers = {"ClaimCenter"}, FeatureNumber = "F42", PI = "PI5", Iteration = "I1", StoryOrDefectNumber = "US155555", Themes = {"Turkeys"})
    @AutomationHistory(StoryOrDefectNumbers = {"US1555553", "US1555554"})
    @Test
    public void testOne() {

    }

    @AutomatedTest(Author = "Denver Hysell", Team = "ACES", Centers = {"ClaimCenter"}, FeatureNumber = "F42", PI = "PI5", Iteration = "I2", StoryOrDefectNumber = "US155559", Themes = {"Chickens"})
    @AutomationHistory(StoryOrDefectNumbers = {"US1555556", "US1555557", "US155558"})
    @Test
    public void testTwo() {
        GuidewireInteract interact = BrowserFactory.getNewGuidewireChromeBrowser();
        interact.getDriver().manage().window().maximize();
        interact.getDriver().get("http://cc8uat/cc/ClaimCenter.do");
        Assert.fail("Test Fail");
    }

    @Test
    public void testThree() {

    }

}
