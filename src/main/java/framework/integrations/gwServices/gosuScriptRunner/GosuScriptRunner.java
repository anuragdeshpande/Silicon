package framework.integrations.gwServices.gosuScriptRunner;

import framework.applications.gw.GuidewireCenter;
import framework.integrations.http.HTTPOperations;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

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
            makeGETRequest(guidewireCenter.getEnvironment().getEnvironmentUrl()+"service/datachange/"+externalReferenceFromJSONFile+"/run");
        } else {
            throw new RuntimeException("Couldn't run the script. The script status is not Open. Found: "+scriptStatus);
        }
    }

    public String getStatus(String externalReferenceFromJSONFile){
        return makeGETRequest(guidewireCenter.getEnvironment().getEnvironmentUrl()+"service/datachange/"+externalReferenceFromJSONFile+"/status");
    }

    private static synchronized void makeMultiPartPOSTRequest(String postURL, MultipartEntityBuilder postRequestBody) {
        CloseableHttpClient client;
        try {
            client = HttpClients.createDefault();
            HttpPost post = new HttpPost(postURL);
            post.setHeader(HttpHeaders.AUTHORIZATION, "Basic "+ Base64.getEncoder().encodeToString("su:gw".getBytes()));
            post.setEntity(postRequestBody.build());
            CloseableHttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            client.close();

            if (statusCode != 200) {
                throw new RuntimeException("Unexpected status code. Post request did not return 200. Instead returned: " + statusCode);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static synchronized String makeGETRequest(String getURL){
        CloseableHttpClient client;
        try {
            client = HttpClients.createDefault();
            HttpGet get = new HttpGet(getURL);
            get.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("su:gw".getBytes()));
            CloseableHttpResponse response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != 200){
                throw new RuntimeException("Unexpected status code. Post request did not return 200. Instead returned: " + statusCode);
            }

            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
