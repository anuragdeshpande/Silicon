package framework.guidewire.events;

import framework.logger.eventMessaging.LoggingEvent;

public class LoginEvent extends LoggingEvent {

    public LoginEvent() {
        super(LoginEvent.class.getSimpleName());
    }
}
