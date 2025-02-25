package it.unicam.ids.dciotti.downtowntour.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(
        name = "challenges",
        schema = "downtown_tour",
        uniqueConstraints = {
                @UniqueConstraint(name = "REPORT_UNIQUE_UUID", columnNames = {"uuid"}),
                @UniqueConstraint(name = "REPORT_UNIQUE_USER", columnNames = {"user_id"})
        }
)
public class ChallengeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "c_name", length = 128, nullable = false)
    private String name;
    @Column(name = "description", length = 512, nullable = false)
    private String description;
    @Column(name = "max_players", nullable = false)
    private Integer maxPlayers;
    @Column(name = "c_start", nullable = false)
    private Timestamp start;
    @Column(name = "c_end", nullable = false)
    private Timestamp end;
    @Column(name = "c_open", nullable = false)
    private Boolean open = true;
    @Column(name = "entertainer_id", nullable = false)
    private Integer entertainerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Integer getEntertainerId() {
        return entertainerId;
    }

    public void setEntertainerId(Integer entertainerId) {
        this.entertainerId = entertainerId;
    }
}