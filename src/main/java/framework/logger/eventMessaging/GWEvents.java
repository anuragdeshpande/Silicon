package framework.logger.eventMessaging;

import framework.customExceptions.IncorrectCallException;

public enum GWEvents implements IMaintainEventNames {
    FATAL_ISSUE("FatalIssue");

    private final String value;

    GWEvents(String value) {
        this.value = value;
    }

    public String getEventName() {
        return value;
    }

    public GWEvents getEnum(String value) {
        for (GWEvents type : values()) {
            if (type.getEventName().equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new IncorrectCallException("No GWEvents Type found for: " + value);
    }
}
