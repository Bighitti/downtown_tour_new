package it.unicam.ids.dciotti.downtowntour.controller;

import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/content")
public interface ContentController {

    @PostMapping
    ResponseEntity<ContentDTO> createContent(
            @RequestBody ContentDTO contentDTO);

    @GetMapping(path = "/{id}")
    ResponseEntity<ContentDTO> retrieveContent(
            @PathVariable(name = "id") Integer id);
    @GetMapping(path = "/{creatorId}")
    ResponseEntity<ContentDTO> retrieveContentByCreator(
            @PathVariable(name = "creatorId") Integer creatorId);
    @GetMapping(path = "/{curatorId}")
    ResponseEntity<ContentDTO> retrieveContentByCurator(
            @PathVariable(name = "curatorId") Integer curatorId);

    @GetMapping(path = "/all")
    ResponseEntity<List<ContentDTO>> retrieveAllContents();

    @PutMapping(path = "/{id}")
    ResponseEntity<ContentDTO> updateContent(
            @PathVariable(name = "id") Integer id,
            @RequestBody ContributorDTO contributorDTO);

    @DeleteMapping(path = "/delete/{id}")
    boolean deleteContent(
            @PathVariable(name = "id") Integer id);
}