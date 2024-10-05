package it.unicam.ids.dciotti.downtowntour.controller.impl;

import it.unicam.ids.dciotti.downtowntour.controller.ContentController;
import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
import it.unicam.ids.dciotti.downtowntour.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import it.unicam.ids.dciotti.downtowntour.repository.ContributorRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ContentControllerImpl implements ContentController {
    private final ContentService contentService;
    private final ContributorRepository repository;

    @Autowired
    public ContentControllerImpl(
            ContentService contentService,
            ContributorRepository repository) {
        this.contentService = contentService;
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Void> createContent(ContentDTO contentDTO) {
        contentService.createContent(contentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ContentDTO> retrieveContent(Integer id) {
        ContentDTO dto = contentService.retrieveContent(id);
        return new ResponseEntity<>(dto , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContentDTO> retrieveContentByCreator(Integer creatorId) {
        return null;
    }

    @Override
    public ResponseEntity<ContentDTO> retrieveContentByCurator(Integer curatorId) {
        return null;
    }

    @Override
    public ResponseEntity<List<ContentDTO>> retrieveAllContents() {
        List<ContentDTO> dtos = contentService.retrieveAllContents();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContentDTO> updateContent(Integer id, ContributorDTO contributorDTO) {
        return null;
    }


    @Override
    public boolean deleteContent(Integer id) {
        this.contentService.deleteContent(id);

        Optional<ContributorEntity> contentFromMysql = this.repository.findById(id);
        boolean result;
        if(contentFromMysql.isPresent()) {
            result = false;
        }
        else result = true;
        return result;
    }
}