package framework.customExceptions;

public class SimultaneousChangeException extends RuntimeException {

    public SimultaneousChangeException() {
    }

    public SimultaneousChangeException(final String message) {
        super(message);
    }

    public SimultaneousChangeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SimultaneousChangeException(final Throwable cause) {
        super(cause);
    }

    public SimultaneousChangeException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
