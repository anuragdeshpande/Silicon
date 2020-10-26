import framework.ReportManager;

import java.io.IOException;

public class CombineReports {
    public static void main(String[] args) throws IOException {
        String targetLocation = System.getProperty("targetReportLocation");
        String[] reportFileLocations = System.getProperty("reportFileLocations", "").split(",");
        ReportManager.generateCombinedReports(targetLocation, reportFileLocations);
    }
}
