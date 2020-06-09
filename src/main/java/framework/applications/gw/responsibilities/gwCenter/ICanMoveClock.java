package framework.applications.gw.responsibilities.gwCenter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ICanMoveClock{
    LocalDateTime getCurrentServerDate();
    void moveClockTo(LocalDateTime date);
}
