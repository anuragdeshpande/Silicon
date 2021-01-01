package framework.integrations.rally;

import com.aventstack.extentreports.ExtentTest;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.GetRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.request.UpdateRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.GetResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.response.UpdateResponse;
import com.rallydev.rest.util.Ref;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class RallyQueryableService {
    private final String apiKey = "_YV4rMBAS4e03RzzbCvslCwHjXk6ekcLoxNd2fdONac";
    private final String rallyURL = "https://rally1.rallydev.com";
    protected final ExtentTest regressionLogger;

    protected RallyQueryableService(ExtentTest regressionLogger) {
        this.regressionLogger = regressionLogger;
    }

    protected QueryResponse query(QueryRequest request) {
        try (RallyRestApi restApi = new RallyRestApi(new URI(rallyURL), apiKey)) {
            restApi.setApplicationName("Regression-Rally Integration");
            return restApi.query(request);
        } catch (IOException | URISyntaxException e) {
            if(regressionLogger != null){
                regressionLogger.info(e);
                regressionLogger.warning("Could not complete the rally request due to: " + e.getLocalizedMessage());
            }
            return null;
        }
    }

    protected CreateResponse create(CreateRequest request) {
        try (RallyRestApi restApi = new RallyRestApi(new URI(rallyURL), apiKey)) {
            restApi.setApplicationName("Regression-Rally Integration");
            return restApi.create(request);
        } catch (IOException | URISyntaxException e) {
            if(regressionLogger != null){
                regressionLogger.info(e);
                regressionLogger.warning("Could not complete the rally request due to: " + e.getLocalizedMessage());
            }
            return null;
        }
    }

    protected GetResponse get(String _ref) {
        try (RallyRestApi restApi = new RallyRestApi(new URI(rallyURL), apiKey)) {
            restApi.setApplicationName("Regression-Rally Integration");
            String relativeRef = Ref.getRelativeRef(_ref);
            GetRequest getRequest = new GetRequest(relativeRef);
            return restApi.get(getRequest);
        } catch (IOException | URISyntaxException e) {
            if(regressionLogger != null){
                regressionLogger.info(e);
                regressionLogger.warning("Could not complete the rally request due to: " + e.getLocalizedMessage());
            }
            return null;
        }
    }

    protected UpdateResponse update(UpdateRequest updateRequest) {
        try (RallyRestApi restApi = new RallyRestApi(new URI(rallyURL), apiKey)) {
            restApi.setApplicationName("Regression-Rally Integration");
            return restApi.update(updateRequest);
        } catch (IOException | URISyntaxException e) {
            if(regressionLogger != null){
                regressionLogger.info(e);
                regressionLogger.warning("Could not complete the rally request due to: " + e.getLocalizedMessage());
            }
            return null;
        }
    }
}
