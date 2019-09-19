package framework.integrations.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class HTTPGet {

    public static synchronized String makeGETRequest(String getURL){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        try {
            url = new URL(getURL);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                return line;
            }
        } catch (IOException mue) {
            mue.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }

        return null;
    }
}
