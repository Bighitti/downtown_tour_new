package it.unicam.ids.dciotti.model;

public class Contributor extends User {
    private Curator authorizedBy;

    public Curator getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(Curator authorizedBy) {
        this.authorizedBy = authorizedBy;
    }
}
