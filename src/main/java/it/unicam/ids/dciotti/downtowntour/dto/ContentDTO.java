package it.unicam.ids.dciotti.downtowntour.dto;

import java.util.Date;

public class ContentDTO {
    private String text;
    private Date publication = new Date();
    private ContributorDTO contributor;
    private CuratorDTO authorizedBy;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public ContributorDTO getContributor() {
        return contributor;
    }

    public void setContributor(ContributorDTO contributor) {
        this.contributor = contributor;
    }

    public CuratorDTO getAuthorizedByCuratorFiscalCode() {
        return authorizedBy;
    }

    public void setAuthorizedBy(CuratorDTO authorizedBy) {
        this.authorizedBy = authorizedBy;
    }
}
