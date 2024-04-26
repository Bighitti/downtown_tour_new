package it.unicam.ids.dciotti.model;

import java.util.ArrayList;
import java.util.List;

public class Contributor extends User {
    private Curator authorizedBy;
    private List<Content> contents = new ArrayList<>();

    public Curator getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(Curator authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
