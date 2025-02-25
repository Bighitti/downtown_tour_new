package it.unicam.ids.dciotti.downtowntour.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContentDTO {
    private Integer id;
    private String text;
    private Integer challengeId;
    private Date publication = new Date();
    private List<Integer> reports = new ArrayList<>();

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

    public Integer getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Integer challengeId) {
        this.challengeId = challengeId;
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public List<Integer> getReports() {
        return reports;
    }
}
