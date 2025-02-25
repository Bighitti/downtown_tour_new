package it.unicam.ids.dciotti.downtowntour.exception;

public class TouristUpgradeException extends Exception {
    public TouristUpgradeException() {
        this(null);
    }

    public TouristUpgradeException(Throwable cause) {
        super("Tourist cannot be upgraded", cause);
    }
}
