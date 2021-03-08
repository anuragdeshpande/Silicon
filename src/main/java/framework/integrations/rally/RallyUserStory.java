package framework.integrations.rally;

import com.aventstack.extentreports.ExtentTest;
import com.google.gson.JsonObject;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.util.QueryFilter;
import framework.integrations.rally.enums.RallyItemType;

public class RallyUserStory extends RallyQueryableService {
    private String storyNumber;
    private JsonObject rallyResponse;
    private boolean isStoryTracked;

    private RallyUserStory(String storyNumber, ExtentTest regressionLogger) {
        super(regressionLogger);
        this.storyNumber = storyNumber;
    }

    public static RallyUserStory getUserStory(String storyNumber, ExtentTest regressionLogger) {
        RallyUserStory userStory = new RallyUserStory(storyNumber, regressionLogger);
        QueryRequest queryRequest = new QueryRequest(RallyItemType.USER_STORY.getItem_type_key());
        queryRequest.setLimit(1000);
        queryRequest.setScopedDown(false);
        queryRequest.setScopedUp(false);
        queryRequest.setQueryFilter(new QueryFilter("FormattedID", "=", storyNumber));
        userStory.rallyResponse = userStory.query(queryRequest).getResults().get(0).getAsJsonObject();

        return userStory;
    }

    public static RallyUserStory getUserStoryByRef(String _ref, ExtentTest regressionLogger) {
        RallyUserStory rallyUserStory = new RallyUserStory(null, regressionLogger);
        rallyUserStory.rallyResponse = rallyUserStory.get(_ref).getObject();
        rallyUserStory.storyNumber = rallyUserStory.rallyResponse.get("FormattedID").getAsString();
        String currentColumn = rallyUserStory.rallyResponse.get("FlowState").getAsJsonObject().get("_refObjectName").getAsString();
        rallyUserStory.isStoryTracked = !(currentColumn.equalsIgnoreCase("Done") || currentColumn.equalsIgnoreCase("Accepted"));
        return rallyUserStory;
    }

    public String getStoryNumber() {
        return storyNumber;
    }

    public boolean isStoryTracked() {
        return isStoryTracked;
    }

    public JsonObject getRallyResponse() {
        return rallyResponse;
    }
}
