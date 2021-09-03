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
import java.util.Optional;

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
    private String batchEnvironmentUrl;

    private Environment(final String environmentUrl, final boolean hasRoundTripDocumentSupport, final boolean canMoveClock, final DBConnectionDTO dbConnectionDetails, final Environments environmentName, final ApplicationNames applicationName) {
        this.environmentUrl = environmentUrl;
        this.hasRoundTripDocumentSupport = hasRoundTripDocumentSupport;
        this.canMoveClock = canMoveClock;
        this.dbConnectionDetails = dbConnectionDetails;
        this.environmentName = environmentName;
        this.applicationName = applicationName;
    }

    private Environment() {

    }

    public static Environment resolve(final ApplicationNames applicationName, final Environments environment) {
        final String ENVIRONMENT_RESOLVER_QUERY = "select * from " +
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
            final Object[] result = ConnectionManager.getDBConnectionTo(DBConnectionDTO.QA_DATA_REPO)
                    .query(ENVIRONMENT_RESOLVER_QUERY, ConnectionManager.getResultHandlerInstance()).get(0);

            final Environment environmentToReturn = buildFor(result);
            if (environment == Environments.STAGING10) {
                switch (applicationName) {
                    case AB:
                        environmentToReturn.setBatchEnvironmentUrl("http://fbmsgwab-stg10/ab/");
                        break;
                    case BC:
                        environmentToReturn.setBatchEnvironmentUrl("http://fbmsgwbcb-stg10/bc/");
                        break;
                    case CC:
                        environmentToReturn.setBatchEnvironmentUrl("http://fbmsgwccb-stg10:8080/cc/");
                        break;
                    case PC:
                        environmentToReturn.setBatchEnvironmentUrl("http://fbmsgwpcb-stg10/pc/");
                        break;
                    default:
                        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
                }
            }
            return environmentToReturn;
        } catch (final SQLException e) {
            Assert.fail("Cannot connect to the data repo to get environment details" + e.getLocalizedMessage());
        } catch (final NullPointerException npe) {
            Assert.fail("No Matching Results found for the given details: Application:" + applicationName.name() + " Environment: " + environment.name() + " Exception: " + npe.getLocalizedMessage());
        }


        return null;
    }

    public static Environment resolveLocal(final ApplicationNames applicationName) {
        final String localhostName = System.getenv("COMPUTERNAME");
        final Environment environment = new Environment();
        environment.setCanMoveClock(true);
        environment.setEnvironmentName(Environments.LOCAL);
        environment.setHasRoundTripDocumentSupport(false);
        switch (applicationName) {
            case AB:
                environment.setApplicationName(ApplicationNames.AB);
                environment.setEnvironmentUrl("http://localhost:8280/ab/");
                final DBConnectionDTO abDTO = new DBConnectionDTO(localhostName, "abUserLocal", "password", "ContactManagerDatabase");
                environment.setDbConnectionDetails(abDTO);
                break;
            case PC:
                environment.setApplicationName(ApplicationNames.PC);
                environment.setEnvironmentUrl("http://localhost:8180/pc/");
                final DBConnectionDTO pcDTO = new DBConnectionDTO(localhostName, "pcUserLocal", "password", "PolicyCenterDatabase");
                environment.setDbConnectionDetails(pcDTO);
                break;
            case CC:
                environment.setApplicationName(ApplicationNames.CC);
                environment.setEnvironmentUrl("http://localhost:8080/cc/");
                final DBConnectionDTO ccDTO = new DBConnectionDTO(localhostName, "ccUserLocal", "password", "ClaimCenterDatabase");
                environment.setDbConnectionDetails(ccDTO);
                break;
            case BC:
                environment.setApplicationName(ApplicationNames.BC);
                environment.setEnvironmentUrl("http://localhost:8580/bc/");
                final DBConnectionDTO bcDTO = new DBConnectionDTO(localhostName, "bcUserLocal", "password", "BillingCenterDatabase");
                environment.setDbConnectionDetails(bcDTO);
                break;
            default:
                throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
        }
        return environment;
    }

    public static List<Environment> resolveGWInstancesForPortalEnvironment(final Environments environment) {
        final String ENVIRONMENT_RESOLVER_QUERY = "select * from " +
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
            final List<Object[]> results = ConnectionManager.getDBConnectionTo(DBConnectionDTO.QA_DATA_REPO)
                    .query(ENVIRONMENT_RESOLVER_QUERY, ConnectionManager.getResultHandlerInstance());
            final ArrayList<Environment> resolvedEnvironments = new ArrayList<>();
            results.forEach(resultRow -> {
                resolvedEnvironments.add(buildFor(resultRow));
            });
            return resolvedEnvironments;
        } catch (final SQLException e) {
            Assert.fail("Cannot connect to the data repo to get environment details" + e.getLocalizedMessage());
        } catch (final NullPointerException npe) {
            Assert.fail("No Matching Results found for the given details: Environment: " + environment.name() + " Exception: " + npe.getLocalizedMessage());
        }

        return null;
    }

    private static Environment buildFor(final Object[] rowResultFromDB) {
        final Environment resolvedEnvironment = new Environment();
        resolvedEnvironment.setEnvironmentUrl(String.valueOf(rowResultFromDB[0]));
        resolvedEnvironment.setCanMoveClock(Boolean.parseBoolean(String.valueOf(rowResultFromDB[2])));
        resolvedEnvironment.setHasRoundTripDocumentSupport(Boolean.parseBoolean(String.valueOf(rowResultFromDB[4])));
        final DBConnectionDTO connectionDTO = new DBConnectionDTO();
        connectionDTO.setDbName(String.valueOf(rowResultFromDB[5]));
        connectionDTO.setDbUsername(String.valueOf(rowResultFromDB[6]));
        connectionDTO.setDbPassword(new String(Base64.getDecoder().decode(String.valueOf(rowResultFromDB[7]))));
        connectionDTO.setDbServer(String.valueOf(rowResultFromDB[8]));
        resolvedEnvironment.setApplicationName(ApplicationNames.valueOf(String.valueOf(rowResultFromDB[9])));
        resolvedEnvironment.setEnvironmentName(Environments.valueOf(String.valueOf(rowResultFromDB[10])));
        resolvedEnvironment.setDbConnectionDetails(connectionDTO);

        return resolvedEnvironment;
    }

    public static Environment newInstance(final String environmentUrl, final boolean hasRoundTripDocumentSupport, final boolean canMoveClock, final DBConnectionDTO dbConnectionDetails, final ApplicationNames applicationName) {
        return new Environment(environmentUrl, hasRoundTripDocumentSupport, canMoveClock, dbConnectionDetails, Environments.OTHER, applicationName);
    }

    public String getEnvironmentUrl() {
        return environmentUrl;
    }

    private void setEnvironmentUrl(final String environmentUrl) {
        this.environmentUrl = environmentUrl;
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

    private void setDbConnectionDetails(final DBConnectionDTO dbConnectionDetails) {
        this.dbConnectionDetails = dbConnectionDetails;
    }

    public Optional<String> getBatchEnvironmentURL() {
        return Optional.of(this.batchEnvironmentUrl);
    }

    public void setBatchEnvironmentUrl(final String url) {
        this.batchEnvironmentUrl = url;
    }

    public Environments getEnvironmentName() {
        return environmentName;
    }

    private void setEnvironmentName(final Environments environmentName) {
        this.environmentName = environmentName;
    }

    public ApplicationNames getApplicationName() {
        return applicationName;
    }

    private void setApplicationName(final ApplicationNames applicationName) {
        this.applicationName = applicationName;
    }

    private void setHasRoundTripDocumentSupport(final boolean hasRoundTripDocumentSupport) {
        this.hasRoundTripDocumentSupport = hasRoundTripDocumentSupport;
    }

    private void setCanMoveClock(final boolean canMoveClock) {
        this.canMoveClock = canMoveClock;
    }
}
