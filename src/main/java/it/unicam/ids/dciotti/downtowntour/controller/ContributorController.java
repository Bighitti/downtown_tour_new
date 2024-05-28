package it.unicam.ids.dciotti.downtowntour.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/contributor")
public interface ContributorController {
    //TODO: create
    @PostMapping
    ResponseEntity<Void> createContributor();
    //TODO: read/retrieve
    //TODO: update
    //TODO: delete
}
