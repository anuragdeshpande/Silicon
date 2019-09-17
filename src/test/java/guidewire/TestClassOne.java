package guidewire;

import annotations.AutomatedTest;
import annotations.AutomationHistory;
import framework.BaseOperations;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestClassOne extends BaseOperations {

   @BeforeTest
   public void test(){
   }

    @AutomatedTest(Author = "Denver Hysell", Team = "ACES", Centers = {"GuidewireCenter"}, FeatureNumber = "F42", PI = "PI5", Iteration = "I2", StoryOrDefectNumber = "US155559", Themes = {"Chickens"})
    @AutomationHistory(StoryOrDefectNumbers = {"US1555556", "US1555557", "US155558"})
    @Test
    public void testTwo() {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        interact.getDriver().manage().window().maximize();
        interact.getDriver().get("http://cc8dev/cc/GuidewireCenter.do");
    }

    @AutomatedTest(Author = "Denver Hysell", Team = "ACES", Centers = {"GuidewireCenter"}, FeatureNumber = "F42", PI = "PI5", Iteration = "I2", StoryOrDefectNumber = "US155559", Themes = {"Chickens"})
    @AutomationHistory(StoryOrDefectNumbers = {"US1555556", "US1555557", "US155558"})
    @Test(dependsOnMethods = "testTwo")
    public void testThree() {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        interact.getDriver().manage().window().maximize();
        interact.getDriver().get("http://cc8uat/cc/GuidewireCenter.do");
    }

    @AutomatedTest(Author = "Denver Hysell", Team = "ACES", Centers = {"GuidewireCenter"}, FeatureNumber = "F42", PI = "PI5", Iteration = "I2", StoryOrDefectNumber = "US155559", Themes = {"Chickens"})
    @AutomationHistory(StoryOrDefectNumbers = {"US1555556", "US1555557", "US155558"})
    @Test(dependsOnMethods = "testThree")
    public void testFour() {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        interact.getDriver().manage().window().maximize();
        interact.getDriver().get("http://cc8dev3/cc/GuidewireCenter.do");
    }
}
