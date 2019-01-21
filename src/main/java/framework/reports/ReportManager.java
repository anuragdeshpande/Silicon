package framework.reports;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ReportManager {

    private ReportManager() {

    }

    public static ExtentReports initiate() {
        ExtentReports extentReports = new ExtentReports();
        extentReports.setAnalysisStrategy(AnalysisStrategy.SUITE);
        ExtentHtmlReporter extentReporter;

        // Create new directory and files
        String rootPath = "\\\\qa\\regression_logs\\";
        String resultsFileName = System.getProperty("reportFileName");
        String directoryName = rootPath + resultsFileName;

        // Set path to report
        if (new File(directoryName).mkdir()) {
            extentReporter = new ExtentHtmlReporter(directoryName + "\\" + resultsFileName + ".html");
        } else {
            extentReporter = new ExtentHtmlReporter(rootPath + resultsFileName + ".html");
        }

        // Configurations
        extentReporter.config().enableTimeline(true);
        extentReporter.config().setAutoCreateRelativePathMedia(true);
        extentReporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(extentReporter);

        return extentReports;
    }
}
