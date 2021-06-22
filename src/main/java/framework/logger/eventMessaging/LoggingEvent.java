package framework.logger.eventMessaging;

import ch.qos.logback.classic.Logger;
import framework.enums.LogLevel;
import framework.logger.RegressionLogger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

public final class LoggingEvent implements ILogEvent {

    protected String eventName;
//    private final Stack<String> eventMessages;
//    private final HashMap<String, LoggingEvent> subEvents;
    private String currentStateMessage;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private String eventID;
    private LogEventState currentState;
    private String parentEventID;
    protected Logger logger = (Logger) LoggerFactory.getLogger("EventLogger");

    private LoggingEvent(String eventName) {
        this.eventName = eventName;
        this.eventID = UUID.randomUUID().toString();
        this.currentState = LogEventState.NOT_STARTED;
//        this.eventMessages = new Stack<>();
//        this.subEvents = new HashMap<>();
    }

    @Override
    public void startEvent() {
        String classMethodName = RegressionLogger.getFirstAvailableLogger().getTestName();
        this.startTimestamp = new Timestamp(System.currentTimeMillis());
        this.currentStateMessage = IDefaultEventLoggingMessages.eventStartMessage(classMethodName, this);
        this.currentState = LogEventState.STARTED;
//        eventMessages.add(eventStartMessage);
        logger.info(this.currentStateMessage);
    }

    @Override
    public void updateEvent(String updateMessage) {
        _updateEvent(LogLevel.INFO, updateMessage);
    }

    @Override
    public void updateEvent(LogLevel logLevel, String updateMessage) {
        _updateEvent(logLevel, updateMessage);
    }

    private void _updateEvent(LogLevel logLevel, String updateMessage){
        String classMethodName = RegressionLogger.getFirstAvailableLogger().getTestName();
        this.currentStateMessage = IDefaultEventLoggingMessages.eventUpdateMessage(classMethodName, this, updateMessage);

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
    public void endEvent() {
        String classMethodName = RegressionLogger.getFirstAvailableLogger().getTestName();
        this.endTimestamp = new Timestamp(System.currentTimeMillis());
        long timeDiff = (endTimestamp.getTime() - startTimestamp.getTime())/1000;
        String eventEndMessage = IDefaultEventLoggingMessages.eventEndMessage(classMethodName, this, timeDiff + " Seconds");
        this.currentState = LogEventState.ENDED;
//        eventMessages.add(eventEndMessage);
        logger.info(eventEndMessage);
    }

    public void setParentEventID(String parentEventID){
        this.parentEventID = parentEventID;
    }

    public Optional<String> getParentEventID(){
        return Optional.ofNullable(this.parentEventID);
    }

    public String getEventID(){
        return this.eventID;
    }


    public String getEventName() {
        return eventName;
    }

//    public List<String> getEventMessages() {
//        return eventMessages;
//    }

    public String getCurrentStateMessage(){
        return this.currentStateMessage;
    }

//    public Optional<LoggingEvent> getSubEvent(String name){
//        return Optional.ofNullable(subEvents.get(name));
//    }

//    public void addSubEvent(String eventKey, LoggingEvent event){
//        this.subEvents.put(eventKey, event);
//    }


    public LogEventState getCurrentState() {
        return currentState;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public static LoggingEvent newEvent(String eventName){
        return new LoggingEvent(eventName);
    }
}
