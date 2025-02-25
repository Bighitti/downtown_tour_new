package it.unicam.ids.dciotti.downtowntour.exception;

public class ContentExistsException extends Exception {
    public ContentExistsException() {
        this(null);
    }

    public ContentExistsException(Throwable cause) {
        super("Content exists", cause);
    }
}
