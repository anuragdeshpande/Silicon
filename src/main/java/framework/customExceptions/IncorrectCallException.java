package framework.customExceptions;

public class IncorrectCallException extends RuntimeException{

    public IncorrectCallException() {
        super();
    }

    public IncorrectCallException(String message) {
        super(message);
    }

    public IncorrectCallException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCallException(Throwable cause) {
        super(cause);
    }

    protected IncorrectCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
