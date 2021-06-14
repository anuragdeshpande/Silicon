package framework.guidewire.events;

import framework.logger.eventMessaging.LoggingEvent;

public class LogoutEvent extends LoggingEvent {

    public LogoutEvent() {
        super(LogoutEvent.class.getSimpleName());
    }
}
