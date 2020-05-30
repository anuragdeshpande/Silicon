package framework.integrations.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPGet {

    public static synchronized String makeGETRequest(String getURL){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        try {
            url = new URL(getURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code: "+responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK){
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();
                return response.toString();
            } else {
                System.out.println("GET Request did not work");
            }

        } catch (IOException mue) {
            mue.printStackTrace();
        }
        return null;
    }
}
