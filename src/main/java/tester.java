import java.io.File;
import java.io.IOException;

public class tester {
    public static void main(String[] args) throws IOException {
        String path = "\\\\qa\\regression_logs\\null\\null.html";
        if(!new File(path).exists()){
            if(new File("\\\\qa\\regression_logs\\null").mkdir()){
                new File(path).createNewFile();
            }

        }

    }
}
