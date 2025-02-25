package it.unicam.ids.dciotti.downtowntour.exception;

public class TouristNotFoundException extends Exception {
    public TouristNotFoundException() {
        this(null);
    }

    public TouristNotFoundException(Throwable cause) {
        super("Tourist not found", cause);
    }
}
