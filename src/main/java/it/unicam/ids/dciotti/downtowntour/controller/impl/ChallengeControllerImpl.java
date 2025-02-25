package it.unicam.ids.dciotti.downtowntour.controller.impl;

import it.unicam.ids.dciotti.downtowntour.controller.ChallengeController;
import it.unicam.ids.dciotti.downtowntour.dto.ChallengeDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.exception.AdminPrivilegeException;
import it.unicam.ids.dciotti.downtowntour.exception.ChallengeNotFoundException;
import it.unicam.ids.dciotti.downtowntour.exception.UserNotFoundException;
import it.unicam.ids.dciotti.downtowntour.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChallengeControllerImpl implements ChallengeController {
    private final ChallengeService challengeService;

    @Autowired
    public ChallengeControllerImpl(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @Override
    public ResponseEntity<ChallengeDTO> retrieveChallenge(Integer challengeId) {
        ChallengeDTO dto = challengeService.retrieveChallenge(challengeId);
        return new ResponseEntity<>(dto, dto == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ChallengeDTO>> retrieveAllChallenges() {
        List<ChallengeDTO> dtos = challengeService.retrieveAllChallenges();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> subscribeToChallenge(Integer challengeId, LoginDTO loginDTO) {
        return new ResponseEntity<>(challengeService.subscribeToChallenge(challengeId, loginDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteChallenge(Integer challengeId, LoginDTO loginDTO) {
        try {
            challengeService.deleteChallenge(challengeId, loginDTO);
        } catch (AdminPrivilegeException | ChallengeNotFoundException | UserNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
