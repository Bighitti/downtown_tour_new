package it.unicam.ids.dciotti.model;

public class Curator extends Contributor {
    public Curator() {
        super.setAuthorizedBy(this);
    }

    @Override
    public void setAuthorizedBy(Curator authorizedBy) {
        throw new RuntimeException("Curators are authorized by them self");
    }
}