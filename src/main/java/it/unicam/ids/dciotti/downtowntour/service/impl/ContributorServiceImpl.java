package it.unicam.ids.dciotti.downtowntour.service.impl;

import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
import it.unicam.ids.dciotti.downtowntour.mapper.ContributorMapper;
import it.unicam.ids.dciotti.downtowntour.model.Contributor;
import it.unicam.ids.dciotti.downtowntour.repository.ContributorRepository;
import it.unicam.ids.dciotti.downtowntour.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContributorServiceImpl implements ContributorService {
    private final ContributorMapper contributorMapper;
    private final ContributorRepository contributorRepository;

    @Autowired
    public ContributorServiceImpl(
            ContributorMapper contributorMapper,
            ContributorRepository contributorRepository) {
        this.contributorMapper = contributorMapper;
        this.contributorRepository = contributorRepository;
    }

    @Override
    public ContributorDTO createContributor(ContributorDTO contributorDTO) {
        Contributor contributor = contributorMapper.modelFromDto(contributorDTO);
        ContributorEntity contributorEntity = contributorMapper.entity(contributor);
        contributorRepository.save(contributorEntity);
        return contributorMapper.dto(contributorMapper.modelFromEntity(contributorEntity));
    }

    @Override
    public ContributorDTO retrieveContributor(Integer id) {
        Optional<ContributorEntity> contributorEntity = contributorRepository.findById(id);
        if (contributorEntity.isPresent()) {
            return contributorMapper.dto(contributorMapper.modelFromEntity(contributorEntity.get()));
        }
        return null;
    }

    @Override
    public List<ContributorDTO> retrieveAllContributors() {
        List<ContributorEntity> contributorEntities = contributorRepository.findAll();
        return contributorMapper.dto(contributorEntities);
    }

    @Override
    public void deleteContributor(Integer id) {
        contributorRepository.deleteById(id);
    }

    @Override
    public ContributorDTO updateContributor(Integer id, ContributorDTO contributorDTO){
       // Optional<ContributorEntity> contributorEntity = contributorRepository.findById(id);
        Optional<ContributorEntity> optionalContributorEntity = contributorRepository.findById(id);
        if (optionalContributorEntity.isPresent()) {
            ContributorEntity contributorEntity = optionalContributorEntity.get();
            // Aggiorna i campi dell'entità con i valori del DTO
            contributorEntity.setFirstname(contributorDTO.getFirstname());//setName(contributorDTO.getName());
            contributorEntity.setLastname(contributorEntity.getLastname());//se(contributorDTO.getRole());
            // contributorEntity
            // Salva l'entità aggiornata nel repository
            ContributorEntity updatedEntity = contributorRepository.save(contributorEntity);
            // Converte l'entità aggiornata in DTO e lo restituisce
            return contributorMapper.dto(contributorMapper.modelFromEntity(updatedEntity));
        }
        return null;
    }
}