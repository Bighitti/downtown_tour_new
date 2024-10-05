package it.unicam.ids.dciotti.downtowntour.service;

import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.exception.ContributorNotFoundException;
import it.unicam.ids.dciotti.downtowntour.exception.InsertFailException;

import java.util.List;

public interface ContentService {

    ContentDTO retrieveContent(Integer id);

    void deleteContent(Integer contentDTO);
    ContentDTO createContent(ContentDTO contentDTO) throws ContributorNotFoundException, InsertFailException;

    List<ContentDTO> retrieveAllContents();
}


