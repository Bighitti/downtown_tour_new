package it.unicam.ids.dciotti.downtowntour.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(
        name = "content",
        schema = "downtown_tour"
)
public class ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "text", length = 512, nullable = false)
    private String text;
    @Column(name = "publication", nullable = false)
    private Timestamp publication;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contributor", nullable = false)
    private ContributorEntity contributor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorized_by", nullable = true)
    private ContributorEntity authorizedBy;

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

    public Timestamp getPublication() {
        return publication;
    }

    public void setPublication(Timestamp publication) {
        this.publication = publication;
    }

    public ContributorEntity getContributor() {
        return contributor;
    }

    public void setContributor(ContributorEntity contributor) {
        this.contributor = contributor;
    }

    public ContributorEntity getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(ContributorEntity authorizedBy) {
        this.authorizedBy = authorizedBy;
    }
}
