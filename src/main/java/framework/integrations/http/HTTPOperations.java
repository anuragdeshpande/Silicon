package framework.integrations.http;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class HTTPOperations {

    public static synchronized String makeGETRequest(String getURL) {
        URL url;
        BufferedReader br;
        String line;

        try {
            url = new URL(getURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "PostmanRuntime/7.26.10");
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            con.setRequestProperty("Connection", "keep-alive");
            con.addRequestProperty("Host", "www.mysite.com");
            con.addRequestProperty("Cache-Control","max-age=0");
            con.addRequestProperty("Accept-Language","en-US,en;q=0.8");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream())));
                StringBuilder response = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();
                return response.toString();
            } else {
                System.out.println("GET Request did not work. Got: "+responseCode);
            }

        } catch (IOException mue) {
            mue.printStackTrace();
        }
        return null;
    }


    public static synchronized void makeMultiPartPOSTRequest(String postURL, MultipartEntityBuilder postRequestBody) {
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

    public static synchronized String makeHTTPGETRequest(String getURL){
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
