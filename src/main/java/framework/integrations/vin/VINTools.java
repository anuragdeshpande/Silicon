package framework.integrations.vin;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class VINTools {
    public static String getRealVIN(){
        return getVIN("real");
    }

    public static String getFakeValidVIN(){
        return getVIN("fake");
    }

    public static Vehicle decodeVIN(String vinNumber){
        String decodedVINDetails = queryVINDetails(vinNumber);
        if(decodedVINDetails != null){
            JSONObject jsonResult = new JSONObject(decodedVINDetails);
            JSONObject results = jsonResult.getJSONArray("Results").getJSONObject(0);
            String make = results.getString("Make");
            String model = results.getString("Model");
            String year = results.getString("ModelYear");

            return new Vehicle(year,make, model, vinNumber);

        }

        return null;
    }

    private static String queryVINDetails(String vinNumber){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        try {
            url = new URL("https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVinValues/"+vinNumber+"?format=json");
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
