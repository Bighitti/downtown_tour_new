package it.unicam.ids.dciotti.downtowntour.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class ChallengeContributorEntityPK implements Serializable {
    @Serial
    private static final long serialVersionUID = 6758104619149202356L;

    @Column(name = "challenge_id")
    private Integer challengeId;

    @Column(name = "contributor_id")
    private Integer contributorId;

    public Integer getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Integer challengeId) {
        this.challengeId = challengeId;
    }

    public Integer getContributorId() {
        return contributorId;
    }

    public void setContributorId(Integer contributorId) {
        this.contributorId = contributorId;
    }
}
