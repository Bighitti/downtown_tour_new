package it.unicam.ids.dciotti.downtowntour.controller.impl;

import it.unicam.ids.dciotti.downtowntour.controller.ContentController;
import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.exception.*;
import it.unicam.ids.dciotti.downtowntour.repository.ContentRepository;
import it.unicam.ids.dciotti.downtowntour.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContentControllerImpl implements ContentController {
    private final ContentService contentService;
    private final ContentRepository contentRepository;

    @Autowired
    public ContentControllerImpl(
            ContentService contentService,
            ContentRepository contentRepository) {
        this.contentService = contentService;
        this.contentRepository = contentRepository;
    }

    @Override
    public ResponseEntity<ContentDTO> createContent(Integer contributorId, ContentDTO contentDTO) {
        try {
            ContentDTO dto = contentService.createContent(contributorId, contentDTO);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (ContributorNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ContentDTO> retrieveContent(Integer contentId) {
        ContentDTO dto = contentService.retrieveContent(contentId);
        return new ResponseEntity<>(dto, dto == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContentDTO>> retrieveContentUnauthorized() {
        List<ContentDTO> dto = contentService.retrieveContentUnauthorized();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContentDTO>> retrieveContentByContributor(Integer contributorId) {
        List<ContentDTO> dto = contentService.retrieveContentByContributor(contributorId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContentDTO>> retrieveContentAuthorizedByCurator(Integer curatorId) {
        List<ContentDTO> dto = contentService.retrieveContentAuthorizedByCurator(curatorId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContentDTO>> retrieveReportedContent() {
        List<ContentDTO> dtos = contentService.retrieveReportedContent();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContentDTO>> retrieveAllContents() {
        List<ContentDTO> dtos = contentService.retrieveAllContents();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> authorize(Integer contentId, LoginDTO curatorLoginDTO) {
        try {
            contentService.authorize(contentId, curatorLoginDTO);
        } catch (ContentNotFoundException | CuratorNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> report(Integer contentId, LoginDTO loginDTO) {
        try {
            contentService.report(contentId, loginDTO);
        } catch (ContentNotFoundException | UserNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public boolean deleteContent(Integer contentId) {
        if (!contentRepository.existsById(contentId)) return false;
        try {
            contentService.deleteContent(contentId);
        } catch (Exception ignore) {
            return false;
        }
        return true;
    }
}
