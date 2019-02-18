package framework.utils;

public class EnvironmentUtils {

    public static String getOperatingSystemName() {
        String os = System.getProperty("os.name").toLowerCase();
        String operatingSystemName = "windows";

        if (os.contains("win")) {
            operatingSystemName = "windows";
        } else if (os.contains("mac")) {
            operatingSystemName = "mac";
        }else {
            operatingSystemName = "linux";
        }
        return operatingSystemName;
    }
}
