package it.unicam.ids.dciotti.downtowntour.exception;

public class CuratorIntegrityException extends Exception {
    public CuratorIntegrityException() {
        this(null);
    }

    public CuratorIntegrityException(Throwable cause) {
        super("Curator integrity compromised", cause);
    }
}
