package framework.logger.eventMessaging;

import framework.logger.RegressionLogger;

public interface IManageLoggingEvents {

    default <T extends IMaintainEventNames> LoggingEvent startEvent(T eventName){
        return RegressionLogger.getTestClassLogger().startEvent(eventName);
    }

    default <T extends IMaintainEventNames> LoggingEvent getEvent(T eventname){
        return RegressionLogger.getTestClassLogger().getEvent(eventname);
    }

    default void endEvent(LoggingEvent event){
        RegressionLogger.getTestClassLogger().endEvent(event);
    }
}
