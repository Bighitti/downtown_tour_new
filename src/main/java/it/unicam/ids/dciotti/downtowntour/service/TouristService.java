package it.unicam.ids.dciotti.downtowntour.service;

import it.unicam.ids.dciotti.downtowntour.dto.*;
import it.unicam.ids.dciotti.downtowntour.exception.TouristNotFoundException;
import it.unicam.ids.dciotti.downtowntour.exception.UserDataRequiredException;
import it.unicam.ids.dciotti.downtowntour.exception.UserNotFoundException;

import java.util.UUID;

public interface TouristService {
    TouristDTO createTourist(CoordinatesDTO coordinatesDTO);

    TouristDTO retrieveTourist(UUID uuid);

    TouristDTO loginTourist(LoginDTO loginDTO);

    TouristDTO updateCoordinates(UUID uuid, CoordinatesDTO coordinatesDTO) throws TouristNotFoundException;

    TouristDTO upgradeTourist(UUID uuid, UserDTO userDTO) throws TouristNotFoundException, UserDataRequiredException;

    void deleteTourist(LoginDTO loginDTO) throws TouristNotFoundException, UserNotFoundException;
}
