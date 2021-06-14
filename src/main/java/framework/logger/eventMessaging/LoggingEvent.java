package framework.logger.eventMessaging;

import ch.qos.logback.classic.Logger;
import framework.logger.IDefaultEventLoggingMessages;
import framework.logger.RegressionLogger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public abstract class LoggingEvent implements ILogEvent {

    protected String eventName;
    private final Stack<String> eventMessages;
    private final HashMap<String, LoggingEvent> subEvents;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    protected Logger logger = (Logger) LoggerFactory.getLogger("EventLogger");

    public LoggingEvent(String eventName) {
        this.eventName = eventName;
        this.eventMessages = new Stack<>();
        this.subEvents = new HashMap<>();
    }

    @Override
    public void startEvent() {
        String classMethodName = RegressionLogger.getFirstAvailableLogger().getTestName();
        this.startTimestamp = new Timestamp(System.currentTimeMillis());
        String eventStartMessage = IDefaultEventLoggingMessages.eventStartMessage(classMethodName, getEventName());
        eventMessages.add(eventStartMessage);
        logger.info(eventStartMessage);
    }

    @Override
    public void updateEvent(String updateMessage) {
        String classMethodName = RegressionLogger.getFirstAvailableLogger().getTestName();
        String eventUpdateMessage = IDefaultEventLoggingMessages.eventUpdateMessage(classMethodName, getEventName(), updateMessage);
        eventMessages.add(eventUpdateMessage);
        logger.info(eventUpdateMessage);
    }

    @Override
    public void endEvent() {
        String classMethodName = RegressionLogger.getFirstAvailableLogger().getTestName();
        this.endTimestamp = new Timestamp(System.currentTimeMillis());
        long timeDiff = (endTimestamp.getTime() - startTimestamp.getTime())/1000;
        String eventEndMessage = IDefaultEventLoggingMessages.eventEndMessage(classMethodName, getEventName(), timeDiff + " Seconds");
        eventMessages.add(eventEndMessage);
        logger.info(eventEndMessage);
    }


    public String getEventName() {
        return eventName;
    }

    public List<String> getEventMessages() {
        return eventMessages;
    }

    public String getCurrentState(){
        return eventMessages.peek();
    }

    public Optional<LoggingEvent> getSubEvent(String name){
        return Optional.ofNullable(subEvents.get(name));
    }

    public void addSubEvent(String eventKey, LoggingEvent event){
        this.subEvents.put(eventKey, event);
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }
}
