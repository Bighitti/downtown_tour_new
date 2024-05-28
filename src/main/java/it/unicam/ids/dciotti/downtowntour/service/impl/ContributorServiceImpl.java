package it.unicam.ids.dciotti.downtowntour.service.impl;

import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import it.unicam.ids.dciotti.downtowntour.service.ContributorService;
import org.springframework.stereotype.Service;

@Service
public class ContributorServiceImpl implements ContributorService {
    @Override
    public void createContributor(ContributorDTO contributorDTO) {
        System.out.println(contributorDTO);
    }
}
