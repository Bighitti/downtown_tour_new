package it.unicam.ids.dciotti.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Content {
    private String text;
    private final Date publication = new Date();
    private final Contributor contributor;
    private Curator authorized;
    private final Challenge challenge;
    private final List<Report> reportToBeSolved = new ArrayList<>();

    public Content(Contributor contributor) {
        this.contributor = contributor;
        contributor.getContents().add(this);
        this.authorized = contributor.getAuthorizedBy();
        this.challenge = null;
    }

    public Content(Contributor contributor, Challenge challenge) {
        this.contributor = contributor;
        this.authorized = contributor.getAuthorizedBy();
        this.challenge = challenge;
        if (challenge != null) {
            challenge.getContents().add(this);
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPublication() {
        return publication;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public Curator getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Curator authorized) {
        this.authorized = authorized;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public List<Report> getReportToBeSolved() {
        return reportToBeSolved;
    }
}
