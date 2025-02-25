package it.unicam.ids.dciotti.downtowntour.exception;

public class CuratorRequiresNoAuthorizationException extends Exception {
    public CuratorRequiresNoAuthorizationException() {
        this(null);
    }

    public CuratorRequiresNoAuthorizationException(Throwable cause) {
        super("Curator does not need any authorization", cause);
    }
}
