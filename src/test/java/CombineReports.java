import framework.ReportManager;

import java.io.IOException;

public class CombineReports {
    public static void main(String[] args) throws IOException {
        String targetLocation = System.getProperty("targetReportLocation");
        String forMasterFile = System.getProperty("forMasterFile", "false");
        String[] reportFileLocations = System.getProperty("reportFileLocations", "").split(",");
        ReportManager.generateCombinedReports(Boolean.parseBoolean(forMasterFile), targetLocation, reportFileLocations);
    }
}
