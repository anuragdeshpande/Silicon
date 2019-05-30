package framework.constants;

import java.util.concurrent.TimeUnit;

public class ReactionTime {

    private long time;
    private TimeUnit timeUnit;

    private ReactionTime(long time, TimeUnit timeUnit){
        this.time = time;
        this.timeUnit = timeUnit;
    }



    public static ReactionTime getInstance(long time, TimeUnit timeUnit){
        return new ReactionTime(time, timeUnit);
    }



    // Constants for ready use
    public static final ReactionTime IMMEDIATE = new ReactionTime(0, TimeUnit.SECONDS);
    public static final ReactionTime MOMENTARY = new ReactionTime(10,TimeUnit.MILLISECONDS);
    public static final ReactionTime ONE_SECOND = new ReactionTime(1, TimeUnit.SECONDS);
    public static final ReactionTime THREE_SECONDS = new ReactionTime(3, TimeUnit.SECONDS);

    // exercise extreme caution when changing this, it is used across the framework as standard time
    public static final ReactionTime STANDARD_WAIT_TIME = new ReactionTime(5, TimeUnit.SECONDS);



    // getters and setters

    public long getTime() {
        return time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
