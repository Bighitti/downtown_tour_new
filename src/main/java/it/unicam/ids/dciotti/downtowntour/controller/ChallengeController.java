package it.unicam.ids.dciotti.downtowntour.controller;

import it.unicam.ids.dciotti.downtowntour.dto.ChallengeDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/challenge")
public interface ChallengeController {
    @GetMapping(path = "/id/{challengeId}")
    ResponseEntity<ChallengeDTO> retrieveChallenge(
            @PathVariable Integer challengeId);

    @GetMapping(path = "/all")
    ResponseEntity<List<ChallengeDTO>> retrieveAllChallenges();

    @PostMapping(path = "/subscribe/{challengeId}")
    ResponseEntity<Boolean> subscribeToChallenge(
            @PathVariable Integer challengeId,
            @RequestBody LoginDTO loginDTO);

    @DeleteMapping(path = "/{challengeId}")
    ResponseEntity<Void> deleteChallenge(
            @PathVariable Integer challengeId,
            @RequestBody LoginDTO loginDTO);
}
