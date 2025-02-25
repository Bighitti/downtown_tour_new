package it.unicam.ids.dciotti.downtowntour.exception;

public class ChallengeNotFoundException extends Exception {
    public ChallengeNotFoundException() {
        this(null);
    }

    public ChallengeNotFoundException(Throwable cause) {
        super("Admin not found", cause);
    }
}
