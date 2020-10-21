package framework.integrations.rally;

import com.aventstack.extentreports.ExtentTest;
import com.google.gson.JsonObject;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.util.QueryFilter;
import framework.integrations.rally.dtos.RallyTestCaseDTO;
import framework.integrations.rally.enums.RallyItemType;

import java.util.Arrays;

public class RallyTestCase extends RallyQueryableService {

    private final String testCaseName;
    private JsonObject rallyResponse;
    private RallyUserStory relatedUserStory;
    private final ExtentTest regressionLogger;

    private RallyTestCase(String testCaseName, ExtentTest regressionLogger) {
        super(regressionLogger);
        this.testCaseName = testCaseName;
        this.regressionLogger = regressionLogger;
    }

    public void recordTestResult(RallyTestCaseDTO dto){
        if(relatedUserStory.isStoryTracked()){
            String testCaseRef = rallyResponse.get("_ref").getAsString();
            JsonObject newTestCaseResult = new JsonObject();
            newTestCaseResult.addProperty("TestCase", testCaseRef);
            newTestCaseResult.addProperty("Verdict", dto.testCaseStatus());
            newTestCaseResult.addProperty("Notes", dto.getNotes());
            newTestCaseResult.addProperty("Build", "TestBuild");
            newTestCaseResult.addProperty("Tester", dto.getRallyUserRef());
            newTestCaseResult.addProperty("Date", dto.getDateOfTestCase());
            CreateRequest createRequest = new CreateRequest(RallyItemType.TEST_CASE_RESULT.getItem_type_key(), newTestCaseResult);
            CreateResponse createResponse = create(createRequest);
            if(regressionLogger != null){
                if(createResponse.wasSuccessful()){
                    regressionLogger.info("Successfully updated the test case "+ testCaseName +"  as "+dto.testCaseStatus());
                } else {
                    regressionLogger.warning("Failed to update the test case in rally due to following errors: "+ Arrays.toString(createResponse.getErrors()));
                }
            }
        } else {
            if (regressionLogger != null){
                regressionLogger.info("Skipping test case update as the story is NOT being tracked");
            }
        }
    }

    public static RallyTestCase getTestCaseByID(String testCaseName, ExtentTest regressionLogger){
        RallyTestCase testCase = new RallyTestCase(testCaseName, regressionLogger);
        QueryRequest queryRequest = new QueryRequest(RallyItemType.TEST_CASE.getItem_type_key());
        queryRequest.setQueryFilter(new QueryFilter("FormattedID", "=", testCaseName));
        testCase.rallyResponse = testCase.query(queryRequest).getResults().get(0).getAsJsonObject();
        String userStoryRef = testCase.rallyResponse.get("WorkProduct").getAsJsonObject().get("_ref").getAsString();
        testCase.relatedUserStory = RallyUserStory.getUserStoryByRef(userStoryRef, regressionLogger);
        return testCase;
    }



    public static void main(String[] args) {
        RallyTestCase testCase = RallyTestCase.getTestCaseByID("TC1994", null);
        testCase.recordTestResult(RallyTestCaseDTO.getInstance(true, "This is a test", RallyUserReference.getUserReference("adeshphande@idfbins.com")));
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public JsonObject getRallyResponse() {
        return rallyResponse;
    }

    public RallyUserStory getRelatedUserStory() {
        return relatedUserStory;
    }
}
