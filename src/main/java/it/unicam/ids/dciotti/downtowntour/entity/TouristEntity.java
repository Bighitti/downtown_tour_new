package it.unicam.ids.dciotti.downtowntour.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(
        name = "tourists",
        schema = "downtown_tour",
        uniqueConstraints = {
                @UniqueConstraint(name = "TOURIST_UNIQUE_UUID", columnNames = {"uuid"}),
                @UniqueConstraint(name = "TOURIST_UNIQUE_USER", columnNames = {"user_id"})
        }
)
public class TouristEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "uuid", length = 36, nullable = false)
    private String uuid;
    @Column(name = "last_coord_x", nullable = true)
    private Double lastCoordX;
    @Column(name = "last_coord_y", nullable = true)
    private Double lastCoordY;
    @Column(name = "user_id", nullable = true)
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Double getLastCoordX() {
        return lastCoordX;
    }

    public void setLastCoordX(Double lastCoordX) {
        this.lastCoordX = lastCoordX;
    }

    public Double getLastCoordY() {
        return lastCoordY;
    }

    public void setLastCoordY(Double lastCoordY) {
        this.lastCoordY = lastCoordY;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}