package framework.constants;

import java.io.File;

public final class StringConstants {
    public static final String BLANK = "";
    public static final String TAB = "\t";
    public static final String SPACE = " ";
    public static final String XML_TEST_NAME = "xmlTestName_strvar";
    public static final String TEST_CLASS_NAME = "testClassName_strvar";
    public static final String TEST_NAME = "testName_strvar";
    public static final String PACKAGE_NAME = "packageName_strvar";
    public static final String SUITE_NAME = "suiteName_strvar";
    public static final String DEFAULT_REPORT_LOCATION_PATH = System.getProperty("os.name").toLowerCase().contains("win") ? "\\\\qa\\regression_logs\\" + System.getProperty("globalSuiteName") : "/media/fbmsqa11/regression_logs/"+System.getProperty("globalSuiteName");
    public static final String DEFAULT_MASTER_REPORT_LOCATION_PATH = "http://qa.idfbins.com/regression_logs/"+System.getProperty("globalSuiteName")+"/combinedReport.html";
    public static final String UI_TEST = "UITest";
    public static final String SMOKE_TEST = "SmokeTest";
    public static final String API_TEST = "APITest";
    public static final String DISTRIBUTED_TESTS_FILES_ROOT_LOCATION = System.getProperty("ReportDirectoryFullLocation", "C:\\tmp") + File.separator+"testDistributions";
    public static final String DISTRIBUTED_TESTS_FILES_NON_CLOCK_MOVE_LOCATION = DISTRIBUTED_TESTS_FILES_ROOT_LOCATION +"\\"+"nonClockMove";
    public static final String DISTRIBUTED_TESTS_FILES_CLOCK_MOVE_LOCATION = DISTRIBUTED_TESTS_FILES_ROOT_LOCATION+"\\"+"clockMove";

}
