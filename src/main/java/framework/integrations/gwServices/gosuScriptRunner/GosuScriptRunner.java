package framework.integrations.gwServices.gosuScriptRunner;

import framework.applications.gw.GuidewireCenter;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;

import static framework.integrations.http.HTTPOperations.makeHTTPGETRequest;
import static framework.integrations.http.HTTPOperations.makeMultiPartPOSTRequest;

public class GosuScriptRunner<T extends GuidewireCenter> {

    private final T guidewireCenter;

    public GosuScriptRunner(T guidewireCenter) {
        this.guidewireCenter = guidewireCenter;
    }

    public void registerScript(String gspScriptResourcePathReference, String jsonMetaDataResourcePathReference){
        File gspFile = new File(gspScriptResourcePathReference);
        File jsonMetaFile = new File(jsonMetaDataResourcePathReference);
        MultipartEntityBuilder postRequestBody = MultipartEntityBuilder.create();
        postRequestBody.addBinaryBody("file", gspFile, ContentType.APPLICATION_OCTET_STREAM, "gspFile.gsp");
        postRequestBody.addBinaryBody("file", jsonMetaFile, ContentType.APPLICATION_JSON, "gspMeta.json");
        makeMultiPartPOSTRequest(guidewireCenter.getEnvironment().getEnvironmentUrl(), postRequestBody);
    }

    public void runScript(String externalReferenceFromJSONFile){
        String scriptStatus = getStatus(externalReferenceFromJSONFile);
        if(scriptStatus.equalsIgnoreCase("Open")){
            makeHTTPGETRequest(guidewireCenter.getEnvironment().getEnvironmentUrl()+"service/datachange/"+externalReferenceFromJSONFile+"/run");
        } else {
            throw new RuntimeException("Couldn't run the script. The script status is not Open. Found: "+scriptStatus);
        }
    }

    public String getStatus(String externalReferenceFromJSONFile){
        return makeHTTPGETRequest(guidewireCenter.getEnvironment().getEnvironmentUrl()+"service/datachange/"+externalReferenceFromJSONFile+"/status");
    }
}
