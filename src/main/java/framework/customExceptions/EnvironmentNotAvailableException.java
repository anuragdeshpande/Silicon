package framework.customExceptions;

public class EnvironmentNotAvailableException extends RuntimeException{
    public EnvironmentNotAvailableException() {
        super();
    }

    public EnvironmentNotAvailableException(String message) {
        super(message);
    }

    public EnvironmentNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnvironmentNotAvailableException(Throwable cause) {
        super(cause);
    }

    protected EnvironmentNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
