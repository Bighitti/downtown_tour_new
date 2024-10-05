package it.unicam.ids.dciotti.downtowntour.service;

import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;

import java.util.List;

public interface ContributorService {
    ContributorDTO createContributor(ContributorDTO contributorDTO);

    ContributorDTO retrieveContributor(Integer id);

    List<ContributorDTO> retrieveAllContributors();

    void deleteContributor(Integer id);

    ContributorDTO updateContributor(Integer id, ContributorDTO contributorDTO);
}
