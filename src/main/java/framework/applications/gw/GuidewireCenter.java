package framework.applications.gw;

import framework.applications.Application;
import framework.applications.gw.responsibilities.gwCenter.*;
import framework.constants.ReactionTime;
import framework.customExceptions.AccountLockedOrDisabledException;
import framework.customExceptions.EnvironmentNotAvailableException;
import framework.customExceptions.IncorrectCallException;
import framework.customExceptions.InvalidLoginException;
import framework.database.ConnectionManager;
import framework.elements.ui_element.UIElement;
import framework.enums.ApplicationNames;
import framework.enums.Environments;
import framework.enums.LogLevel;
import framework.environmentResolution.Environment;
import framework.guidewire.GuidewireInteract;
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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
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

    private String overrideEnvironmentURL = null;

    protected Environment environment;
    protected QueryRunner queryRunner;
    protected String currentUsername;
    protected String currentPassword;

    public GuidewireCenter() {
        super();
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
    }

    @Override
    public void tempLogin(String userName, String password) {
        _login(userName, password);
    }

    @Override
    public void logout() {
        GuidewireInteract interact = getInteractObject();
        GuidewireInteract.clickQuickJump();
        interact.withElement(GWIDs.SETTINGS_COG).click();
        boolean hasPendingWorkItems = interact.withElement(GWIDs.UNSAVED_WORK).getElement().getAttribute("class").contains("gw-hasChildren");
        if(hasPendingWorkItems){
            throw new IncorrectCallException("There is pending/Unsaved work. Please save/cancel before test can logout");
        }
        try{
            interact.withElement(GWIDs.SettingsCog.LOGOUT).click();
        } catch (StaleElementReferenceException se){
            PauseTest.waitForPageToLoad();
            if (interact.withOptionalElement(GWIDs.Login.LOGIN, ReactionTime.IMMEDIATE).isPresent()) {
                RegressionLogger.getFirstAvailableLogger().info("Logout ran into stale element, resolving");
            }
        }
        PauseTest.createInstance().until(ExpectedConditions.visibilityOfElementLocated(GWIDs.Login.LOGIN.getReference()), "Waiting for logout to complete");
    }

    public void forceLogout(){
        GuidewireInteract.clearCookiesToForceLogout();
    }


    public String getOverrideEnvironmentURL() {
        return overrideEnvironmentURL;
    }

    @Override
    public void openEnvironment(Environment environment) {
        this.environment = environment;
        if(ThreadFactory.getInstance().getDriver() == null){
            ThreadFactory.getInstance().setDriver(DriverFactory.getInstance().createBrowserWindow());
        }

        if(isUp()){
            BrowserStorageAccess.getInstance().store("ApplicationSystem", "guidewire");
            GuidewireInteract interact = getInteractObject();
            // Clearing any existing Banner Messages
            interact.withDOM().clearBannerMessage();
            String currentUrl = interact.getDriver().getCurrentUrl();
            if (isCurrentCenterOpen()) {
                PauseTest.waitForPageToLoad();
                if(!interact.withOptionalElement(GWIDs.Login.USER_NAME, ReactionTime.IMMEDIATE).isPresent()){
                    forceLogout();
                }
            }

            // initiating db. Doing it here so that by the time the browser comes up the connection is ready for load.
            interact.withDOM().injectInfoMessage("Getting connection to "+this.environment.getEnvironmentName()+" database");
            this.queryRunner = ConnectionManager.getDBConnectionTo(this.environment);
            interact.withDOM().clearBannerMessage();
            String url = getOverrideEnvironmentURL() != null ? getOverrideEnvironmentURL() : environment.getEnvironmentUrl();


            if(!url.equalsIgnoreCase(currentUrl)){
                interact.getDriver().get(url);
            }

        } else {
            throw new EnvironmentNotAvailableException(this.environment.getEnvironmentUrl() + " Did not respond properly");
        }

    }

    @Override
    public boolean isUp() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(environment.getEnvironmentUrl());
            return httpClient.execute(httpGet, httpResponse -> {
                int status = httpResponse.getStatusLine().getStatusCode();
                return status >= 200 && status < 300;
            });
        } catch (IOException e) {
            return false;
        }
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
        interact.getDriver().navigate().refresh();
        interact.withTextbox(GWIDs.Login.USER_NAME).fill(username);
        interact.withTextbox(GWIDs.Login.PASSWORD).fill(password);
        interact.withElement(GWIDs.Login.LOGIN).click();
        try{
            PauseTest.createInstance().until(ExpectedConditions.invisibilityOfElementLocated(By.id("Login-LoginScreen-2")), "Waiting for login to complete");
        } catch (TimeoutException te){
            UIElement loginIssues = interact.withOptionalElement(GWIDs.Login.LOGIN_MESSAGES, ReactionTime.ONE_SECOND);
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
            getLogger().fail(e);
            Assert.fail("Failed to move clock: " + e.getLocalizedMessage());
        }


        interact.withDOM().clearBannerMessage();
    }

    public BCDebugToolsAPIPortType getBCDebugToolsAPI() {
        try {
            BCDebugToolsAPI api = null;
            if (environment.getEnvironmentName().equals(Environments.LOCAL)) {
                api = new BCDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolveLocal(ApplicationNames.BC)).getEnvironmentUrl() + "ws/gw/webservice/policycenter/bc801/BCDebugToolsAPI?WSDL"));
            } else {
                api = new BCDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolve(ApplicationNames.BC, environment.getEnvironmentName())).getEnvironmentUrl() + "ws/gw/webservice/policycenter/bc801/BCDebugToolsAPI?WSDL"));
            }
            BCDebugToolsAPIPortType service = api.getBCDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, "su", "gw");
            return service;
        } catch (MalformedURLException e) {
            getLogger().fail("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public PCDebugToolsAPIPortType getPCDebugToolsAPI() {
        try {
            PCDebugToolsAPI api = null;
            if (environment.getEnvironmentName().equals(Environments.LOCAL)) {
                api = new PCDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolveLocal(ApplicationNames.PC)).getEnvironmentUrl() + "ws/gw/webservice/pc/pc800/pcdebugtools/PCDebugToolsAPI?WSDL"));
            } else {
                api = new PCDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolve(ApplicationNames.PC, environment.getEnvironmentName())).getEnvironmentUrl() + "ws/gw/webservice/pc/pc800/pcdebugtools/PCDebugToolsAPI?WSDL"));
            }
            PCDebugToolsAPIPortType service = api.getPCDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, "su", "gw");
            return service;
        } catch (MalformedURLException e) {
            getLogger().fail("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public CCDebugToolsAPIPortType getCCDebugToolsAPI() {
        try {
            CCDebugToolsAPI api = null;
            if (environment.getEnvironmentName().equals(Environments.LOCAL)) {
                api = new CCDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolveLocal(ApplicationNames.CC)).getEnvironmentUrl() + "ws/gw/webservice/cc/cc1000/ccdebugtools/CCDebugToolsAPI?WSDL"));
            } else {
                api = new CCDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolve(ApplicationNames.CC, environment.getEnvironmentName())).getEnvironmentUrl() + "ws/gw/webservice/cc/cc1000/ccdebugtools/CCDebugToolsAPI?WSDL"));
            }
            CCDebugToolsAPIPortType service = api.getCCDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, "su", "gw");
            return service;
        } catch (MalformedURLException e) {
            getLogger().fail("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public ABDebugToolsAPIPortType getABDebugToolsAPI() {
        try {
            ABDebugToolsAPI api = null;
            if (environment.getEnvironmentName().equals(Environments.LOCAL)) {
                api = new ABDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolveLocal(ApplicationNames.AB)).getEnvironmentUrl() + "ws/gw/webservice/ab/ab1000/abdebugtoolsapi/ABDebugToolsAPI?WSDL"));
            } else {
                api = new ABDebugToolsAPI(new URL(Objects.requireNonNull(Environment.resolve(ApplicationNames.AB, environment.getEnvironmentName())).getEnvironmentUrl() + "ws/gw/webservice/ab/ab1000/abdebugtoolsapi/ABDebugToolsAPI?WSDL"));
            }
            ABDebugToolsAPIPortType service = api.getABDebugToolsAPISoap11Port();
            initiateService((BindingProvider) service, "su", "gw");
            return service;
        } catch (MalformedURLException e) {
            getLogger().fail("Failed to connect to BC Debug Tools API" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void runGosuScript(String gspFilePathRelativeToResourcesDirectory, String jsonFilePathRelativeToResourcesDirectory) {
        String referenceName;
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(Objects.requireNonNull(GuidewireCenter.class.getClassLoader().getResource(gspFilePathRelativeToResourcesDirectory)).getPath()));
            referenceName = jsonObject.get("ExternalReference").toString();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }


        GosuScriptRunner<GuidewireCenter> runner = new GosuScriptRunner<>(this);
        runner.registerScript(gspFilePathRelativeToResourcesDirectory, jsonFilePathRelativeToResourcesDirectory);
        runner.runScript(referenceName);
        Date currentDate = new Date();
        Date endDate = DateUtils.addHours(currentDate, 1);
        while (runner.getStatus(referenceName).equalsIgnoreCase("Executing")) {
            if (new Date().after(endDate)) {
                throw new RuntimeException("Script is still executing after waiting for an hour. Exiting. Script might be still running. Cannot make sure.");
            }
        }
    }

    @Override
    public void importXMLFile(String adminDataResourcePathReference) {
        AdminDataImporter<GuidewireCenter> importer = new AdminDataImporter<>(this);
        importer.importData(adminDataResourcePathReference);
    }

    private XMLGregorianCalendar convertDateToXMLGregCal(LocalDateTime date) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(date.toLocalDate().atStartOfDay(ZoneId.systemDefault())));
        } catch (DatatypeConfigurationException e) {
            RegressionLogger.getTestLogger().fail("Could not convert Date to XMLGregorian Calandar: " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }


    private String cleanUpEnvironmentURL(String environmentURL) {
        String cleanupURL = environmentURL.substring(0, environmentURL.lastIndexOf("/"));
        return cleanupURL.substring(0, cleanupURL.lastIndexOf("/")) + "/";
    }
}
