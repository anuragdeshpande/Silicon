package framework.environmentResolution;

import framework.database.ConnectionManager;
import framework.database.models.DBConnectionDTO;
import framework.enums.ApplicationNames;
import framework.enums.Environments;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Control class designed to resolve environments from the repo, and return a standard interface object for accessing
 * environment details
 */
public class Environment extends GenericEnvironment {
    private String environmentUrl;
    private boolean hasRoundTripDocumentSupport;
    private boolean canMoveClock;
    private DBConnectionDTO dbConnectionDetails;
    private Environments environmentName;
    private ApplicationNames applicationName;

    private Environment(String environmentUrl, boolean hasRoundTripDocumentSupport, boolean canMoveClock, DBConnectionDTO dbConnectionDetails, Environments environmentName, ApplicationNames applicationName) {
        this.environmentUrl = environmentUrl;
        this.hasRoundTripDocumentSupport = hasRoundTripDocumentSupport;
        this.canMoveClock = canMoveClock;
        this.dbConnectionDetails = dbConnectionDetails;
        this.environmentName = environmentName;
        this.applicationName = applicationName;
    }

    private Environment() {

    }

    public String getEnvironmentUrl() {
        return environmentUrl;
    }

    public boolean hasRoundTripDocumentSupport() {
        return hasRoundTripDocumentSupport;
    }

    public boolean canMoveClock() {
        return canMoveClock;
    }

    public DBConnectionDTO getDbConnectionDetails() {
        return dbConnectionDetails;
    }

    public Environments getEnvironmentName() {
        return environmentName;
    }

    public ApplicationNames getApplicationName() {
        return applicationName;
    }

    private void setEnvironmentUrl(String environmentUrl) {
        this.environmentUrl = environmentUrl;
    }

    private void setHasRoundTripDocumentSupport(boolean hasRoundTripDocumentSupport) {
        this.hasRoundTripDocumentSupport = hasRoundTripDocumentSupport;
    }

    private void setCanMoveClock(boolean canMoveClock) {
        this.canMoveClock = canMoveClock;
    }

    private void setDbConnectionDetails(DBConnectionDTO dbConnectionDetails) {
        this.dbConnectionDetails = dbConnectionDetails;
    }

    private void setEnvironmentName(Environments environmentName) {
        this.environmentName = environmentName;
    }

    private void setApplicationName(ApplicationNames applicationName) {
        this.applicationName = applicationName;
    }

    public static Environment resolve(ApplicationNames applicationName, Environments environment) {
        String ENVIRONMENT_RESOLVER_QUERY = "select * from " +
                "(SELECT url.Url as ApplicationUrl, url.JenkinsDeployJobUrl as JenkinsJobUrl, env.ClockMove as MoveClock," +
                "url.LogPath as ApplicationLogPath, url.RoundtripDocuments as DocumentRoundTrip, " +
                "url.DBName as DatabaseName, url.DBUser as DBUserName, url.DBUserCred as DBPassword, " +
                "env.DBServer as DatabaseServer, urlType.Name as ApplicationName, env.Name as EnvironmentName " +
                "FROM GWEnvs_Urls url " +
                "INNER JOIN GWEnvs_UrlTypes urlType ON url.UrlTypesID = urlType.UrlTypesID " +
                "INNER JOIN GWEnvs_Envs env ON url.EnvsID = env.EnvsID) as a " +
                "where a.ApplicationName = '" + applicationName.name().toUpperCase() + "' " +
                "and a.EnvironmentName = '" + environment.name().toUpperCase() + "'";
        try {
            Object[] result = ConnectionManager.getDBConnectionTo(DBConnectionDTO.QA_DATA_REPO)
                    .query(ENVIRONMENT_RESOLVER_QUERY, ConnectionManager.getResultHandlerInstance()).get(0);

            return buildFor(result);
        } catch (SQLException e) {
            Assert.fail("Cannot connect to the data repo to get environment details" + e.getLocalizedMessage());
        } catch (NullPointerException npe) {
            Assert.fail("No Matching Results found for the given details: Application:" + applicationName.name() + " Environment: " + environment.name() + " Exception: " + npe.getLocalizedMessage());
        }


        return null;
    }

    public static Environment resolveLocal(ApplicationNames applicationName) {
        String localhostName = System.getenv("COMPUTERNAME");
        Environment environment = new Environment();
        environment.setCanMoveClock(true);
        environment.setEnvironmentName(Environments.LOCAL);
        environment.setHasRoundTripDocumentSupport(false);
        switch (applicationName) {
            case AB:
                environment.setApplicationName(ApplicationNames.AB);
                environment.setEnvironmentUrl("http://localhost:8280/ab/");
                DBConnectionDTO abDTO = new DBConnectionDTO(localhostName, "abUserLocal", "password", localhostName+"_ContactManager");
                environment.setDbConnectionDetails(abDTO);
                break;
            case PC:
                environment.setApplicationName(ApplicationNames.PC);
                environment.setEnvironmentUrl("http://localhost:8180/pc/");
                DBConnectionDTO pcDTO = new DBConnectionDTO(localhostName, "pcUserLocal", "password", localhostName+"_PolicyCenter");
                environment.setDbConnectionDetails(pcDTO);
                break;
            case CC:
                environment.setApplicationName(ApplicationNames.CC);
                environment.setEnvironmentUrl("http://localhost:8080/cc/");
                DBConnectionDTO ccDTO = new DBConnectionDTO(localhostName, "ccUserLocal", "password", localhostName+"_ClaimCenter");
                environment.setDbConnectionDetails(ccDTO);
                break;
            case BC:
                environment.setApplicationName(ApplicationNames.BC);
                environment.setEnvironmentUrl("http://localhost:8580/bc/");
                DBConnectionDTO bcDTO = new DBConnectionDTO(localhostName, "bcUserLocal", "password", localhostName+"_BillingCenter");
                environment.setDbConnectionDetails(bcDTO);
                break;
            default:
                throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
        }
        return environment;
    }

    public static List<Environment> resolveGWInstancesForPortalEnvironment(Environments environment) {
        String ENVIRONMENT_RESOLVER_QUERY = "select * from " +
                "(SELECT url.Url as ApplicationUrl, url.JenkinsDeployJobUrl as JenkinsJobUrl, env.ClockMove as MoveClock," +
                "url.LogPath as ApplicationLogPath, url.RoundtripDocuments as DocumentRoundTrip, " +
                "url.DBName as DatabaseName, url.DBUser as DBUserName, url.DBUserCred as DBPassword, " +
                "env.DBServer as DatabaseServer, urlType.Name as ApplicationName, env.Name as EnvironmentName " +
                "FROM GWEnvs_Urls url " +
                "INNER JOIN GWEnvs_UrlTypes urlType ON url.UrlTypesID = urlType.UrlTypesID " +
                "INNER JOIN GWEnvs_Envs env ON url.EnvsID = env.EnvsID) as a " +
                "where a.ApplicationName in ('CC', 'PC', 'BC', 'AB')" +
                "and a.EnvironmentName = '" + environment.name().toUpperCase() + "'";
        try {
            List<Object[]> results = ConnectionManager.getDBConnectionTo(DBConnectionDTO.QA_DATA_REPO)
                    .query(ENVIRONMENT_RESOLVER_QUERY, ConnectionManager.getResultHandlerInstance());
            ArrayList<Environment> resolvedEnvironments = new ArrayList<>();
            results.forEach(resultRow -> {
                resolvedEnvironments.add(buildFor(resultRow));
            });
            return resolvedEnvironments;
        } catch (SQLException e) {
            Assert.fail("Cannot connect to the data repo to get environment details" + e.getLocalizedMessage());
        } catch (NullPointerException npe) {
            Assert.fail("No Matching Results found for the given details: Environment: " + environment.name() + " Exception: " + npe.getLocalizedMessage());
        }

        return null;
    }

    private static Environment buildFor(Object[] rowResultFromDB) {
        Environment resolvedEnvironment = new Environment();
        resolvedEnvironment.setEnvironmentUrl(String.valueOf(rowResultFromDB[0]));
        resolvedEnvironment.setCanMoveClock(Boolean.parseBoolean(String.valueOf(rowResultFromDB[2])));
        resolvedEnvironment.setHasRoundTripDocumentSupport(Boolean.parseBoolean(String.valueOf(rowResultFromDB[4])));
        DBConnectionDTO connectionDTO = new DBConnectionDTO();
        connectionDTO.setDbName(String.valueOf(rowResultFromDB[5]));
        connectionDTO.setDbUsername(String.valueOf(rowResultFromDB[6]));
        connectionDTO.setDbPassword(new String(Base64.getDecoder().decode(String.valueOf(rowResultFromDB[7]))));
        connectionDTO.setDbServer(String.valueOf(rowResultFromDB[8]));
        resolvedEnvironment.setApplicationName(ApplicationNames.valueOf(String.valueOf(rowResultFromDB[9])));
        resolvedEnvironment.setEnvironmentName(Environments.valueOf(String.valueOf(rowResultFromDB[10])));
        resolvedEnvironment.setDbConnectionDetails(connectionDTO);

        return resolvedEnvironment;
    }
}
