package kdan.jessica.phantommask.service.ex;

public class BalanceNotEnough extends ServiceException{
    public BalanceNotEnough() {
    }

    public BalanceNotEnough(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BalanceNotEnough(String message, Throwable cause) {
        super(message, cause);
    }

    public BalanceNotEnough(String message) {
        super(message);
    }

    public BalanceNotEnough(Throwable cause) {
        super(cause);
    }
}
