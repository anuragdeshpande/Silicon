package framework.customExceptions;

public class AccountLockedOrDisabledException extends RuntimeException {
    public AccountLockedOrDisabledException() {
        super();
    }

    public AccountLockedOrDisabledException(String message) {
        super(message);
    }

    public AccountLockedOrDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountLockedOrDisabledException(Throwable cause) {
        super(cause);
    }

    protected AccountLockedOrDisabledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
