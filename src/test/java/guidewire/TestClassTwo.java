package guidewire;

import framework.BaseOperations;
import framework.annotations.AutomatedTest;
import framework.annotations.AutomationHistory;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;
import org.testng.annotations.Test;

public class TestClassTwo extends BaseOperations {

    @AutomatedTest(Author = "Denver Hysell", Team = "ACES", Centers = {"GuidewireCenter"}, FeatureNumber = "F42", PI = "PI5", Iteration = "I2", StoryOrDefectNumber = "US155559", Themes = {"Chickens"})
    @AutomationHistory(StoryOrDefectNumbers = {"US1555556", "US1555557", "US155558"})
    @Test
    public void testFive() {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        interact.getDriver().manage().window().maximize();
        interact.getDriver().get("http://cc8dev/cc/GuidewireCenter.do");
    }

    @AutomatedTest(Author = "Denver Hysell", Team = "ACES", Centers = {"GuidewireCenter"}, FeatureNumber = "F42", PI = "PI5", Iteration = "I2", StoryOrDefectNumber = "US155559", Themes = {"Chickens"})
    @AutomationHistory(StoryOrDefectNumbers = {"US1555556", "US1555557", "US155558"})
    @Test
    public void testSix() {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        interact.getDriver().manage().window().maximize();
        interact.getDriver().get("http://cc8uat/cc/GuidewireCenter.do");
    }

    @AutomatedTest(Author = "Denver Hysell", Team = "ACES", Centers = {"GuidewireCenter"}, FeatureNumber = "F42", PI = "PI5", Iteration = "I2", StoryOrDefectNumber = "US155559", Themes = {"Chickens"})
    @AutomationHistory(StoryOrDefectNumbers = {"US1555556", "US1555557", "US155558"})
    @Test
    public void testSeven() {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        interact.getDriver().manage().window().maximize();
        interact.getDriver().get("http://cc8dev3/cc/GuidewireCenter.do");
    }
}
