package framework.enums;

import framework.customExceptions.IncorrectCallException;

public enum FrameworkSystemEvents {
    CUSTOM_TIMEOUT("Custom_Timeout"),
    EXTENDED_WAIT_TIME("extended_wait_time");

    private final String value;

    FrameworkSystemEvents(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public FrameworkSystemEvents getEnum(String value) {
        for (FrameworkSystemEvents type : values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new IncorrectCallException("No FrameworkSystemEvents Type found for: " + value);
    }
}
