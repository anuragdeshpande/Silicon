package framework.enums;

import framework.customExceptions.IncorrectCallException;

import java.util.Arrays;

public enum FrameworkSystemTags {
    BLOCKED_MESSAGE_QUEUE("BlockedMessageQueue"),
    POTENTIAL_SYSTEM_FAILURE("PotentialSystemFailure"),
    ERROR_ON_SCREEN("ErrorOnScreen"),
    ACTIVE_DEFECT("ActiveDefect"),
    CLOCK_MOVED("ClockMoved");


    private final String value;

    FrameworkSystemTags(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FrameworkSystemTags getEnum(String value) {
        return Arrays.stream(values()).parallel().filter(tag -> tag.getValue().equalsIgnoreCase(value))
                .findFirst().orElseThrow(() -> new IncorrectCallException("No FrameworkSystemTags Type found for: " + value));
    }

    public static boolean hasEnum(String value){
        try{
            getEnum(value);
            return true;
        } catch (IncorrectCallException e){
            return false;
        }
    }
}
