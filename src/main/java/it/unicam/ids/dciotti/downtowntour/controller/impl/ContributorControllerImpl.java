package it.unicam.ids.dciotti.downtowntour.controller.impl;

import it.unicam.ids.dciotti.downtowntour.controller.ContributorController;
import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import it.unicam.ids.dciotti.downtowntour.model.User;
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
    @PostMapping
    public ResponseEntity<ContributorDTO> createContributor(ContributorDTO contributorDTO) {
        ContributorDTO dtoSaved = contributorService.createContributor(contributorDTO);
        return new ResponseEntity<>(dtoSaved, HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ContributorDTO> retrieveContributor(Integer id) {
        ContributorDTO dto = contributorService.retrieveContributor(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<ContributorDTO> updateContributor(Integer id, @RequestBody ContributorDTO contributorDTO) {
        ContributorDTO dto = contributorService.updateContributor(id, contributorDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/all")
    public ResponseEntity<List<ContributorDTO>> retrieveAllContributors() {
        List<ContributorDTO> dtos = contributorService.retrieveAllContributors();
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteContributor(Integer id) {
        contributorService.deleteContributor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
