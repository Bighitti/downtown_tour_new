package it.unicam.ids.dciotti.downtowntour.exception;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        this(null);
    }

    public InvalidCredentialsException(Throwable cause) {
        super("Invalid credentials", cause);
    }
}
