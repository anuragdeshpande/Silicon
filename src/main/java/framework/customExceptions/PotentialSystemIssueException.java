package framework.customExceptions;

public class PotentialSystemIssueException extends RuntimeException{
    public PotentialSystemIssueException() {
        super();
    }

    public PotentialSystemIssueException(String message) {
        super(message);
    }

    public PotentialSystemIssueException(String message, Throwable cause) {
        super(message, cause);
    }

    public PotentialSystemIssueException(Throwable cause) {
        super(cause);
    }

    protected PotentialSystemIssueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
