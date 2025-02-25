package it.unicam.ids.dciotti.downtowntour.dto;

import java.util.UUID;

public class TouristDTO {
    private UUID cookie;
    private CoordinatesDTO actualCoordinates;
    private UserDTO user;

    public UUID getCookie() {
        return cookie;
    }

    public void setCookie(UUID cookie) {
        this.cookie = cookie;
    }

    public CoordinatesDTO getActualCoordinates() {
        return actualCoordinates;
    }

    public void setActualCoordinates(CoordinatesDTO actualCoordinates) {
        this.actualCoordinates = actualCoordinates;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}