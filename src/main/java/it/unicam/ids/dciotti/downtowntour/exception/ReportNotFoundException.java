package it.unicam.ids.dciotti.downtowntour.exception;

public class ReportNotFoundException extends Exception {
    public ReportNotFoundException() {
        this(null);
    }

    public ReportNotFoundException(Throwable cause) {
        super("Report not found", cause);
    }
}
