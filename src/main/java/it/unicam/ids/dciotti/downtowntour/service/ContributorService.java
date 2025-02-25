package it.unicam.ids.dciotti.downtowntour.service;

import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.exception.*;

import java.util.List;

public interface ContributorService {
    ContributorDTO createContributor(ContributorDTO contributorDTO) throws UserDataRequiredException;

    ContributorDTO retrieveContributor(Integer contributorId);

    List<ContributorDTO> retrieveAllContributors();

    ContributorDTO authorizeContributorByCurator(Integer contributorId, LoginDTO loginDTO) throws ContributorNotFoundException, CuratorNotFoundException, CuratorRequiresNoAuthorizationException;

    void deleteContributor(Integer contributorId) throws ContentExistsException, ContributorNotFoundException;
}
