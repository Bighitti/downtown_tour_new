package it.unicam.ids.dciotti.downtowntour.service;

import it.unicam.ids.dciotti.downtowntour.dto.ChallengeDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.dto.UserDTO;
import it.unicam.ids.dciotti.downtowntour.exception.*;

public interface AdminService {
    void createUser(UserDTO userDTO) throws AdminCreationFailException;

    UserDTO login(LoginDTO loginDTO);

    void reportSolve(Integer reportId, LoginDTO loginDTO) throws AdminPrivilegeException, ReportNotFoundException, UserNotFoundException;

    ChallengeDTO challengeStart(ChallengeDTO challengeDTO) throws AdminPrivilegeException, UserNotFoundException;

    void promoteAdmin(Integer userId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException,  UserNotFoundException;

    void promoteEntertainer(Integer userId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException, UserNotFoundException;

    void promoteModerator(Integer userId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException, UserNotFoundException;

    void promoteFromContributorToCurator(Integer contributorId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException, UserNotFoundException;

    void demoteFromCuratorToContributor(Integer contributorId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException, UserNotFoundException;

    void unauthorizeContent(Integer contentId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException, ContentNotFoundException;

    void deleteAdmin(LoginDTO loginDTO) throws AdminCancellationFailException, UserNotFoundException;
}
