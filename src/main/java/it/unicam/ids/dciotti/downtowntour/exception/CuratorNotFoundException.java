package it.unicam.ids.dciotti.downtowntour.exception;

public class CuratorNotFoundException extends Exception {
    public CuratorNotFoundException() {
        this(null);
    }

    public CuratorNotFoundException(Throwable cause) {
        super("Curator not found", cause);
    }
}
