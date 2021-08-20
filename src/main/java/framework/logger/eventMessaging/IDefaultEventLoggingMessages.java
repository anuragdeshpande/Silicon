package framework.logger.eventMessaging;

public interface IDefaultEventLoggingMessages {

    static <T extends LoggingEvent> String eventUpdateMessage(final String testName, final String className, final T loggingEvent, final String updateMessage) {
        final String returnMessage = "Test=" + testName + " Class=" + className + " Event=" + loggingEvent.getEventName() + " EventID=" + loggingEvent.getEventID() + " EventUpdate: " + updateMessage;
        if (loggingEvent.getParentEventID().isPresent()) {
            return returnMessage + " ParentEventID=" + loggingEvent.getParentEventID().get();
        } else {
            return returnMessage;
        }
    }

    static <T extends LoggingEvent> String eventEndMessage(final String testName, final String className, final T loggingEvent, final String timeTakenSeconds) {
        final String returnMessage = "Test=" + testName + " Class=" + className + " EventID=" + loggingEvent.getEventID() + " Event=" + loggingEvent.getEventName() + " Ended. TimeTaken=" + timeTakenSeconds;
        if (loggingEvent.getParentEventID().isPresent()) {
            return returnMessage + " ParentEventID=" + loggingEvent.getParentEventID().get();
        } else {
            return returnMessage;
        }
    }
}
