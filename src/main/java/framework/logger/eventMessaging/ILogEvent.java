package framework.logger.eventMessaging;

import framework.enums.LogLevel;

public interface ILogEvent {

    void startEvent();
    void updateEvent(String updateMessage);
    void updateEvent(LogLevel logLevel, String updateMessage);
    void endEvent();
}
