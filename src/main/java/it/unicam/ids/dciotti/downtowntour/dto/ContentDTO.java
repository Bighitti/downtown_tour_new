package it.unicam.ids.dciotti.downtowntour.dto;

import java.util.Date;

public class ContentDTO {
    private Integer id;
    private String text;
    private Date publication = new Date();
    private Integer contributorId;
    private Integer authorizedById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public Integer getContributorId() {
        return contributorId;
    }

    public void setContributorId(Integer contributorId) {
        this.contributorId = contributorId;
    }

    public Integer getAuthorizedById() {
        return authorizedById;
    }

    public void setAuthorizedById(Integer authorizedById) {
        this.authorizedById = authorizedById;
    }
}
