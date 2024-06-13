package it.unicam.ids.dciotti.downtowntour.dto;

import java.util.Date;

public class ContentDTO {
    private String text;
    private Date publication = new Date();
    private String contributorFiscalCode;
    private String authorizedByCuratorFiscalCode;

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

    public String getContributorFiscalCode() {
        return contributorFiscalCode;
    }

    public void setContributorFiscalCode(String contributorFiscalCode) {
        this.contributorFiscalCode = contributorFiscalCode;
    }

    public String getAuthorizedByCuratorFiscalCode() {
        return authorizedByCuratorFiscalCode;
    }

    public void setAuthorizedByCuratorFiscalCode(String authorizedByCuratorFiscalCode) {
        this.authorizedByCuratorFiscalCode = authorizedByCuratorFiscalCode;
    }
}
