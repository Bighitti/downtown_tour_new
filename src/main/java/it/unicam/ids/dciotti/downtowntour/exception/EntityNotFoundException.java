package it.unicam.ids.dciotti.downtowntour.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message); // Passa il messaggio al costruttore della superclasse
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause); // Costruttore che accetta anche una causa
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause); // Passa solo la causa
    }
}