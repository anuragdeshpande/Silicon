package framework.environmentResolution;

import framework.database.models.DBConnectionDTO;
import framework.enums.Environments;
import framework.guidewire.PortalInteract;
import framework.webdriver.BrowserFactory;

import java.util.List;

public class PortalEnvironment extends GenericEnvironment {
    private String environmentUrl;
    private Environments gwEnvironmentPortalUses;
    private Environment ccEnvironment;
    private Environment pcEnvironment;
    private Environment bcEnvironment;
    private Environment abEnvironment;
    private DBConnectionDTO uaaServerDetails;

    private PortalEnvironment(String environmentURL, Environments gwEnvironmentPortalUses, DBConnectionDTO uaaServerDetails) {
        this.environmentUrl = environmentURL;
        this.gwEnvironmentPortalUses = gwEnvironmentPortalUses;
        PortalInteract interact = BrowserFactory.getCurrentPortalsBrowser();
        this.uaaServerDetails = uaaServerDetails;

        // Resolving CC
        interact.withDOM().injectInfoMessage("Fetching GW Connection details for Portals " + gwEnvironmentPortalUses.name() + " Environment");
        List<Environment> environments = Environment.resolveGWInstancesForPortalEnvironment(Environments.DEV);
        assert environments != null;
        for (Environment environment : environments) {
            switch (environment.getApplicationName()) {
                case AB:
                    interact.withDOM().injectInfoMessage("Making sure AB on (" + gwEnvironmentPortalUses.name() + ") is ready to use");
                    this.abEnvironment = environment;
                    continue;
                case CC:
                    interact.withDOM().injectInfoMessage("Making sure CC on (" + gwEnvironmentPortalUses.name() + ") is ready to use");
                    this.ccEnvironment = environment;
                    continue;
                case BC:
                    interact.withDOM().injectInfoMessage("Making sure BC on (" + gwEnvironmentPortalUses.name() + ") is ready to use");
                    this.bcEnvironment = environment;
                    continue;
                case PC:
                    interact.withDOM().injectInfoMessage("Making sure PC on (" + gwEnvironmentPortalUses.name() + ") is ready to use");
                    this.pcEnvironment = environment;
                    continue;
                default: // do nothing
            }
        }
    }

    public static PortalEnvironment DEV = new PortalEnvironment("http://fbmsdkr-dev1.idfbins.com/amp/html/#/auth/login", Environments.DEV, new DBConnectionDTO("fbmsdkr-dev1.idfbins.com", "uaa", "password", ""));

    public static PortalEnvironment resolveCustomEnvironment(String portalUrl, Environments gwEnvironmentPortalUses, DBConnectionDTO uaaServerDetails) {
        return new PortalEnvironment(portalUrl, gwEnvironmentPortalUses, uaaServerDetails);
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

    public DBConnectionDTO getUaaServerDetails() {
        return uaaServerDetails;
    }
}
