package framework.environmentResolution;

import framework.enums.ApplicationNames;
import framework.enums.Environments;
import framework.guidewire.PortalInteract;
import framework.webdriver.BrowserFactory;

public class PortalEnvironment extends GenericEnvironment {
    private String environmentUrl;
    private Environments gwEnvironmentPortalUses;
    private Environment ccEnvironment;
    private Environment pcEnvironment;
    private Environment bcEnvironment;
    private Environment abEnvironment;

    private PortalEnvironment(String environmentURL, Environments gwEnvironmentPortalUses){
        this.environmentUrl = environmentURL;
        this.gwEnvironmentPortalUses = gwEnvironmentPortalUses;
        PortalInteract interact = BrowserFactory.getCurrentPortalsBrowser();

        // Resolving CC
        interact.withDOM().injectInfoMessage("Making sure CC on ("+gwEnvironmentPortalUses.name()+") is ready to use");
        this.ccEnvironment = Environment.resolve(ApplicationNames.CC, gwEnvironmentPortalUses);

        // Resolving PC
        interact.withDOM().injectInfoMessage("Making sure PC on ("+gwEnvironmentPortalUses.name()+") is ready to use");
        this.pcEnvironment = Environment.resolve(ApplicationNames.PC, gwEnvironmentPortalUses);

        // Resolving BC
        interact.withDOM().injectInfoMessage("Making sure BC on ("+gwEnvironmentPortalUses.name()+") is ready to use");
        this.bcEnvironment = Environment.resolve(ApplicationNames.BC, gwEnvironmentPortalUses);

        // Resolving AB
        interact.withDOM().injectInfoMessage("Making sure AB on ("+gwEnvironmentPortalUses.name()+") is ready to use");
        this.abEnvironment = Environment.resolve(ApplicationNames.AB, gwEnvironmentPortalUses);
    }

    public static PortalEnvironment DEV = new PortalEnvironment("http://fbmsdkr-dev1.idfbins.com/amp/html/#/auth/login", Environments.DEV);

    public static PortalEnvironment resolveCustomEnvironment(String portalUrl, Environments gwEnvironmentPortalUses){
        return new PortalEnvironment(portalUrl, gwEnvironmentPortalUses);
    }

    public Environments getGwEnvironmentPortalUses() {
        return gwEnvironmentPortalUses;
    }

    public String getEnvironmentUrl() {
        return environmentUrl;
    }

    public Environment getCcEnvironment() {
        return ccEnvironment;
    }

    public Environment getPcEnvironment() {
        return pcEnvironment;
    }

    public Environment getBcEnvironment() {
        return bcEnvironment;
    }

    public Environment getAbEnvironment() {
        return abEnvironment;
    }
}
