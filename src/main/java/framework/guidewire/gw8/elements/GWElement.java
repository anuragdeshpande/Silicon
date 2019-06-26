package framework.guidewire.gw8.elements;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;
import framework.webdriver.PauseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GWElement extends UIElement {
    public GWElement(Identifier identifier) {
        super(identifier);
    }

    /**
     * \
     * <p>
     * Use of this constructor automatically assumes the Select Box in question is
     * optional and will wait upto the reaction time passed as parameter
     *
     * @param identifier   element Identifier
     * @param reactionTime time to wait
     */
    public GWElement(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }

    protected GWElement(WebElement element) {
        super(element);
    }

    @Override
    public void click() {
        if (this.isPresent()) {
            try{
                this.getElement().click();
            } catch (ElementClickInterceptedException cie){

                System.out.println("Overlay element found. Waiting for a second or less for overlay to clear.");
                // there is a temporary overlay, wait for the overlay to disappear and then click again.
                WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
                ReactionTime reactionTime = ReactionTime.IMMEDIATE;
                driver.manage().timeouts().implicitlyWait(reactionTime.getTime(),reactionTime.getTimeUnit());
                try{
                    new WebDriverWait(driver, 3)
                            .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body[contains(@class, 'x-mask')]")));
                } catch (TimeoutException e){
                    Assert.fail("Guidewire Application is taking over 3 seconds to respond to click: Aborting tests");
                }

                reactionTime = ReactionTime.STANDARD_WAIT_TIME;
                driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());

                // clicking again
                this.getElement().click();

            }

            System.out.println("Clicked Element" + identifier.getReference());

            // closing the warning window if Identifier is marked to check it
            if (identifier.shouldCheckForWarning()) {
                closeWarningWindow();
                try{
                    PauseTest.createSpecialInstance(3, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("southPanel")));
                } catch (Exception e){
                    // do nothing. just the south panel did not show up. so no warning windows.
                }

            }

            // checking for error messages after clicking
            if (GuidewireInteract.hasErrorMessageOnScreen()) {
                if(identifier.shouldCheckForWarning()){
                    closeWarningWindow();
                }
                Assert.fail("Error Message On Screen: " + GuidewireInteract.getErrorMessageFromScreen());
            }


        } else {
            Assert.fail("Element is not Clickable");
        }
    }

    private void closeWarningWindow(){
        if(GuidewireInteract.hasErrorMessageOnScreen()) {
            GWElement closeButton = new GWElement(new Identifier(By.linkText("Close")), ReactionTime.IMMEDIATE);
            GWElement clearButton = new GWElement(new Identifier(By.linkText("Clear")), ReactionTime.IMMEDIATE);
            if (closeButton.isPresent()) {
                closeButton.getElement().click();
                this.getElement().click();
            }

            if (clearButton.isPresent()) {
                clearButton.getElement().click();
                this.getElement().click();
            }
        }
    }
}
