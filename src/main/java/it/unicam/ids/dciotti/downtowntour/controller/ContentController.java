package it.unicam.ids.dciotti.downtowntour.controller;

import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/content")
public interface ContentController {
    @PostMapping(path = "/contributor/{contributorId}")
    ResponseEntity<ContentDTO> createContent(
            @PathVariable Integer contributorId,
            @RequestBody ContentDTO contentDTO);

    @GetMapping(path = "/id/{contentId}")
    ResponseEntity<ContentDTO> retrieveContent(
            @PathVariable Integer contentId);

    @GetMapping(path = "/unauth")
    ResponseEntity<List<ContentDTO>> retrieveContentUnauthorized();

    @GetMapping(path = "/createdby/{contributorId}")
    ResponseEntity<List<ContentDTO>> retrieveContentByContributor(
            @PathVariable Integer contributorId);

    @GetMapping(path = "/authorizedby/{curatorId}")
    ResponseEntity<List<ContentDTO>> retrieveContentAuthorizedByCurator(
            @PathVariable Integer curatorId);

    @GetMapping(path = "/reported")
    ResponseEntity<List<ContentDTO>> retrieveReportedContent();

    @GetMapping(path = "/all")
    ResponseEntity<List<ContentDTO>> retrieveAllContents();

    @PatchMapping(path = "/authorize/{contentId}")
    ResponseEntity<Void> authorize(
            @PathVariable Integer contentId,
            @RequestBody LoginDTO curatorLoginDTO);

    @PatchMapping(path = "/report/{contentId}")
    ResponseEntity<Void> report(
            @PathVariable Integer contentId,
            @RequestBody LoginDTO loginDTO);

    @DeleteMapping(path = "/{contentId}")
    boolean deleteContent(
            @PathVariable Integer contentId);
}
