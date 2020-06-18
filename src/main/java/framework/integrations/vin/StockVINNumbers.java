package framework.integrations.vin;

import framework.constants.ReactionTime;
import framework.integrations.http.HTTPOperations;
import framework.webdriver.BrowserFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class StockVINNumbers implements Serializable {

    private static void reloadVINs() {
        BrowserFactory.getCurrentBrowser().withDOM().injectInfoMessage("Getting 50 New Vin numbers for stock");
        ArrayList<String> vins = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            vins.add(getVIN("Real"));
        }

        writeVins(vins);
        BrowserFactory.getCurrentBrowser().withDOM().injectSuccessMessage("Restocking VIN Complete");
        BrowserFactory.getCurrentBrowser().withDOM().clearBannerMessage();
        BrowserFactory.changeImplicitWaitTo(ReactionTime.STANDARD_WAIT_TIME);
    }

    static String getRealVIN() {
        String vinToReturn = getVIN("Real");
        if (vinToReturn == null) {
            List<String> vins = readVins();
            vinToReturn = vins.get(new Random().nextInt(vins.size()));
            vins.remove(vinToReturn);
            if (vins.size() < 50) {
                reloadVINs();
            } else {
                writeVins(vins);
            }
        }


        return vinToReturn;
    }

    private static void writeVins(List<String> vins) {
        try (FileOutputStream fos = new FileOutputStream(StockVINNumbers.class.getResource("/").getPath() + "/vins.tmp"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(vins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readVins() {
        try (FileInputStream fis = new FileInputStream(StockVINNumbers.class.getResource("/").getPath() + "/vins.tmp"); ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<String>) ois.readObject();
        } catch (FileNotFoundException fnf) {
            reloadVINs();
            return readVins();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static String getVIN(String type) {
        return HTTPOperations.makeGETRequest("http://randomvin.com/getvin.php?type=" + type);
    }
}
