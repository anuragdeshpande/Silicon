package framework.guidewire.events;

import framework.customExceptions.IncorrectCallException;
import framework.logger.eventMessaging.IMaintainEventNames;

public enum GWEvents implements IMaintainEventNames {
    LOGIN("Login"),
    LOGOUT("Logout"),
    BATCH_JOB("BatchJob"),
    WORK_QUEUE("WorkQueue");

    private final String value;

    GWEvents(String value) {
        this.value = value;
    }

    @Override
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
