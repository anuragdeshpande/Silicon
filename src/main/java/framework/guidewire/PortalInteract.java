package framework.guidewire;

import framework.applications.portals.elements.PortalConfirmationWindow;
import framework.applications.portals.elements.tables.PortalTable;
import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.checkbox.UICheckbox;
import framework.logger.RegressionLogger;
import framework.utils.PropertiesFileLoader;
import framework.webdriver.BrowserFactory;
import framework.webdriver.Interact;
import framework.webdriver.PauseTest;
import framework.webdriver.interactionContracts.ICanInteractWithTable;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Properties;

public class PortalInteract extends Interact implements ICanInteractWithTable<PortalTable> {

    public PortalInteract(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isGuidewire() {
        return true;
    }

    @Override
    public void setGuidewire(boolean guidewire) {
        throw new IllegalStateException("Cannot change \"Is Guidewire\" setting for this product. " +
                "The value is always set to true.");
    }

    @Override
    public UICheckbox withCheckbox(Identifier identifier) {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public PortalConfirmationWindow withConfirmationWindow() {
        return new PortalConfirmationWindow(new Identifier(By.xpath("//*[contains(@class, 'gw-modal-body')]//*[contains(@class, 'gw-message')]")),
                ReactionTime.THREE_SECONDS);
    }

    @Override
    public PortalConfirmationWindow withOptionalConfirmationWindow(ReactionTime reactionTime) {
        return new PortalConfirmationWindow(new Identifier(By.xpath("//*[contains(@class, 'gw-modal-body')]//*[contains(@class, 'gw-message')]")),
                reactionTime);
    }

    @Override
    public PortalTable withTable(Identifier identifier) {
        return new PortalTable(identifier);
    }

    @Override
    public PortalTable withOptionalTable(Identifier identifier, ReactionTime reactionTime) {
        return new PortalTable(identifier, reactionTime);
    }


    public static void waitForPageLoad(){
        Properties configProperties = PropertiesFileLoader.load("config.properties");
        int timeout = 15;
        String timeoutStr = configProperties.getProperty("PortalPageTimeout");
        if(timeoutStr != null){
            timeout = Integer.parseInt(timeoutStr);
        }
        PortalInteract interact = BrowserFactory.getCurrentPortalsBrowser();
        try{
            interact.withDOM().injectInfoMessage("Waiting for Home page to load");
            PauseTest.createSpecialInstance(timeout, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("gw-progress")), "Page is loading");
        }catch (TimeoutException te){
            RegressionLogger.getTestLogger().captureScreenshot("Page is still loading");
            interact.withDOM().injectDangerMessage("Page did not load in under "+timeout+" seconds");
            RegressionLogger.getTestLogger().captureScreenshot("Page loading did not meet SLA");
            throw new TimeoutException("Page is still loading");
        }
    }

    public static boolean hasErrorMessageOnScreen(){
        PortalInteract interact = BrowserFactory.getCurrentPortalsBrowser();
        return interact
                .withOptionalElement(new Identifier(By.xpath("//div[contains(@class, 'gw-modal-body')]//i[contains(@class, 'gw-modal-state-error-circle')]"))
                        , ReactionTime.IMMEDIATE).isPresent();
    }

    public static void closeErrorMessage(){
        PortalInteract interact = BrowserFactory.getCurrentPortalsBrowser();
        interact.withElement(new Identifier(By.xpath("//div[contains(@class, 'gw-modal-footer')]//button[contains(@class, 'gw-btn-primary')]"))).click();
        try{
            PauseTest.createInstance().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'gw-modal-body')]//i[contains(@class, 'gw-modal-state-error-circle')]")), "Dismissing Error Message");
        } catch (Exception e){
            // do nothing
        }
    }
}
