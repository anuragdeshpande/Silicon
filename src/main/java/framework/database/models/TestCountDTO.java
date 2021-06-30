package framework.database.models;

import com.aventstack.extentreports.Status;
import framework.database.ConnectionManager;
import framework.enums.ApplicationNames;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class TestCountDTO {
    private int passCount;
    private int failCount;
    private int skipCount;
    private int warningCount;

    public TestCountDTO() {
    }

    public static TestCountDTO getTestCountDataFor(String uuid, String suiteName){
        QueryRunner testNGReportingServer = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        List<StatusResultDTO> resultDTOS;
        try {
            resultDTOS = testNGReportingServer.query("select t.TestStatus as testStatus, count(TestStatus) as testCount from TestResults t where t.UUID = '" + uuid + "' and SuiteName= '"+suiteName+"' group by t.TestStatus",
                    new BeanListHandler<>(StatusResultDTO.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        HashMap<Status, Integer> map = StatusResultDTO.convertToStatusMap(resultDTOS);
        int passTestCount = map.get(Status.PASS);
        int failTestCount = map.get(Status.FAIL);
        int skipTestCount = map.get(Status.SKIP);
        int warningTestCount = map.get(Status.WARNING);

        TestCountDTO testCountDTO = new TestCountDTO();
        testCountDTO.setPassCount(passTestCount);
        testCountDTO.setFailCount(failTestCount);
        testCountDTO.setSkipCount(skipTestCount);
        testCountDTO.setWarningCount(warningTestCount);

        return testCountDTO;
    }


    public static TestCountDTO getTestCountDataFor(String uuid, ApplicationNames applicationName){
        QueryRunner testNGReportingServer = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        List<StatusResultDTO> resultDTOS;
        try {
            resultDTOS = testNGReportingServer.query("select t.TestStatus as testStatus, count(TestStatus) as testCount from TestResults t where t.UUID = '" + uuid + "' and Tags like '%"+applicationName.getFullName()+"%' group by t.TestStatus",
                    new BeanListHandler<>(StatusResultDTO.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        HashMap<Status, Integer> map = StatusResultDTO.convertToStatusMap(resultDTOS);
        int passTestCount = map.get(Status.PASS);
        int failTestCount = map.get(Status.FAIL);
        int skipTestCount = map.get(Status.SKIP);
        int warningTestCount = map.get(Status.WARNING);

        TestCountDTO testCountDTO = new TestCountDTO();
        testCountDTO.setPassCount(passTestCount);
        testCountDTO.setFailCount(failTestCount);
        testCountDTO.setSkipCount(skipTestCount);
        testCountDTO.setWarningCount(warningTestCount);

        return testCountDTO;
    }

    public int getPassCount() {
        return passCount;
    }

    public int getFailCount() {
        return failCount;
    }


    public int getSkipCount() {
        return skipCount;
    }

    public int getWarningCount() {
        return warningCount;
    }

    public void setPassCount(int passCount) {
        this.passCount = passCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }


    public void setSkipCount(int skipCount) {
        this.skipCount = skipCount;
    }

    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }
}
