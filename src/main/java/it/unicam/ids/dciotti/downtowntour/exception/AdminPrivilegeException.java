package it.unicam.ids.dciotti.downtowntour.exception;

public class AdminPrivilegeException extends Exception {
    public AdminPrivilegeException() {
        this(null);
    }

    public AdminPrivilegeException(Throwable cause) {
        super("Admin privilege", cause);
    }
}
