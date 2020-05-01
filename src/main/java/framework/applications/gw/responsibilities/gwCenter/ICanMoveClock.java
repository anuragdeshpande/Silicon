package framework.applications.gw.responsibilities.gwCenter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ICanMoveClock<T> extends ICanConnectToDebugToolsAPI<T>{
    LocalDateTime getCurrentServerDate();
    void moveClockTo(LocalDate date);
}
