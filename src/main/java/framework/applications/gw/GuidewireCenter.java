package framework.applications.gw;

import framework.applications.Application;
import framework.applications.gw.responsibilities.gwCenter.*;
import framework.constants.ReactionTime;
import framework.database.ConnectionManager;
import framework.elements.alertwindow.UIConfirmationWindow;
import framework.enums.LogLevel;
import framework.environmentResolution.Environment;
import framework.guidewire.GuidewireInteract;
import framework.guidewire.pages.GWIDs;
import framework.integrations.gwServices.debugToolsAPI.ab.ABDebugToolsAPI;
import framework.integrations.gwServices.debugToolsAPI.ab.ABDebugToolsAPIPortType;
import framework.integrations.gwServices.debugToolsAPI.bc.BCDebugToolsAPI;
import framework.integrations.gwServices.debugToolsAPI.bc.BCDebugToolsAPIPortType;
import framework.integrations.gwServices.debugToolsAPI.cc.CCDebugToolsAPI;
import framework.integrations.gwServices.debugToolsAPI.cc.CCDebugToolsAPIPortType;
import framework.integrations.gwServices.debugToolsAPI.pc.PCDebugToolsAPI;
import framework.integrations.gwServices.debugToolsAPI.pc.PCDebugToolsAPIPortType;
import framework.integrations.gwServices.debugToolsAPI.pc.WsiAuthenticationException_Exception;
import framework.logger.RegressionLogger;
import framework.webdriver.BrowserFactory;
import framework.webdriver.PauseTest;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.Validate;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.Map;

abstract public class GuidewireCenter extends Application implements IGWOperations, ILogManagement,
        IBatchServer, ITempLogin, IErrorHandling, IGWPages, ICanMoveClock {

    private String overrideEnvironmentURL = null;

    protected Environment environment;
    protected QueryRunner queryRunner;
    protected String currentUsername;
    protected String currentPassword;

    public GuidewireCenter() {
        super();
    }

    @Override
    public GuidewireInteract getInteractObject() {
        return BrowserFactory.getCurrentGuidewireBrowser();
    }

    @Override
    public boolean hasErrorMessageOnScreen() {
        String errorMessage = getInteractObject().withOptionalElement(GWIDs.ERROR_MESSAGE, ReactionTime.MOMENTARY).screenGrab();
        return !errorMessage.equalsIgnoreCase("");
    }

    @Override
    public String getErrorMessageOnScreen() {
        return getInteractObject().withOptionalElement(GWIDs.ERROR_MESSAGE, ReactionTime.IMMEDIATE).screenGrab();
    }

    @Override
    public void overrideEnvironmentURL(String url) {
        this.overrideEnvironmentURL = url;
    }

    @Override
    public void setLogLevel(String loggerName, LogLevel logLevel) {
        ServerPages serverPages = openServerPages();
        serverPages.clickSetLogLevel();
        GuidewireInteract interact = getInteractObject();
        interact.withSelectBox(GWIDs.ServerPages.ServerTools.LogLevel.LOGGERS).select(loggerName);
        interact.withSelectBox(GWIDs.ServerPages.ServerTools.LogLevel.LEVELS).select(logLevel.name());
        if (interact.withOptionalElement(GWIDs.ServerPages.ServerTools.LogLevel.SET_LEVEL, ReactionTime.MOMENTARY).isPresent()) {
            interact.withElement(GWIDs.ServerPages.ServerTools.LogLevel.SET_LEVEL).click();
        } else {
            System.out.println("System already at " + logLevel.name() + " for Logger: " + loggerName);
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

        // Making sure the current user is not on vacation
        PauseTest.createInstance().until(ExpectedConditions.elementToBeClickable(GWIDs.QUICK_JUMP.getReference()));
        GuidewireInteract interact = getInteractObject();

        if (interact.withOptionalElement(GWIDs.VACATION_STATUS_UPDATE, ReactionTime.IMMEDIATE).isPresent()) {
            interact.withSelectBox(GWIDs.VACATION_STATUS_DROPDOWN).select("At work");
            interact.withElement(GWIDs.VACATION_STATUS_UPDATE).click();
        }
        PauseTest.createInstance().until(ExpectedConditions.elementToBeClickable(GWIDs.QUICK_JUMP.getReference()));


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

        try {
            PauseTest.createSpecialInstance(1, 1).until(ExpectedConditions.alertIsPresent());
            interact.getDriver().switchTo().alert().accept();
        } catch (Exception e) {
            // do nothing
        }
    }


    public String getOverrideEnvironmentURL() {
        return overrideEnvironmentURL;
    }

    @Override
    public void openEnvironment(Environment environment) {
        GuidewireInteract interact = getInteractObject();
        // Clearing any existing Banner Messages
        interact.withDOM().clearBannerMessage();
        if (!interact.getDriver().getCurrentUrl().equalsIgnoreCase("data:,")) {
            /* flushing out browser */

            // refresh browser
            interact.getDriver().navigate().refresh();
            // logging out of the current environment if it is logged in
            if (interact.withOptionalElement(GWIDs.SETTINGS_COG, ReactionTime.IMMEDIATE).isPresent()) {
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

    public RegressionLogger getLogger() {
        return RegressionLogger.getTestLogger();
    }

    private void _login(String username, String password) {
        GuidewireInteract interact = getInteractObject();
        if (this.environment == null) {
            getLogger().info("Environment is not initialized. Opening Default environment");
            openDefaultEnvironment();
        }
        interact.withTextbox(GWIDs.Login.USER_NAME).fill(username);
        interact.withTextbox(GWIDs.Login.PASSWORD).fill(password);
        interact.withElement(GWIDs.Login.LOGIN).click();
        RegressionLogger.getTestLogger().info("Logging in with: " + username);
    }

    protected void initiateService(BindingProvider provider, String userName, String password) {
        Map<String, Object> context = provider.getRequestContext();
        context.put("com.sun.xml.internal.ws.connect.timeout", 10000);
        context.put("com.sun.xml.internal.ws.request.timeout", 5000);
        context.put("javax.xml.ws.security.auth.username", userName);
        context.put("javax.xml.ws.security.auth.password", password);
    }

    public boolean canMoveClock() {
        if (this.environment == null) {
            Assert.fail("Please call openDefaultEnvironment() or openEnvironment() to open the application");
        }

        return this.environment.canMoveClock();
    }

    protected static String getComputerName() {
        String hostOrComputerName;
        if (System.getenv("COMPUTERNAME") != null)
            hostOrComputerName = System.getenv("COMPUTERNAME") + ".idfbins.com";
        else if (System.getenv("HOSTNAME") != null)
            hostOrComputerName = System.getenv("HOSTNAME") + ".idfbins.com";
        else
            hostOrComputerName = "UNKNOWN.idfbins.com";
        return System.getenv("username") == null ? hostOrComputerName : hostOrComputerName.concat("/:ASCII:" + new StringBuilder(System.getenv("username")).reverse().toString()); // Doing this so that no one points finger looking at logs.
    }

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void moveClockTo(LocalDateTime date) {
        Validate.isTrue(canMoveClock(), "This environment is not a clock move environment");
        GuidewireInteract interact = getInteractObject();
        XMLGregorianCalendar convertDateToXMLGregCal = convertDateToXMLGregCal(date);
        try {
            // Moving PC clock
            PCDebugToolsAPIPortType pcDebugToolsAPI = getPCDebugToolsAPI();
            interact.withDOM().injectInfoMessage("Moving PC Clock to: " + date.toString());
            pcDebugToolsAPI.moveClockTo(convertDateToXMLGregCal, getComputerName());

            // Moving CC clock
            CCDebugToolsAPIPortType ccDebugToolsAPI = getCCDebugToolsAPI();
            interact.withDOM().injectInfoMessage("Moving CC Clock to: " + date.toString());
            ccDebugToolsAPI.moveClockTo(convertDateToXMLGregCal);

            // Moving BC clock
            BCDebugToolsAPIPortType bcDebugToolsAPI = getBCDebugToolsAPI();
            interact.withDOM().injectInfoMessage("Moving BC Clock to: " + date.toString());
            bcDebugToolsAPI.moveClockTo(convertDateToXMLGregCal);

            // Moving AB clock
            ABDebugToolsAPIPortType abDebugToolsAPI = getABDebugToolsAPI();
            interact.withDOM().injectInfoMessage("Moving AB Clock to: " + date.toString());
            abDebugToolsAPI.moveClockTo(convertDateToXMLGregCal);

        } catch (WsiAuthenticationException_Exception | framework.integrations.gwServices.debugToolsAPI.cc.WsiAuthenticationException_Exception | framework.integrations.gwServices.debugToolsAPI.bc.WsiAuthenticationException_Exception | framework.integrations.gwServices.debugToolsAPI.ab.WsiAuthenticationException_Exception e) {
            getLogger().error(e);
            Assert.fail("Failed to move clock: " + e.getLocalizedMessage());
        }


        interact.withDOM().clearBannerMessage();
    }

    public BCDebugToolsAPIPortType getBCDebugToolsAPI() {
        try {
            BCDebugToolsAPI api = new BCDebugToolsAPI(new URL(this.environment.getEnvironmentUrl() + "ws/gw/webservice/policycenter/bc801/BCDebugToolsAPI?WSDL"));
            BCDebugToolsAPIPortType service = api.getBCDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, "su", "gw");
            return service;
        } catch (MalformedURLException e) {
            getLogger().error("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public PCDebugToolsAPIPortType getPCDebugToolsAPI() {
        try {
            PCDebugToolsAPI api = new PCDebugToolsAPI(new URL(this.environment.getEnvironmentUrl() + "ws/gw/webservice/pc/pc800/pcdebugtools/PCDebugToolsAPI?WSDL"));
            PCDebugToolsAPIPortType service = api.getPCDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, "su", "gw");
            return service;
        } catch (MalformedURLException e) {
            getLogger().error("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public CCDebugToolsAPIPortType getCCDebugToolsAPI() {
        try {
            CCDebugToolsAPI api = new CCDebugToolsAPI(new URL(this.environment.getEnvironmentUrl() + "ws/gw/webservice/cc/cc700/ccdebugtools/CCDebugToolsAPI?WSDL"));
            CCDebugToolsAPIPortType service = api.getCCDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, "su", "gw");
            return service;
        } catch (MalformedURLException e) {
            getLogger().error("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public ABDebugToolsAPIPortType getABDebugToolsAPI() {
        try {
            ABDebugToolsAPI api = new ABDebugToolsAPI(new URL(this.environment.getEnvironmentUrl() + "ws/gw/webservice/ab/ab801/abdebugtoolsapi/ABDebugToolsAPI?WSDL"));
            ABDebugToolsAPIPortType service = api.getABDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, "su", "gw");
            return service;
        } catch (MalformedURLException e) {
            getLogger().error("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private XMLGregorianCalendar convertDateToXMLGregCal(LocalDateTime date) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(date.toLocalDate().atStartOfDay(ZoneId.systemDefault())));
        } catch (DatatypeConfigurationException e) {
            RegressionLogger.getTestLogger().error("Could not convert Date to XMLGregorian Calandar: " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
