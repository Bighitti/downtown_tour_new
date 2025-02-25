package it.unicam.ids.dciotti.downtowntour.exception;

public class AdminCancellationFailException extends Exception {
    public AdminCancellationFailException() {
        this(null);
    }

    public AdminCancellationFailException(Throwable cause) {
        super("Admin cancellation fail", cause);
    }
}
