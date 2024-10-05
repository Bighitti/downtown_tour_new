package it.unicam.ids.dciotti.downtowntour.service.impl;

import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.entity.ContentEntity;
import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
import it.unicam.ids.dciotti.downtowntour.exception.ContributorNotFoundException;
import it.unicam.ids.dciotti.downtowntour.exception.InsertFailException;
import it.unicam.ids.dciotti.downtowntour.mapper.ContentMapper;
import it.unicam.ids.dciotti.downtowntour.repository.ContentRepository;
import it.unicam.ids.dciotti.downtowntour.repository.ContributorRepository;
import it.unicam.ids.dciotti.downtowntour.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService {
    private final ContentMapper contentMapper;
    private final ContentRepository contentRepository;
    private final ContributorRepository contributorRepository;

    @Autowired
    public ContentServiceImpl(
            ContentMapper contentMapper,
            ContentRepository contentRepository,
            ContributorRepository contributorRepository) {
        this.contentMapper = contentMapper;
        this.contentRepository = contentRepository;
        this.contributorRepository = contributorRepository;
    }


    @Override
    public ContentDTO retrieveContent(Integer id) {
        Optional<ContributorEntity> dto = contributorRepository.findById(id);
        return null;
    }

    public List<ContentDTO> retrieveAllContents() {
        List<ContentEntity> contentEntities = contentRepository.findAll();
        return contentMapper.dto(contentMapper.modelFromEntity(contentEntities));
    }

    @Override
    public void deleteContent(Integer contentDTO) {
        contributorRepository.deleteById(contentDTO);
    }

    @Override
    public ContentDTO createContent(ContentDTO contentDTO) throws ContributorNotFoundException, InsertFailException {
        ContributorEntity contributorEntity;
        try {
            Optional<ContributorEntity> optContributor = contributorRepository.findById(contentDTO.getContributorId());
            contributorEntity = optContributor.get();
        } catch (IllegalArgumentException | NoSuchElementException ex) {
            throw new ContributorNotFoundException(ex);
        }
        contentDTO.setId(null);
        contentDTO.setPublication(new Date());
        ContentEntity contentEntity = contentMapper.entity(contentDTO);
        contentEntity.setContributor(contributorEntity);
        contentEntity.setAuthorizedBy(contributorEntity.getAuthorizedBy());
        try {
            contentRepository.save(contentEntity);
        } catch (Exception ex) {
            throw new InsertFailException(ex);
        }
        return contentMapper.dto(contentEntity);
    }

}
