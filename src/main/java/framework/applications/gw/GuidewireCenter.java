package framework.applications.gw;

import framework.applications.Application;
import framework.applications.gw.responsibilities.gwCenter.*;
import framework.constants.ReactionTime;
import framework.customExceptions.*;
import framework.database.ConnectionManager;
import framework.elements.ui_element.UIElement;
import framework.enums.ApplicationNames;
import framework.enums.Environments;
import framework.enums.FrameworkSystemTags;
import framework.enums.LogLevel;
import framework.environmentResolution.Environment;
import framework.guidewire.GuidewireInteract;
import framework.guidewire.events.GWEvents;
import framework.guidewire.pages.GWIDs;
import framework.integrations.gwServices.adminImporter.AdminDataImporter;
import framework.integrations.gwServices.debugToolsAPI.ab.ABDebugToolsAPI;
import framework.integrations.gwServices.debugToolsAPI.ab.ABDebugToolsAPIPortType;
import framework.integrations.gwServices.debugToolsAPI.bc.BCDebugToolsAPI;
import framework.integrations.gwServices.debugToolsAPI.bc.BCDebugToolsAPIPortType;
import framework.integrations.gwServices.debugToolsAPI.cc.CCDebugToolsAPI;
import framework.integrations.gwServices.debugToolsAPI.cc.CCDebugToolsAPIPortType;
import framework.integrations.gwServices.debugToolsAPI.pc.PCDebugToolsAPI;
import framework.integrations.gwServices.debugToolsAPI.pc.PCDebugToolsAPIPortType;
import framework.integrations.gwServices.debugToolsAPI.pc.WsiAuthenticationException_Exception;
import framework.integrations.gwServices.gosuScriptRunner.GosuScriptRunner;
import framework.logger.RegressionLogger;
import framework.logger.eventMessaging.LoggingEvent;
import framework.webdriver.BrowserFactory;
import framework.webdriver.DriverFactory;
import framework.webdriver.PauseTest;
import framework.webdriver.ThreadFactory;
import framework.webdriver.utils.BrowserStorageAccess;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

abstract public class GuidewireCenter extends Application implements IGWOperations, ILogManagement,
        IBatchServer, ITempLogin, IErrorHandling, IGWPages, ICanMoveClock, ICanStartRemoteDataChange {

    protected Environment environment;
    protected QueryRunner queryRunner;
    protected String currentUsername;
    protected String currentPassword;
    private String overrideEnvironmentURL = null;
    private String bcUrl, abUrl, ccUrl, pcUrl;
    private boolean skipWaitOnLogin = false;
    private boolean skipLogoutIfAlreadyOpen = false;

    public GuidewireCenter() {
        super();
        getLogger().info("New Guidewire instance is being spawned: " + getApplicationUUID());
    }

    protected static String getComputerName() {
        final String hostOrComputerName;
        if (System.getenv("COMPUTERNAME") != null) {
            hostOrComputerName = System.getenv("COMPUTERNAME") + ".idfbins.com";
        } else if (System.getenv("HOSTNAME") != null) {
            hostOrComputerName = System.getenv("HOSTNAME") + ".idfbins.com";
        } else {
            hostOrComputerName = "UNKNOWN.idfbins.com";
        }
        return System.getenv("username") == null ? hostOrComputerName : hostOrComputerName.concat("/:ASCII:" + new StringBuilder(System.getenv("username")).reverse().toString()); // Doing this so that no one points finger looking at logs.
    }

    protected abstract boolean isCurrentCenterOpen();

    @Override
    public GuidewireInteract getInteractObject() {
        return BrowserFactory.getCurrentGuidewireBrowser();
    }

    @Override
    public boolean hasErrorMessageOnScreen() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public String getErrorMessageOnScreen() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void overrideEnvironmentURL(final String url) {
        this.overrideEnvironmentURL = url;
    }

    public void overrideEnvironment(final Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setLogLevel(final String loggerName, final LogLevel logLevel) {
        final ServerPages serverPages = openServerPages();
        serverPages.clickSetLogLevel();
        final GuidewireInteract interact = getInteractObject();
        interact.withSelectBox(GWIDs.ServerPages.ServerTools.LogLevel.LOGGERS).select(loggerName);
        interact.withSelectBox(GWIDs.ServerPages.ServerTools.LogLevel.LEVELS).select(logLevel.name());
        if (interact.withOptionalElement(GWIDs.ServerPages.ServerTools.LogLevel.SET_LEVEL, ReactionTime.MOMENTARY).isPresent()) {
            interact.withElement(GWIDs.ServerPages.ServerTools.LogLevel.SET_LEVEL).click();
            RegressionLogger.getTestLogger().info("Loggers for: " + loggerName + " Set to: " + logLevel.name());
        } else {
            RegressionLogger.getTestLogger().info("System already at " + logLevel.name() + " for Logger: " + loggerName);
        }

        serverPages.returnToCenter();

    }

    @Override
    public ServerPages openServerPages() {
        final GuidewireInteract interact = getInteractObject();
        interact.withElement(GWIDs.QUICK_JUMP).click();
        interact.pressKeys(Keys.ALT, Keys.SHIFT, "t");
        return new ServerPages(this);
    }

    @Override
    public void login(final String userName, final String password) {
        _login(userName, password);
        if (this.currentUsername != null) {
            getLogger().info("Username was not empty: " + this.currentUsername + " being replaced by: " + userName + " Application Identifier: " + getApplicationUUID());
        }
        this.currentUsername = userName;
        this.currentPassword = password;
    }

    @Override
    public void tempLogin(final String userName, final String password) {
        _login(userName, password);
    }

    @Override
    public void logout() {
        final GuidewireInteract interact = getInteractObject();
        GuidewireInteract.clickQuickJump();
        interact.withElement(GWIDs.SETTINGS_COG).click();
        final boolean hasPendingWorkItems = interact.withElement(GWIDs.UNSAVED_WORK).getElement().getAttribute("class").contains("gw-hasChildren");
        if (hasPendingWorkItems) {
            throw new IncorrectCallException("There is pending/Unsaved work. Please save/cancel before test can logout");
        }
        final RegressionLogger logger = RegressionLogger.getFirstAvailableLogger();
        final LoggingEvent logoutEvent = logger.startEvent(GWEvents.LOGOUT);
        try {
            interact.withElement(GWIDs.SettingsCog.LOGOUT).click();
        } catch (final StaleElementReferenceException se) {
            PauseTest.waitForPageToLoad();
            if (interact.withOptionalElement(GWIDs.Login.LOGIN, ReactionTime.IMMEDIATE).isPresent()) {
                logger.info("Logout ran into stale element, resolving");
            }
        }
        PauseTest.createInstance().until(ExpectedConditions.visibilityOfElementLocated(GWIDs.Login.LOGIN.getReference()), "Waiting for logout to complete");
        logger.endEvent(logoutEvent);
    }

    public void forceLogout() {
        GuidewireInteract.clearCookiesToForceLogout();
        try {
            final Alert alert = getInteractObject().getDriver().switchTo().alert();
            if (alert != null) {
                alert.accept();
            }
        } catch (final NoAlertPresentException nae) {
            // nothing to do. alert was not present.
        }
    }

    public String getOverrideEnvironmentURL() {
        return overrideEnvironmentURL;
    }

    @Override
    public void openEnvironment(final Environment environment) {
        this.environment = environment;
        if (ThreadFactory.getInstance().getDriver() == null) {
            ThreadFactory.getInstance().setDriver(DriverFactory.getInstance().createBrowserWindow());
        }

        if (isUp()) {
            BrowserStorageAccess.getInstance().store("ApplicationSystem", "guidewire");
            final GuidewireInteract interact = getInteractObject();
            // Clearing any existing Banner Messages
            interact.withDOM().clearBannerMessage();
            final String currentUrl = interact.getDriver().getCurrentUrl();
            if (isCurrentCenterOpen() && !skipLogoutIfAlreadyOpen) {
                PauseTest.waitForPageToLoad();
                if (!interact.withOptionalElement(GWIDs.Login.USER_NAME, ReactionTime.IMMEDIATE).isPresent()) {
                    forceLogout();
                }
            }

            // initiating db. Doing it here so that by the time the browser comes up the connection is ready for load.
            interact.withDOM().injectInfoMessage("Getting connection to " + this.environment.getEnvironmentName() + " database");
            this.queryRunner = ConnectionManager.getDBConnectionTo(this.environment);
            interact.withDOM().clearBannerMessage();
            final String url = getOverrideEnvironmentURL() != null ? getOverrideEnvironmentURL() : environment.getEnvironmentUrl();


            if (!url.equalsIgnoreCase(currentUrl)) {
                interact.getDriver().get(url);
            }

        } else {
            throw new EnvironmentNotAvailableException(this.environment.getEnvironmentUrl() + " Did not respond properly");
        }

    }

    @Override
    public void openCurrentEnvironmentURL() {
        if (this.environment != null) {
            getInteractObject().getDriver().get(environment.getEnvironmentUrl());
        } else {
            throw new UnexpectedTerminationException("No Environment settings were found, please use openEnvironment() or openDefaultEnvironment() instead.");
        }
    }

    @Override
    public boolean isUp() {
        try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpGet httpGet = new HttpGet(environment.getEnvironmentUrl());
            return httpClient.execute(httpGet, httpResponse -> {
                final int status = httpResponse.getStatusLine().getStatusCode();
                return status >= 200 && status < 300;
            });
        } catch (final IOException e) {
            return false;
        }
    }

    public RegressionLogger getLogger() {
        return RegressionLogger.getTestLogger();
    }

    private void _login(final String username, final String password) {
        final RegressionLogger testLogger = RegressionLogger.getTestLogger();
        final LoggingEvent loginEvent = testLogger.startEvent(GWEvents.LOGIN);
        final GuidewireInteract interact = getInteractObject();
        if (this.environment == null) {
            getLogger().info("Environment is not initialized. Opening Default environment");
            openDefaultEnvironment();
        }
        interact.getDriver().navigate().refresh();
        interact.withTextbox(GWIDs.Login.USER_NAME).fill(username);
        interact.withTextbox(GWIDs.Login.PASSWORD).fill(password);
        interact.withElement(GWIDs.Login.LOGIN).click();

        if (!this.skipWaitOnLogin) {
            try {
                PauseTest.createInstance().until(ExpectedConditions.invisibilityOfElementLocated(By.id("Login-LoginScreen-2")), "Waiting for login to complete");
            } catch (final TimeoutException te) {
                final UIElement loginIssues = interact.withOptionalElement(GWIDs.Login.LOGIN_MESSAGES, ReactionTime.ONE_SECOND);
                if (loginIssues.isPresent()) {
                    if (loginIssues.getElement().getText().contains("Your username and/or password may be incorrect")) {
                        throw new InvalidLoginException("Unable to login with Username: " + username + " and Password " + password);
                    }

                    if (loginIssues.getElement().getText().contains("Locked")) {
                        throw new AccountLockedOrDisabledException("Unable to login with Username: " + username + " and Password " + password);
                    }
                }
            }
            PauseTest.waitForPageToLoad(ReactionTime.getInstance(60, TimeUnit.SECONDS));
        }

        testLogger.info("Logging in with: " + username);
        testLogger.endEvent(loginEvent);
    }

    protected void initiateService(final BindingProvider provider, final String userName, final String password) {
        final Map<String, Object> context = provider.getRequestContext();
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

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void moveClockTo(final LocalDateTime date) {
        Validate.isTrue(canMoveClock(), "This environment is not a clock move environment");
        final GuidewireInteract interact = getInteractObject();
        final XMLGregorianCalendar convertDateToXMLGregCal = convertDateToXMLGregCal(date);
        final RegressionLogger logger = RegressionLogger.getTestLogger();
        logger.addTag(FrameworkSystemTags.CLOCK_MOVED.getValue());
        try {
            // Moving PC clock
            final PCDebugToolsAPIPortType pcDebugToolsAPI = getPCDebugToolsAPI();
            interact.withDOM().injectInfoMessage("Moving PC Clock to: " + date);
            pcDebugToolsAPI.moveClockTo(convertDateToXMLGregCal, getComputerName());
            logger.info("Moved Clock in PC : " + date);
            // Moving CC clock
            final CCDebugToolsAPIPortType ccDebugToolsAPI = getCCDebugToolsAPI();
            interact.withDOM().injectInfoMessage("Moving CC Clock to: " + date);
            ccDebugToolsAPI.moveClockTo(convertDateToXMLGregCal);
            logger.info("Moved Clock in CC : " + date);

            // Moving BC clock
            final BCDebugToolsAPIPortType bcDebugToolsAPI = getBCDebugToolsAPI();
            interact.withDOM().injectInfoMessage("Moving BC Clock to: " + date);
            bcDebugToolsAPI.moveClockTo(convertDateToXMLGregCal);
            logger.info("Moved Clock in BC : " + date);

            // Moving AB clock
            final ABDebugToolsAPIPortType abDebugToolsAPI = getABDebugToolsAPI();
            interact.withDOM().injectInfoMessage("Moving AB Clock to: " + date);
            abDebugToolsAPI.moveClockTo(convertDateToXMLGregCal);
            logger.info("Moved Clock in AB : " + date);

        } catch (final WsiAuthenticationException_Exception | framework.integrations.gwServices.debugToolsAPI.cc.WsiAuthenticationException_Exception | framework.integrations.gwServices.debugToolsAPI.bc.WsiAuthenticationException_Exception | framework.integrations.gwServices.debugToolsAPI.ab.WsiAuthenticationException_Exception e) {
            getLogger().fail(e);
            Assert.fail("Failed to move clock: " + e.getLocalizedMessage());
        }


        interact.withDOM().clearBannerMessage();
    }

    public BCDebugToolsAPIPortType getBCDebugToolsAPI() {
        try {
            final BCDebugToolsAPI api;
            boolean isProdLikeEnvironment = false;
            if (environment != null && environment.getEnvironmentName().equals(Environments.LOCAL)) {
                api = new BCDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolveLocal(ApplicationNames.BC)).getEnvironmentUrl() + "ws/gw/webservice/policycenter/bc1000/BCDebugToolsAPI?WSDL"));
            } else {
                String environmentURL;
                if (bcUrl != null) {
                    environmentURL = bcUrl;
                } else {
                    environmentURL = Objects.requireNonNull(Environment.resolve(ApplicationNames.BC, environment.getEnvironmentName())).getEnvironmentUrl();
                }

                if (environment != null && this.environment.getEnvironmentName() == Environments.STAGING10) {
                    isProdLikeEnvironment = true;
                    environmentURL = this.environment.getBatchEnvironmentURL().get();
                }
                api = new BCDebugToolsAPI(new URL(environmentURL + "ws/gw/webservice/policycenter/bc1000/BCDebugToolsAPI?WSDL"));
            }
            final BCDebugToolsAPIPortType service = api.getBCDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, isProdLikeEnvironment ? "emessaging" : "su", "gw");
            return service;
        } catch (final MalformedURLException e) {
            getLogger().fail("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public PCDebugToolsAPIPortType getPCDebugToolsAPI() {
        try {
            final PCDebugToolsAPI api;
            boolean isProdLikeEnvironment = false;
            if (environment != null && environment.getEnvironmentName().equals(Environments.LOCAL)) {
                api = new PCDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolveLocal(ApplicationNames.PC)).getEnvironmentUrl() + "ws/gw/webservice/pc/pc1000/pcdebugtools/PCDebugToolsAPI?WSDL"));
            } else {
                String environmentUrl;
                if (pcUrl != null) {
                    environmentUrl = pcUrl;
                } else {
                    environmentUrl = Objects.requireNonNull(Environment.resolve(ApplicationNames.PC, environment.getEnvironmentName())).getEnvironmentUrl();
                    if (environment != null && this.environment.getEnvironmentName() == Environments.STAGING10) {
                        isProdLikeEnvironment = true;
                        environmentUrl = this.environment.getBatchEnvironmentURL().get();
                    }
                }
                api = new PCDebugToolsAPI(new URL(environmentUrl + "ws/gw/webservice/pc/pc1000/pcdebugtools/PCDebugToolsAPI?WSDL"));
            }
            final PCDebugToolsAPIPortType service = api.getPCDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, isProdLikeEnvironment ? "emessaging" : "su", "gw");
            return service;
        } catch (final MalformedURLException e) {
            getLogger().fail("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public CCDebugToolsAPIPortType getCCDebugToolsAPI() {
        try {
            final CCDebugToolsAPI api;
            boolean isProdLikeEnvironment = false;
            if (environment != null && environment.getEnvironmentName().equals(Environments.LOCAL)) {
                api = new CCDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolveLocal(ApplicationNames.CC)).getEnvironmentUrl() + "ws/gw/webservice/cc/cc1000/ccdebugtools/CCDebugToolsAPI?WSDL"));
            } else {
                String environmentUrl;
                if (ccUrl != null) {
                    environmentUrl = ccUrl;
                } else {
                    environmentUrl = Objects.requireNonNull(Environment.resolve(ApplicationNames.CC, environment.getEnvironmentName())).getEnvironmentUrl();
                    if (environment != null && this.environment.getEnvironmentName() == Environments.STAGING10) {
                        isProdLikeEnvironment = true;
                        environmentUrl = this.environment.getBatchEnvironmentURL().get();
                    }
                }
                api = new CCDebugToolsAPI(new URL(environmentUrl + "ws/gw/webservice/cc/cc1000/ccdebugtools/CCDebugToolsAPI?WSDL"));
            }
            final CCDebugToolsAPIPortType service = api.getCCDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, isProdLikeEnvironment ? "emessaging" : "su", "gw");
            return service;
        } catch (final MalformedURLException e) {
            getLogger().fail("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public ABDebugToolsAPIPortType getABDebugToolsAPI() {
        try {
            ABDebugToolsAPI api = null;
            boolean isProdLikeEnvironment = false;
            if (environment != null && environment.getEnvironmentName().equals(Environments.LOCAL)) {
                api = new ABDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolveLocal(ApplicationNames.AB)).getEnvironmentUrl() + "ws/gw/webservice/ab/ab1000/abdebugtoolsapi/ABDebugToolsAPI?WSDL"));
            } else {
                String environmentUrl;
                if (abUrl != null) {
                    environmentUrl = abUrl;
                } else {
                    environmentUrl = Objects.requireNonNull(Environment.resolve(ApplicationNames.AB, environment.getEnvironmentName())).getEnvironmentUrl();
                    if (environment != null && this.environment.getEnvironmentName() == Environments.STAGING10) {
                        isProdLikeEnvironment = true;
                        environmentUrl = this.environment.getBatchEnvironmentURL().get();
                    }
                }
                api = new ABDebugToolsAPI(new URL(environmentUrl + "ws/gw/webservice/ab/ab1000/abdebugtoolsapi/ABDebugToolsAPI?WSDL"));
            }
            final ABDebugToolsAPIPortType service = api.getABDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, isProdLikeEnvironment ? "emessaging" : "su", "gw");
            return service;
        } catch (final MalformedURLException e) {
            getLogger().fail("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void runGosuScript(final String gspFilePathRelativeToResourcesDirectory, final String jsonFilePathRelativeToResourcesDirectory) {
        final String referenceName;
        final JSONParser parser = new JSONParser();
        try {
            final JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(Objects.requireNonNull(GuidewireCenter.class.getClassLoader().getResource(gspFilePathRelativeToResourcesDirectory)).getPath()));
            referenceName = jsonObject.get("ExternalReference").toString();
        } catch (final IOException | ParseException e) {
            throw new RuntimeException(e);
        }


        final GosuScriptRunner<GuidewireCenter> runner = new GosuScriptRunner<>(this);
        runner.registerScript(gspFilePathRelativeToResourcesDirectory, jsonFilePathRelativeToResourcesDirectory);
        runner.runScript(referenceName);
        final Date currentDate = new Date();
        final Date endDate = DateUtils.addHours(currentDate, 1);
        while (runner.getStatus(referenceName).equalsIgnoreCase("Executing")) {
            if (new Date().after(endDate)) {
                throw new RuntimeException("Script is still executing after waiting for an hour. Exiting. Script might be still running. Cannot make sure.");
            }
        }
    }

    @Override
    public void importXMLFile(final String adminDataResourcePathReference) {
        final AdminDataImporter<GuidewireCenter> importer = new AdminDataImporter<>(this);
        importer.importData(adminDataResourcePathReference);
    }

    private XMLGregorianCalendar convertDateToXMLGregCal(final LocalDateTime date) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(date.toLocalDate().atStartOfDay(ZoneId.systemDefault())));
        } catch (final DatatypeConfigurationException e) {
            RegressionLogger.getTestLogger().fail("Could not convert Date to XMLGregorian Calandar: " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }


    private String cleanUpEnvironmentURL(final String environmentURL) {
        final String cleanupURL = environmentURL.substring(0, environmentURL.lastIndexOf("/"));
        return cleanupURL.substring(0, cleanupURL.lastIndexOf("/")) + "/";
    }

    public void setBcUrl(final String bcUrl) {
        this.bcUrl = bcUrl;
    }

    public void setAbUrl(final String abUrl) {
        this.abUrl = abUrl;
    }

    public void setCcUrl(final String ccUrl) {
        this.ccUrl = ccUrl;
    }

    public void setPcUrl(final String pcUrl) {
        this.pcUrl = pcUrl;
    }

    public void setSkipWaitOnLogin(final boolean skipWaitOnLogin) {
        this.skipWaitOnLogin = skipWaitOnLogin;
    }

    public void setSkipLogoutIfAlreadyOpen(final boolean skipLogoutIfAlreadyOpen) {
        this.skipLogoutIfAlreadyOpen = skipLogoutIfAlreadyOpen;
    }
}
