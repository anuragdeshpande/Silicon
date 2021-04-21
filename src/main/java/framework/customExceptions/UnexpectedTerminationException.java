package framework.customExceptions;

public class UnexpectedTerminationException extends RuntimeException{
    public UnexpectedTerminationException() {
    }

    public UnexpectedTerminationException(String message) {
        super(message);
    }

    public UnexpectedTerminationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedTerminationException(Throwable cause) {
        super(cause);
    }

    public UnexpectedTerminationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
