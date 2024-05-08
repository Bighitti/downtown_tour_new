package it.unicam.ids.dciotti.downtowntour.model;

public class TouristAuth extends Tourist {
    private final User user;

    public TouristAuth(Coordinates lastCoords, User user) {
        super(lastCoords);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}