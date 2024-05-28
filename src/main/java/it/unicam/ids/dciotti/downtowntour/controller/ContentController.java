package it.unicam.ids.dciotti.downtowntour.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/content")
public interface ContentController {
    //TODO: create
    @PostMapping
    ResponseEntity<Void> createContent();
    //TODO: read/retrieve
    //TODO: update
    //TODO: delete
}
