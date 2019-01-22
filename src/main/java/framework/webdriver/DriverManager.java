package framework.webdriver;

import framework.database.ConnectionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import java.net.URL;
import java.sql.Date;
import java.util.Properties;
import java.util.function.Supplier;

public class DriverManager {

    //chrome driver supplier
    public static Supplier<WebDriver> chromeDriverSupplier = () -> {

        Properties properties = new Properties();
        String os = System.getProperty("os.name").toLowerCase();
        String operatingSystemName = "windows";

        if (os.contains("win")) {
            operatingSystemName = "windows";
        } else if (os.contains("mac")) {
            operatingSystemName = "mac";
        }else {
            operatingSystemName = "linux";
        }

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver/"+operatingSystemName+"/chromedriver.exe");
        try {
            properties.load(DriverManager.class.getResourceAsStream("config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String runtimeEnvironment = properties.getProperty("runtimeEnvironment");
        String hubUrl = System.getProperty("hubUrl");

        if (hubUrl == null) {
            if (runtimeEnvironment.equalsIgnoreCase("grid")) {
                hubUrl = properties.getProperty("hubUrl");
            }
        }

        if (hubUrl != null) {
            try {
                return new RemoteWebDriver(new URL(hubUrl), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ChromeDriver();
    };

    public static boolean recordTestResult(ITestResult iTestResult, String status) {

        String applicationName = "CC8DEV";
        String themeName = "TestStuff";
        boolean clockMove = false;
        String testCreator = "Tester";
        String testTeam = "Tester Team";
        boolean testClassEnabled = false;
        String methodName = iTestResult.getMethod().getMethodName();
        Date startDate = new Date(iTestResult.getStartMillis());
        Date endDate = new Date(iTestResult.getEndMillis());
        String failureImageURL = null;
        String failureReason = null;
        String clockMoveTimeUnit = "Test Time Unit";
        int clockMoveAmount = 0;
        String buildNumber = System.getProperty("jenkinsBuildNumber");
        String suiteName = iTestResult.getTestContext().getSuite().getName();
        String testRunSource = System.getProperty("startedByUser");

        if (status.equalsIgnoreCase("Failed")) {
            failureImageURL = null;
            failureReason = iTestResult.getThrowable().getLocalizedMessage();
        }

        QueryRunner regressionDB = ConnectionManager.getRegressionDB();
        try {
            return regressionDB
                    .update("INSERT INTO TestResults" +
                                    "(ApplicationName, ThemeName, ClockMove, TestCreator, TestTeam, TestClassEnabled, MethodName, " +
                                    "StartTime, EndTime, FailureImageURL, TestStatus, FailureReason, ClockMoveTimeUnit, ClockMoveAmount, " +
                                    "buildNumber, SuiteName, TestRunSource) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                            applicationName, themeName, clockMove, testCreator, testTeam, testClassEnabled, methodName,
                            startDate, endDate, failureImageURL, status, failureReason, clockMoveTimeUnit, clockMoveAmount,
                            buildNumber, suiteName, testRunSource) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
