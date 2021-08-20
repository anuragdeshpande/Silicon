package framework.logger.eventMessaging;

public interface IDefaultEventLoggingMessages {

    static <T extends LoggingEvent> String eventStartMessage(String testName, T event){
        return "Test="+testName+" EventID="+event.getEventID()+" Event="+ event.getEventName()+" Started.";
    }

    static <T extends LoggingEvent> String eventUpdateMessage(String testName, T loggingEvent , String updateMessage){
        String returnMessage = "Test="+testName+" Event="+loggingEvent.getEventName()+" EventID="+loggingEvent.getEventID()+" EventUpdate="+updateMessage;
        if (loggingEvent.getParentEventID().isPresent()) {
            return returnMessage +" ParentEventID="+loggingEvent.getParentEventID().get();
        } else {
            return returnMessage;
        }
    }

    static <T extends LoggingEvent> String eventEndMessage(String testName, String className, T loggingEvent , String timeTakenSeconds){
        String returnMessage = "Test=" + testName + " Class="+className+" EventID=" + loggingEvent.getEventID() + " Event=" + loggingEvent.getEventName() + " Ended. TimeTaken=" + timeTakenSeconds;
        if(loggingEvent.getParentEventID().isPresent()){
            return returnMessage +" ParentEventID="+loggingEvent.getParentEventID().get();
        } else {
            return returnMessage;
        }
    }
}
