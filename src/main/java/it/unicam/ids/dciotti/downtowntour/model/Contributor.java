package it.unicam.ids.dciotti.downtowntour.model;

import java.util.ArrayList;
import java.util.List;

public class Contributor extends User {
    private Curator authorizedBy;
    private final List<Content> contents = new ArrayList<>();

    public Content writeContent(String text) {
        return writeContent(text, null);
    }

    public Content writeContent(String text, Challenge challenge) {
        Content content = new Content(this, challenge);
        content.setText(text);
        return content;
    }

    public Curator getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(Curator authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public List<Content> getContents() {
        return contents;
    }
}
