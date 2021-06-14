package framework.logger;

public interface IDefaultEventLoggingMessages {

    static String eventStartMessage(String testName, String eventName){
        return "Test="+testName+" Event="+eventName+" Started.";
    }

    static String eventUpdateMessage(String testName, String eventName, String updateMessage){
        return "Test="+testName+" Event="+eventName+" EventUpdate="+updateMessage+".";
    }

    static String eventEndMessage(String testName, String eventName, String timeTakenSeconds){
        return "Test="+testName+" Event="+eventName+" Ended. TimeTaken="+timeTakenSeconds;
    }
}
