package framework.environmentResolution;

import framework.database.ConnectionManager;
import framework.database.models.DBConnectionDTO;
import framework.enums.ApplicationNames;
import framework.enums.Environments;
import org.junit.Assert;

import java.sql.SQLException;

/**
 * Control class designed to resolve environments from the repo, and return a standard interface object for accessing
 * environment details
 */
public class Environment {
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

    private Environment(){

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

    public static Environment resolve(ApplicationNames applicationName, Environments environment){
        String ENVIRONMENT_RESOLVER_QUERY = "select * from " +
                "(SELECT url.Url as ApplicationUrl, url.JenkinsDeployJobUrl as JenkinsJobUrl, env.ClockMove as MoveClock," +
                "url.LogPath as ApplicationLogPath, url.RoundtripDocuments as DocumentRoundTrip, " +
                "url.DBName as DatabaseName, url.DBUser as DBUserName, url.DBUserCred as DBPassword, " +
                "env.DBServer as DatabaseServer, urlType.Name as ApplicationName, env.Name as EnvironmentName " +
                "FROM GWEnvs_Urls url " +
                "INNER JOIN GWEnvs_UrlTypes urlType ON url.UrlTypesID = urlType.UrlTypesID " +
                "INNER JOIN GWEnvs_Envs env ON url.EnvsID = env.EnvsID) as a " +
                "where a.ApplicationName = '"+applicationName.name().toUpperCase()+"' " +
                "and a.EnvironmentName = '"+environment.name().toUpperCase()+"'";
        try{
            Object[] result = ConnectionManager.getDBConnectionTo(DBConnectionDTO.QA_DATA_REPO)
                    .query(ENVIRONMENT_RESOLVER_QUERY, ConnectionManager.getResultHandlerInstance()).get(0);
            Environment resolvedEnvironment = new Environment();
            resolvedEnvironment.setApplicationName(applicationName);
            resolvedEnvironment.setEnvironmentUrl(String.valueOf(result[0]));
            resolvedEnvironment.setCanMoveClock(Boolean.parseBoolean(String.valueOf(result[1])));
            resolvedEnvironment.setHasRoundTripDocumentSupport(Boolean.parseBoolean(String.valueOf(result[4])));
            resolvedEnvironment.setEnvironmentName(environment);
            DBConnectionDTO connectionDTO = new DBConnectionDTO();
            connectionDTO.setDbName(String.valueOf(result[5]));
            connectionDTO.setDbUsername(String.valueOf(result[6]));
            connectionDTO.setDbPassword(String.valueOf(result[7]));
            connectionDTO.setDbServer(String.valueOf(result[8]));
            resolvedEnvironment.setDbConnectionDetails(connectionDTO);

            return resolvedEnvironment;

        } catch (SQLException e) {
            Assert.fail("Cannot connect to the data repo to get environment details"+ e.getLocalizedMessage());

        } catch (NullPointerException npe){
            Assert.fail("No Matching Results found for the given details: Application:"+applicationName.name()+" Environment: "+environment.name()+" Exception: "+npe.getLocalizedMessage());
        }

        return null;
    }


}
