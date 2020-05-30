package framework.database.models;

import com.aventstack.extentreports.Status;

import java.util.HashMap;
import java.util.List;

public class StatusResultDTO {
    private String testStatus;
    private int testCount;

    public String getTestStatus() {
        return testStatus;
    }

    public int getTestCount() {
        return testCount;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public void setTestCount(int testCount) {
        this.testCount = testCount;
    }

    public static HashMap<Status, Integer> convertToStatusMap(List<StatusResultDTO> dtoList){
        HashMap<Status, Integer> map = new HashMap<>();
        if(!dtoList.isEmpty()){
            dtoList.forEach(statusResultDTO -> map.put(Status.valueOf(statusResultDTO.getTestStatus().toUpperCase()), statusResultDTO.getTestCount()));
        }

        if(!map.containsKey(Status.PASS)){
            map.put(Status.PASS, 0);
        }

        if(!map.containsKey(Status.WARNING)){
            map.put(Status.WARNING, 0);
        }

        if(!map.containsKey(Status.FAIL)){
            map.put(Status.FAIL, 0);
        }

        if(!map.containsKey(Status.FATAL)){
            map.put(Status.FATAL, 0);
        }

        if(!map.containsKey(Status.SKIP)){
            map.put(Status.SKIP, 0);
        }

        return map;
    }
}
