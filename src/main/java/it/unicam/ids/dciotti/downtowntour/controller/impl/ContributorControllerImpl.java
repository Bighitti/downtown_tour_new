package it.unicam.ids.dciotti.downtowntour.controller.impl;

import it.unicam.ids.dciotti.downtowntour.controller.ContributorController;
import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.exception.*;
import it.unicam.ids.dciotti.downtowntour.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContributorControllerImpl implements ContributorController {
    private final ContributorService contributorService;

    @Autowired
    public ContributorControllerImpl(ContributorService contributorService) {
        this.contributorService = contributorService;
    }

    @Override
    public ResponseEntity<ContributorDTO> createContributor(ContributorDTO contributorDTO) {
        try {
            ContributorDTO dto = contributorService.createContributor(contributorDTO);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (UserDataRequiredException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ContributorDTO> retrieveContributor(Integer contributorId) {
        ContributorDTO dto = contributorService.retrieveContributor(contributorId);
        return new ResponseEntity<>(dto, dto == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContributorDTO>> retrieveAllContributors() {
        List<ContributorDTO> dtos = contributorService.retrieveAllContributors();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContributorDTO> authorizeContributorByCurator(Integer contributorId, LoginDTO loginDTO) {
        try {
            ContributorDTO dto = contributorService.authorizeContributorByCurator(contributorId, loginDTO);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (ContributorNotFoundException | CuratorNotFoundException | CuratorRequiresNoAuthorizationException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deleteContributor(Integer contributorId) {
        try {
            contributorService.deleteContributor(contributorId);
        } catch (ContentExistsException | ContributorNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
