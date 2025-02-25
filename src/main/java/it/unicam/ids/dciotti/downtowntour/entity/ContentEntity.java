package it.unicam.ids.dciotti.downtowntour.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(
        name = "contents",
        schema = "downtown_tour"
)
public class ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "text", length = 512, nullable = false)
    private String text;
    @Column(name = "challenge_id", nullable = true)
    private Integer idChallenge;
    @Column(name = "publication", nullable = false)
    private Timestamp publication;
    @Column(name = "contributor_id", nullable = false)
    private Integer idContributor;
    @Column(name = "curator_id", nullable = true)
    private Integer idCurator;

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

    public Integer getIdChallenge() {
        return idChallenge;
    }

    public void setIdChallenge(Integer idChallenge) {
        this.idChallenge = idChallenge;
    }

    public Timestamp getPublication() {
        return publication;
    }

    public void setPublication(Timestamp publication) {
        this.publication = publication;
    }

    public Integer getIdContributor() {
        return idContributor;
    }

    public void setIdContributor(Integer idContributor) {
        this.idContributor = idContributor;
    }

    public Integer getIdCurator() {
        return idCurator;
    }

    public void setIdCurator(Integer idCurator) {
        this.idCurator = idCurator;
    }
}
