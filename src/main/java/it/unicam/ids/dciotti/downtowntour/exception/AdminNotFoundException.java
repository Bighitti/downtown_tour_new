package it.unicam.ids.dciotti.downtowntour.exception;

public class AdminNotFoundException extends Exception {
    public AdminNotFoundException() {
        this(null);
    }

    public AdminNotFoundException(Throwable cause) {
        super("Admin not found", cause);
    }
}
