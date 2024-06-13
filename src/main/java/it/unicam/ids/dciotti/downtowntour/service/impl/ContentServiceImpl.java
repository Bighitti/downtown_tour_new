package it.unicam.ids.dciotti.downtowntour.service.impl;

import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.mapper.ContentMapper;
import it.unicam.ids.dciotti.downtowntour.model.Content;
import it.unicam.ids.dciotti.downtowntour.model.Contributor;
import it.unicam.ids.dciotti.downtowntour.repository.ContributorRepository;
import it.unicam.ids.dciotti.downtowntour.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {
    private final ContentMapper contentMapper;
    private final ContributorRepository contributorRepository;

    @Autowired
    public ContentServiceImpl(
            ContentMapper contentMapper,
            ContributorRepository contributorRepository) {
        this.contentMapper = contentMapper;
        this.contributorRepository = contributorRepository;
    }

    @Override
    public void createContent(ContentDTO contentDTO) {
        Contributor contributor = null; //TODO
        Content content = contentMapper.model(contentDTO, contributor, null, null);
    }
}
