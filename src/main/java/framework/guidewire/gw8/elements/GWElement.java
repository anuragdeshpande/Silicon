package framework.guidewire.gw8.elements;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.enums.ElementType;
import framework.elements.ui_element.UIElement;
import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
                new WebDriverWait(driver, 1)
                        .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body[contains(@class, 'x-mask')]")));

                reactionTime = ReactionTime.STANDARD_WAIT_TIME;
                driver.manage().timeouts().implicitlyWait(reactionTime.getTime(), reactionTime.getTimeUnit());

                // clicking again
                this.getElement().click();

            }

            System.out.println("Clicked Element" + identifier.getReference());

            // closing the warning window if Identifier is marked to check it
            if (identifier.shouldCheckForWarning()) {
                GWElement closeButton = new GWElement(new Identifier(By.linkText("Close"), ElementType.BUTTON), ReactionTime.THREE_SECONDS);
                if (closeButton.isPresent()) {
                    closeButton.getElement().click();
                    this.getElement().click();
                }
            }

            // checking for error messages after clicking
            if (GuidewireInteract.hasErrorMessageOnScreen()) {
                Assert.fail("Error Message On Screen: " + GuidewireInteract.getErrorMessageFromScreen());
            }


        } else {
            Assert.fail("Element is not Clickable");
        }
    }
}
