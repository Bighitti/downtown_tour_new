package it.unicam.ids.dciotti.downtowntour.model;

import java.util.Date;
import java.util.UUID;

public class Tourist {
    private UUID cookie = UUID.randomUUID();
    private Date firstInteraction = new Date();
    private Date lastInteraction = new Date(firstInteraction.getTime());
    private Coordinates lastCoords;

    public Tourist(Coordinates lastCoords) {
        this.lastCoords = lastCoords;
    }

    public void report(Content content) {
        new Report(this, content);
    }

    public UUID getCookie() {
        return cookie;
    }

    public Date getFirstInteraction() {
        return firstInteraction;
    }

    public Date getLastInteraction() {
        return lastInteraction;
    }

    public void setLastInteraction(Date lastInteraction) {
        this.lastInteraction = lastInteraction;
    }

    public Coordinates getLastCoords() {
        return lastCoords;
    }

    public void setLastCoords(Coordinates lastCoords) {
        this.lastCoords = lastCoords;
    }
}