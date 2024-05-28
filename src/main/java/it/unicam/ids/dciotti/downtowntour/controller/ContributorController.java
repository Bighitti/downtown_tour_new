package it.unicam.ids.dciotti.downtowntour.controller;

import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/contributor")
public interface ContributorController {
    //TODO: create
    @PostMapping
    ResponseEntity<Void> createContributor(
            @RequestBody(required = false) ContributorDTO contributorDTO);
    //TODO: read/retrieve
    //TODO: update
    //TODO: delete
}
