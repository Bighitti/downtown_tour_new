package it.unicam.ids.dciotti.downtowntour.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        this(null);
    }

    public UserNotFoundException(Throwable cause) {
        super("User not found", cause);
    }
}
