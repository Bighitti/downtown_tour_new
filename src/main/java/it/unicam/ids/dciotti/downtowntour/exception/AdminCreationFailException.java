package it.unicam.ids.dciotti.downtowntour.exception;

public class AdminCreationFailException extends Exception {
    public AdminCreationFailException() {
        this(null);
    }

    public AdminCreationFailException(Throwable cause) {
        super("Admin creation fail", cause);
    }
}
