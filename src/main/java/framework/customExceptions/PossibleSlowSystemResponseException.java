package framework.customExceptions;

public class PossibleSlowSystemResponseException extends RuntimeException {
    public PossibleSlowSystemResponseException() {
    }

    public PossibleSlowSystemResponseException(final String message) {
        super(message);
    }

    public PossibleSlowSystemResponseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PossibleSlowSystemResponseException(final Throwable cause) {
        super(cause);
    }

    public PossibleSlowSystemResponseException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
