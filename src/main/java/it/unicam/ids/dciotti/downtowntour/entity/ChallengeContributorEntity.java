package it.unicam.ids.dciotti.downtowntour.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "challenge_contributor",
        schema = "downtown_tour"
)
public class ChallengeContributorEntity {
    @EmbeddedId
    private ChallengeContributorEntityPK id;

    public ChallengeContributorEntityPK getId() {
        return id;
    }

    public void setId(ChallengeContributorEntityPK id) {
        this.id = id;
    }
}
