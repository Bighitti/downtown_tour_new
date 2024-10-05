package it.unicam.ids.dciotti.downtowntour.service;

import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;

import java.util.List;

public interface ContentService {

    ContentDTO retrieveContent(Integer id);

    void deleteContent(Integer contentDTO);
    ContentDTO createContent(ContentDTO contentDTO);

    List<ContentDTO> retrieveAllContents();
}


