package framework.events;

import framework.customExceptions.IncorrectCallException;
import framework.logger.eventMessaging.IMaintainEventNames;

public enum FrameworkEvents implements IMaintainEventNames {

    TAG("Tagged"),
    EXTENDED_TIME("ExtendedTime"),
    CUSTOM_TIMEOUT("CustomTimeout");

    private final String value;

    FrameworkEvents(final String value) {
        this.value = value;
    }

    @Override
    public String getEventName() {
        return value;
    }

    public FrameworkEvents getEnum(final String value) {
        for (final FrameworkEvents type : values()) {
            if (type.getEventName().equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new IncorrectCallException("No GWEvents Type found for: " + value);
    }
}
