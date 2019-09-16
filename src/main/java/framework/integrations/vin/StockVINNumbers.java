package framework.integrations.vin;

import framework.constants.ReactionTime;
import framework.webdriver.BrowserFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockVINNumbers implements Serializable {

    private static void reloadVINs(){
        System.out.println("Getting 50 New Vin numbers for stock");
        ArrayList<String> vins = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            vins.add(getVIN("Real"));
        }

        writeVins(vins);
        System.out.println("Restocking VIN Complete.");
       BrowserFactory.changeImplicitWaitTo(ReactionTime.STANDARD_WAIT_TIME);
    }

    public static String getRealVIN(){
//        String vinToReturn = getVIN("Real");
//        if(vinToReturn == null){
            List<String> vins = readVins();
            String vinToReturn = vins.get(new Random().nextInt(vins.size()));
            vins.remove(vinToReturn);
            if(vins.size() < 50){
                reloadVINs();
            } else {
                writeVins(vins);
            }
//        }


        return vinToReturn;
    }

    private static void writeVins(List<String> vins){
        try(FileOutputStream fos = new FileOutputStream(StockVINNumbers.class.getResource("/").getPath()+"/vins.tmp"); ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(vins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readVins(){
        try(FileInputStream fis = new FileInputStream(StockVINNumbers.class.getResource("/").getPath()+"/vins.tmp"); ObjectInputStream ois = new ObjectInputStream(fis)){
            return (List<String>) ois.readObject();
        } catch (FileNotFoundException fnf){
            reloadVINs();
            return readVins();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static String getVIN(String type){
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
