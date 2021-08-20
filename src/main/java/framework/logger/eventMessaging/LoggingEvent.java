package framework.logger.eventMessaging;

import ch.qos.logback.classic.Logger;
import com.google.common.base.Joiner;
import framework.enums.LogLevel;
import framework.logger.RegressionLogger;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

public final class LoggingEvent implements ILogEvent {

    private final String eventName;
    private final String eventID;
    private final Logger logger = (Logger) LoggerFactory.getLogger("EventLogger");
    //    private final Stack<String> eventMessages;
//    private final HashMap<String, LoggingEvent> subEvents;
    private String currentStateMessage;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private LogEventState currentState;
    private String parentEventID;

    private LoggingEvent(final String eventName) {
        this.eventName = eventName;
        this.eventID = UUID.randomUUID().toString();
        this.currentState = LogEventState.NOT_STARTED;
//        this.eventMessages = new Stack<>();
//        this.subEvents = new HashMap<>();
    }

    public synchronized static LoggingEvent newEvent(final String eventName) {
        return new LoggingEvent(eventName);
    }

    @Override
    public synchronized void startEvent() {
        this.startTimestamp = new Timestamp(System.currentTimeMillis());
        this.currentState = LogEventState.STARTED;
    }

    @Override
    public synchronized void updateEvent(final String updateMessage) {
        _updateEvent(LogLevel.INFO, updateMessage);
    }

    @Override
    public synchronized void updateEvent(final LogLevel logLevel, final String updateMessage) {
        _updateEvent(logLevel, updateMessage);
    }

    private synchronized void _updateEvent(final LogLevel logLevel, final String updateMessage) {
        final String className = RegressionLogger.getFirstAvailableLogger().getTestClassName();
        final String classMethodName = RegressionLogger.getFirstAvailableLogger().getTestName();
        this.currentStateMessage = IDefaultEventLoggingMessages.eventUpdateMessage(classMethodName, className, this, updateMessage);

        switch (logLevel) {
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
    public synchronized void endEvent(final String... additionalTags) {
        final String className = RegressionLogger.getFirstAvailableLogger().getTestClassName();
        final String classMethodName = RegressionLogger.getFirstAvailableLogger().getTestName();
        this.endTimestamp = new Timestamp(System.currentTimeMillis());
        final long timeDiff = (endTimestamp.getTime() - startTimestamp.getTime()) / 1000;
        final String eventEndMessage = IDefaultEventLoggingMessages.eventEndMessage(classMethodName, className, this, timeDiff + " Seconds");
        this.currentState = LogEventState.ENDED;
//        eventMessages.add(eventEndMessage);
        if (ArrayUtils.isNotEmpty(additionalTags)) {
            logger.info(eventEndMessage + Joiner.on(" ").skipNulls().join(additionalTags));
        } else {
            logger.info(eventEndMessage);
        }
    }

    public synchronized Optional<String> getParentEventID() {
        return Optional.ofNullable(this.parentEventID);
    }

    public synchronized void setParentEventID(final String parentEventID) {
        this.parentEventID = parentEventID;
    }

    public synchronized String getEventID() {
        return this.eventID;
    }

//    public List<String> getEventMessages() {
//        return eventMessages;
//    }

    public synchronized String getEventName() {
        return eventName;
    }

//    public Optional<LoggingEvent> getSubEvent(String name){
//        return Optional.ofNullable(subEvents.get(name));
//    }

//    public void addSubEvent(String eventKey, LoggingEvent event){
//        this.subEvents.put(eventKey, event);
//    }

    public synchronized String getCurrentStateMessage() {
        return this.currentStateMessage;
    }

    public synchronized LogEventState getCurrentState() {
        return currentState;
    }

    public synchronized Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public synchronized Timestamp getEndTimestamp() {
        return endTimestamp;
    }
}
