package it.unicam.ids.dciotti.downtowntour.exception;

public class InsertFailException extends Exception {
    public InsertFailException() {
        this(null);
    }

    public InsertFailException(Throwable cause) {
        super("Insert fail", cause);
    }
}
