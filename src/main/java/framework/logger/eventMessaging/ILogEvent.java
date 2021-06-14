package framework.logger.eventMessaging;

public interface ILogEvent {

    void startEvent();
    void updateEvent(String updateMessage);
    void endEvent();
}
