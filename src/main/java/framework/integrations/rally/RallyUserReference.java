package framework.integrations.rally;

import com.aventstack.extentreports.ExtentTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;

public class RallyUserReference extends RallyQueryableService{

    private RallyUserReference(ExtentTest regressionLogger) {
        super(regressionLogger);
    }

    public static String getUserReference(String rallyEmailID){
        RallyUserReference reference = new RallyUserReference(null);
        QueryRequest userRequest = new QueryRequest("User");
        userRequest.setFetch(new Fetch("UserName", "Subscription", "DisplayName", "SubscriptionAdmin"));
        userRequest.setQueryFilter(new QueryFilter("UserName", "=", rallyEmailID));
        QueryResponse userQueryResponse = reference.query(userRequest);
        JsonArray userQueryResults = userQueryResponse.getResults();
        JsonElement userQueryElement = userQueryResults.get(0);
        JsonObject userQueryObject = userQueryElement.getAsJsonObject();
        return userQueryObject.get("_ref").getAsString();
    }
}
