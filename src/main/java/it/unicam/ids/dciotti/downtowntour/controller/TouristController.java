package it.unicam.ids.dciotti.downtowntour.controller;

import it.unicam.ids.dciotti.downtowntour.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(path = "/tourist")
public interface TouristController {
    @PostMapping(path = "/create")
    ResponseEntity<TouristDTO> createTourist(
            @RequestBody CoordinatesDTO coordinatesDTO);

    @GetMapping(path = "/{uuid}")
    ResponseEntity<TouristDTO> retrieveTourist(
            @PathVariable UUID uuid);

    @PostMapping
    ResponseEntity<TouristDTO> loginTourist(
            @RequestBody LoginDTO loginDTO);

    @PatchMapping(path = "/coordinates/{uuid}")
    ResponseEntity<TouristDTO> updateCoordinates(
            @PathVariable UUID uuid,
            @RequestBody CoordinatesDTO coordinatesDTO);

    @PatchMapping(path = "/upgrade/{uuid}")
    ResponseEntity<TouristDTO> upgradeTourist(
            @PathVariable UUID uuid,
            @RequestBody UserDTO userDTO);

    @DeleteMapping
    ResponseEntity<Void> deleteTourist(
            @RequestBody LoginDTO loginDTO);
}
