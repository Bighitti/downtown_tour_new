package it.unicam.ids.dciotti.downtowntour.service;

import it.unicam.ids.dciotti.downtowntour.dto.ChallengeDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.exception.AdminPrivilegeException;
import it.unicam.ids.dciotti.downtowntour.exception.ChallengeNotFoundException;
import it.unicam.ids.dciotti.downtowntour.exception.UserNotFoundException;

import java.util.List;

public interface ChallengeService {
    ChallengeDTO retrieveChallenge(Integer challengeId);

    List<ChallengeDTO> retrieveAllChallenges();

    Boolean subscribeToChallenge(Integer challengeId, LoginDTO loginDTO);

    void deleteChallenge(Integer challengeId, LoginDTO loginDTO) throws AdminPrivilegeException, ChallengeNotFoundException, UserNotFoundException;
}
