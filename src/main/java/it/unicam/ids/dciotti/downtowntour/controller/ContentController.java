package it.unicam.ids.dciotti.downtowntour.controller;

import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/content")
public interface ContentController {
    //TODO: create
    @PostMapping
    ResponseEntity<Void> createContent(
            @RequestBody ContentDTO contentDTO);
    //TODO: read/retrieve
    //TODO: update
    //TODO: delete
}
