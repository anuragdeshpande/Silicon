package framework.logger.eventMessaging;

import ch.qos.logback.classic.Logger;
import framework.enums.LogLevel;
import framework.logger.RegressionLogger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

public final class LoggingEvent implements ILogEvent {

    private final String eventName;
//    private final Stack<String> eventMessages;
//    private final HashMap<String, LoggingEvent> subEvents;
    private String currentStateMessage;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private final String eventID;
    private LogEventState currentState;
    private String parentEventID;
    private final Logger logger = (Logger) LoggerFactory.getLogger("EventLogger");

    private LoggingEvent(String eventName) {
        this.eventName = eventName;
        this.eventID = UUID.randomUUID().toString();
        this.currentState = LogEventState.NOT_STARTED;
//        this.eventMessages = new Stack<>();
//        this.subEvents = new HashMap<>();
    }

    @Override
    public synchronized void startEvent() {
        this.startTimestamp = new Timestamp(System.currentTimeMillis());
        this.currentState = LogEventState.STARTED;
    }

    @Override
    public synchronized void updateEvent(String updateMessage) {
        _updateEvent(LogLevel.INFO, updateMessage);
    }

    @Override
    public synchronized void updateEvent(LogLevel logLevel, String updateMessage) {
        _updateEvent(logLevel, updateMessage);
    }

    private synchronized void _updateEvent(LogLevel logLevel, String updateMessage){
        String className = RegressionLogger.getFirstAvailableLogger().getTestClassName();
        String classMethodName = RegressionLogger.getFirstAvailableLogger().getTestName();
        this.currentStateMessage = IDefaultEventLoggingMessages.eventUpdateMessage(classMethodName,className, this, updateMessage);

        switch (logLevel){
            case ERROR:
                logger.error(currentStateMessage);
                break;
            case WARNING:
                logger.warn(currentStateMessage);
                break;
            case DEBUG:
            case TRACE:
            case INFO:
            default:
                logger.info(currentStateMessage);
                break;

        }
//        eventMessages.add(eventUpdateMessage);
    }

    @Override
    public synchronized void endEvent() {
        String className = RegressionLogger.getFirstAvailableLogger().getTestClassName();
        String classMethodName = RegressionLogger.getFirstAvailableLogger().getTestName();
        this.endTimestamp = new Timestamp(System.currentTimeMillis());
        long timeDiff = (endTimestamp.getTime() - startTimestamp.getTime())/1000;
        String eventEndMessage = IDefaultEventLoggingMessages.eventEndMessage(classMethodName, className, this, timeDiff + " Seconds");
        this.currentState = LogEventState.ENDED;
//        eventMessages.add(eventEndMessage);
        logger.info(eventEndMessage);
    }

    public synchronized void setParentEventID(String parentEventID){
        this.parentEventID = parentEventID;
    }

    public synchronized Optional<String> getParentEventID(){
        return Optional.ofNullable(this.parentEventID);
    }

    public synchronized String getEventID(){
        return this.eventID;
    }


    public synchronized String getEventName() {
        return eventName;
    }

//    public List<String> getEventMessages() {
//        return eventMessages;
//    }

    public synchronized String getCurrentStateMessage(){
        return this.currentStateMessage;
    }

//    public Optional<LoggingEvent> getSubEvent(String name){
//        return Optional.ofNullable(subEvents.get(name));
//    }

//    public void addSubEvent(String eventKey, LoggingEvent event){
//        this.subEvents.put(eventKey, event);
//    }


    public synchronized LogEventState getCurrentState() {
        return currentState;
    }

    public synchronized Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public synchronized Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public synchronized static LoggingEvent newEvent(String eventName){
        return new LoggingEvent(eventName);
    }
}
