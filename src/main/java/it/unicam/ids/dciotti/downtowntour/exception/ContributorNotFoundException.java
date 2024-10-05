package it.unicam.ids.dciotti.downtowntour.exception;

public class ContributorNotFoundException extends Exception {
    public ContributorNotFoundException() {
        this(null);
    }

    public ContributorNotFoundException(Throwable cause) {
        super("Contributor not found", cause);
    }
}
