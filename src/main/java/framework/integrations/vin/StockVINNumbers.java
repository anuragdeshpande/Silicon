package framework.integrations.vin;

import framework.constants.ReactionTime;
import framework.integrations.http.HTTPGet;
import framework.webdriver.BrowserFactory;

import java.io.*;
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
        String vinToReturn = getVIN("Real");
        if(vinToReturn == null){
            List<String> vins = readVins();
            vinToReturn = vins.get(new Random().nextInt(vins.size()));
            vins.remove(vinToReturn);
            if(vins.size() < 50){
                reloadVINs();
            } else {
                writeVins(vins);
            }
        }


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
        return HTTPGet.makeGETRequest("http://randomvin.com/getvin.php?type=" + type);
    }
}
