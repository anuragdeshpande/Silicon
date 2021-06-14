package framework.logger.eventMessaging;

import framework.logger.RegressionLogger;

import java.util.Optional;

public interface IStartAndEndEvent {

    default <T extends LoggingEvent> Optional<T> startEvent(Class<T> eventClass){
        return RegressionLogger.getFirstAvailableLogger().startEvent(eventClass);
    }

    default <T extends LoggingEvent> void endEvent(Optional<T> event){
        event.ifPresent(RegressionLogger.getFirstAvailableLogger()::endEvent);
    }
}
