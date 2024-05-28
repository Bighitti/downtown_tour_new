package it.unicam.ids.dciotti.downtowntour.service.impl;

import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import it.unicam.ids.dciotti.downtowntour.mapper.ContributorMapper;
import it.unicam.ids.dciotti.downtowntour.model.Contributor;
import it.unicam.ids.dciotti.downtowntour.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributorServiceImpl implements ContributorService {
    private final ContributorMapper contributorMapper;

    @Autowired
    public ContributorServiceImpl(ContributorMapper contributorMapper) {
        this.contributorMapper = contributorMapper;
    }

    @Override
    public void createContributor(ContributorDTO contributorDTO) {
        Contributor contributor = contributorMapper.model(contributorDTO);
        System.out.println(contributor);
    }
}
