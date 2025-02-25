package it.unicam.ids.dciotti.downtowntour.controller.impl;

import it.unicam.ids.dciotti.downtowntour.controller.TouristController;
import it.unicam.ids.dciotti.downtowntour.dto.*;
import it.unicam.ids.dciotti.downtowntour.exception.TouristNotFoundException;
import it.unicam.ids.dciotti.downtowntour.exception.UserDataRequiredException;
import it.unicam.ids.dciotti.downtowntour.exception.UserNotFoundException;
import it.unicam.ids.dciotti.downtowntour.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TouristControllerimpl implements TouristController {
    private final TouristService touristService;

    @Autowired
    public TouristControllerimpl(TouristService touristService) {
        this.touristService = touristService;
    }

    @Override
    public ResponseEntity<TouristDTO> createTourist(CoordinatesDTO coordinatesDTO) {
        try {
            TouristDTO dto = touristService.createTourist(coordinatesDTO);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<TouristDTO> retrieveTourist(UUID uuid) {
        TouristDTO dto = touristService.retrieveTourist(uuid);
        return new ResponseEntity<>(dto, dto == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TouristDTO> loginTourist(LoginDTO loginDTO) {
        TouristDTO dto = touristService.loginTourist(loginDTO);
        return new ResponseEntity<>(dto, dto == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TouristDTO> updateCoordinates(UUID uuid, CoordinatesDTO coordinatesDTO) {
        try {
            TouristDTO dto = touristService.updateCoordinates(uuid, coordinatesDTO);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (TouristNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<TouristDTO> upgradeTourist(UUID uuid, UserDTO userDTO) {
        try {
            TouristDTO dto = touristService.upgradeTourist(uuid, userDTO);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (TouristNotFoundException | UserDataRequiredException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deleteTourist(LoginDTO loginDTO) {
        try {
            touristService.deleteTourist(loginDTO);
        } catch (TouristNotFoundException | UserNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}