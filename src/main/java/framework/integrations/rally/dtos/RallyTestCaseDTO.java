package framework.integrations.rally.dtos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class RallyTestCaseDTO {
    private boolean hasPassed;
    private String notes;
    private String rallyUserRef;
    private String dateOfTestCase;

    private RallyTestCaseDTO(boolean hasPassed, String notes, String rallyUserRef) {
        this.hasPassed = hasPassed;
        this.notes = notes;
        this.rallyUserRef = rallyUserRef;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.dateOfTestCase = sdf.format(new Date());
    }

    // Getter
    public boolean hasPassed() {
        return hasPassed;
    }

    public String testCaseStatus(){
        return hasPassed ? "Pass" : "Fail";
    }

    public String getNotes() {
        return notes;
    }

    public String getRallyUserRef() {
        return rallyUserRef;
    }

    public String getDateOfTestCase() {
        return dateOfTestCase;
    }

    // Instantiation
    public static RallyTestCaseDTO getInstance(boolean hasPassed, String notes, String rallyUserRef){
        return new RallyTestCaseDTO(hasPassed, notes, rallyUserRef);
    }
}
