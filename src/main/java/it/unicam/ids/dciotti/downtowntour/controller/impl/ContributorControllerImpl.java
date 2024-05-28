package it.unicam.ids.dciotti.downtowntour.controller.impl;

import it.unicam.ids.dciotti.downtowntour.controller.ContributorController;
import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import it.unicam.ids.dciotti.downtowntour.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContributorControllerImpl implements ContributorController {
    private final ContributorService contributorService;

    @Autowired
    public ContributorControllerImpl(ContributorService contributorService) {
        this.contributorService = contributorService;
    }

    @Override
    public ResponseEntity<Void> createContributor(ContributorDTO contributorDTO) {
        contributorService.createContributor(contributorDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
