package framework.applications.gw;

import framework.applications.Application;
import framework.applications.gw.responsibilities.*;
import framework.constants.ReactionTime;
import framework.database.ConnectionManager;
import framework.elements.alertwindow.UIConfirmationWindow;
import framework.enums.LogLevel;
import framework.environmentResolution.Environment;
import framework.guidewire.GuidewireInteract;
import framework.guidewire.pages.GWIDs;
import framework.logger.RegressionLogger;
import framework.webdriver.BrowserFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.openqa.selenium.Keys;
import org.testng.Assert;

abstract public class GuidewireCenter extends Application implements IGWOperations, ILogManagement,
        IBatchServer, ITempLogin, IErrorHandling, IGWPages {

    private String overrideEnvironmentURL = null;

    protected GuidewireInteract interact;
    protected Environment environment;
    protected QueryRunner queryRunner;
    protected String currentUsername;
    protected String currentPassword;

    public GuidewireCenter() {
        super();
        this.interact = BrowserFactory.getCurrentGuidewireBrowser();

    }

    @Override
    public GuidewireInteract getInteractObject() {
        return this.interact;
    }

    @Override
    public boolean hasErrorMessageOnScreen() {
        String errorMessage = this.interact.withOptionalElement(GWIDs.ERROR_MESSAGE, ReactionTime.MOMENTARY).screenGrab();
        return !errorMessage.equalsIgnoreCase("");
    }

    @Override
    public String getErrorMessageOnScreen() {
        return this.interact.withOptionalElement(GWIDs.ERROR_MESSAGE, ReactionTime.IMMEDIATE).screenGrab();
    }

    @Override
    public void overrideEnvironmentURL(String url) {
        this.overrideEnvironmentURL = url;
    }

    @Override
    public void setLogLevel(String loggerName, LogLevel logLevel){
        ServerPages serverPages = openServerPages();
        serverPages.clickSetLogLevel();
        GuidewireInteract interact = getInteractObject();
        interact.withSelectBox(GWIDs.ServerPages.ServerTools.LogLevel.LOGGERS).select(loggerName);
        interact.withSelectBox(GWIDs.ServerPages.ServerTools.LogLevel.LEVELS).select(logLevel.name());
        if(interact.withOptionalElement(GWIDs.ServerPages.ServerTools.LogLevel.SET_LEVEL, ReactionTime.MOMENTARY).isPresent()){
            interact.withElement(GWIDs.ServerPages.ServerTools.LogLevel.SET_LEVEL).click();
        } else {
            System.out.println("System already at "+logLevel.name()+" for Logger: "+ loggerName);
        }

        serverPages.returnToCenter();

    }

    @Override
    public ServerPages openServerPages() {
        GuidewireInteract interact = getInteractObject();
        interact.withElement(GWIDs.QUICK_JUMP).click();
        interact.pressKeys(Keys.ALT, Keys.SHIFT, "t");
        return new ServerPages(this);
    }

    @Override
    public void login(String userName, String password) {
        _login(userName, password);
        this.currentUsername = userName;
        this.currentPassword = password;
    }

    @Override
    public void tempLogin(String userName, String password) {
        _login(userName, password);
    }

    @Override
    public void logout() {
        GuidewireInteract interact = getInteractObject();
        interact.withElement(GWIDs.SETTINGS_COG).click();
        interact.withElement(GWIDs.SettingsCog.LOGOUT).click();

        UIConfirmationWindow uiConfirmationWindow = interact.withOptionalConfirmationWindow(ReactionTime.ONE_SECOND);
        if (uiConfirmationWindow.isPresent() && !uiConfirmationWindow.getElement().getAttribute("class").endsWith("x-hide-offsets")) {
            uiConfirmationWindow.clickOkButton();
        }
    }

    public String getOverrideEnvironmentURL() {
        return overrideEnvironmentURL;
    }

    @Override
    public void openEnvironment(Environment environment) {
        // Clearing any existing Banner Messages
        interact.withDOM().clearBannerMessage();
        if(!interact.getDriver().getCurrentUrl().equalsIgnoreCase("data:,")){
            /* flushing out browser */

            // refresh browser
            interact.getDriver().navigate().refresh();
            // logging out of the current environment if it is logged in
            if(interact.withOptionalElement(GWIDs.SETTINGS_COG, ReactionTime.IMMEDIATE).isPresent()){
                logout();
            }

            /* end flush */
        }

        this.environment = environment;
        this.queryRunner = ConnectionManager.getDBConnectionTo(this.environment);
        String url = getOverrideEnvironmentURL() != null ? getOverrideEnvironmentURL() : environment.getEnvironmentUrl();
        // initiating db. Doing it here so that by the time the browser comes up the connection is ready for load.
        interact.getDriver().get(url);
    }

    public RegressionLogger getLogger(){
        return RegressionLogger.getTestLogger();
    }

    private void _login(String username, String password){
        if(this.environment == null){
            Assert.fail("Please call openDefaultEnvironment() or openEnvironment() to open the application");
        }
        interact.withTextbox(GWIDs.Login.USER_NAME).fill(username);
        interact.withTextbox(GWIDs.Login.PASSWORD).fill(password);
        interact.withElement(GWIDs.Login.LOGIN).click();
    }
}
