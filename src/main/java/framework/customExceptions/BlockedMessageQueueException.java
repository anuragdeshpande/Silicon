package framework.customExceptions;

public class BlockedMessageQueueException extends RuntimeException{
    public BlockedMessageQueueException() {
        super();
    }

    public BlockedMessageQueueException(String message) {
        super(message);
    }

    public BlockedMessageQueueException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockedMessageQueueException(Throwable cause) {
        super(cause);
    }

    protected BlockedMessageQueueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
