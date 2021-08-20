package framework.events;

import framework.customExceptions.IncorrectCallException;
import framework.logger.eventMessaging.IMaintainEventNames;

public enum FrameworkEvents implements IMaintainEventNames {

    ;

    private final String value;

    FrameworkEvents(String value) {
        this.value = value;
    }

    @Override
    public String getEventName() {
        return value;
    }

    public FrameworkEvents getEnum(String value) {
        for (FrameworkEvents type : values()) {
            if (type.getEventName().equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new IncorrectCallException("No GWEvents Type found for: " + value);
    }
}
