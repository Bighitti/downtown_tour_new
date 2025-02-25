package it.unicam.ids.dciotti.downtowntour.controller.impl;

import it.unicam.ids.dciotti.downtowntour.controller.AdminController;
import it.unicam.ids.dciotti.downtowntour.dto.*;
import it.unicam.ids.dciotti.downtowntour.exception.*;
import it.unicam.ids.dciotti.downtowntour.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminControllerImpl implements AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminControllerImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public ResponseEntity<Void> createUser(UserDTO userDTO) {
        try {
            adminService.createUser(userDTO);
        } catch (AdminCreationFailException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDTO> login(LoginDTO loginDTO) {
        UserDTO dto = adminService.login(loginDTO);
        return new ResponseEntity<>(dto, dto == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> reportSolve(Integer reportId, LoginDTO loginDTO) {
        try {
            adminService.reportSolve(reportId, loginDTO);
        } catch (AdminPrivilegeException | ReportNotFoundException | UserNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ChallengeDTO> challengeStart(ChallengeDTO challengeDTO) {
        try {
            ChallengeDTO dto = adminService.challengeStart(challengeDTO);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (AdminPrivilegeException | UserNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> promoteAdmin(Integer userId, LoginDTO loginDTO) {
        try {
            adminService.promoteAdmin(userId, loginDTO);
        } catch (AdminNotFoundException | AdminPrivilegeException | UserNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> promoteEntertainer(Integer userId, LoginDTO loginDTO) {
        try {
            adminService.promoteEntertainer(userId, loginDTO);
        } catch (AdminNotFoundException | AdminPrivilegeException | UserNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> promoteModerator(Integer userId, LoginDTO loginDTO) {
        try {
            adminService.promoteModerator(userId, loginDTO);
        } catch (AdminNotFoundException | AdminPrivilegeException | UserNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> promoteFromContributorToCurator(Integer contributorId, LoginDTO loginDTO) {
        try {
            adminService.promoteFromContributorToCurator(contributorId, loginDTO);
        } catch (AdminNotFoundException | AdminPrivilegeException | UserNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> demoteFromCuratorToContributor(Integer contributorId, LoginDTO loginDTO) {
        try {
            adminService.demoteFromCuratorToContributor(contributorId, loginDTO);
        } catch (AdminNotFoundException | AdminPrivilegeException | UserNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> unauthorizeContent(Integer contentId, LoginDTO loginDTO) {
        try {
            adminService.unauthorizeContent(contentId, loginDTO);
        } catch (AdminNotFoundException | AdminPrivilegeException | ContentNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAdmin(LoginDTO loginDTO) {
        try {
            adminService.deleteAdmin(loginDTO);
        } catch (AdminCancellationFailException | UserNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
