package it.unicam.ids.dciotti.downtowntour.service;

import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.exception.*;

import java.util.List;

public interface ContentService {
    ContentDTO createContent(Integer contributorId, ContentDTO contentDTO) throws ContributorNotFoundException;

    ContentDTO retrieveContent(Integer contentId);

    List<ContentDTO> retrieveContentUnauthorized();

    List<ContentDTO> retrieveContentByContributor(Integer contributorId);

    List<ContentDTO> retrieveContentAuthorizedByCurator(Integer curatorId);

    List<ContentDTO> retrieveReportedContent();

    List<ContentDTO> retrieveAllContents();

    void authorize(Integer contentId, LoginDTO curatorLoginDTO) throws ContentNotFoundException, CuratorNotFoundException;

    void report(Integer contentId, LoginDTO loginDTO) throws ContentNotFoundException, UserNotFoundException;

    void deleteContent(Integer contentDTO);
}
