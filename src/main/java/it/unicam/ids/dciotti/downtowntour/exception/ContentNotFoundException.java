package it.unicam.ids.dciotti.downtowntour.exception;

public class ContentNotFoundException extends Exception {
    public ContentNotFoundException() {
        this(null);
    }

    public ContentNotFoundException(Throwable cause) {
        super("Content not found", cause);
    }
}
