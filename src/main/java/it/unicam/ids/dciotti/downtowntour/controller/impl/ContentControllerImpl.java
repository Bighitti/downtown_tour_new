package it.unicam.ids.dciotti.downtowntour.controller.impl;

import it.unicam.ids.dciotti.downtowntour.controller.ContentController;
import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentControllerImpl implements ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentControllerImpl(ContentService contentService) {
        this.contentService = contentService;
    }

    @Override
    public ResponseEntity<Void> createContent(ContentDTO contentDTO) {
        contentService.createContent(contentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
