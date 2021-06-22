package framework.reports.models;

import com.aventstack.extentreports.ExtentTest;
import framework.logger.eventMessaging.IMaintainEventNames;
import framework.logger.eventMessaging.LoggingEvent;

import java.util.HashMap;

public class LoggerDTO {
    private ExtentTest extentTest;
    private final HashMap<? super IMaintainEventNames, LoggingEvent> events;

    public LoggerDTO(ExtentTest extentTest) {
        this.extentTest = extentTest;
        this.events = new HashMap<>();
    }

    public ExtentTest getExtentTest() {
        return extentTest;
    }

    public HashMap<? super IMaintainEventNames, LoggingEvent> getEvents() {
        return events;
    }

    public <T extends IMaintainEventNames> void addEvent(T eventName, LoggingEvent event){
        this.events.put(eventName, event);
    }
}
