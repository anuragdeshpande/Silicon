package framework.customExceptions;

public class KnownDefectException extends RuntimeException{

    public KnownDefectException() {
    }

    public KnownDefectException(String message) {
        super(message);
    }

    public KnownDefectException(String message, Throwable cause) {
        super(message, cause);
    }

    public KnownDefectException(Throwable cause) {
        super(cause);
    }

    public KnownDefectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
