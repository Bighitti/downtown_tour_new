package it.unicam.ids.dciotti.model;

import java.util.Date;

public class Content {
    private Date publication;
    private String text;
    private Contributor contributor;
    private Curator authorized;

    public Content(Contributor contributor) {
        this.contributor = contributor;
        this.authorized = contributor.getAuthorizedBy();
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
