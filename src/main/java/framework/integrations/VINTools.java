package framework.integrations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class VINTools {
    public static String getRealVIN(){
        return getVIN("real");
    }

    public static String getFakeValidVIN(){
        return getVIN("fake");
    }

    private static String getVIN(String type){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        try {
            url = new URL("http://randomvin.com/getvin.php?type=" + type);
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
