package it.unicam.ids.dciotti.downtowntour.exception;

public class UserDataRequiredException extends Exception {
    public UserDataRequiredException() {
        this(null);
    }

    public UserDataRequiredException(Throwable cause) {
        super("User data required", cause);
    }
}
