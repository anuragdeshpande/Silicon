package framework.applications.gw.responsibilities.gwCenter;

import framework.enums.LogLevel;

public interface ILogManagement {
    void setLogLevel(String loggerName, LogLevel logLevel);
}
