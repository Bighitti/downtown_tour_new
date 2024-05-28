package it.unicam.ids.dciotti.downtowntour.mapper;

import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import it.unicam.ids.dciotti.downtowntour.model.Contributor;
import org.springframework.stereotype.Service;

@Service
public class ContributorMapper {
    public Contributor model(ContributorDTO dto) {
        Contributor contributor = new Contributor();
        contributor.setFirstname(dto.getFirstname());
        contributor.setLastname(dto.getLastname());
        contributor.setBirthday(dto.getBirthday());
        contributor.setEmail(dto.getEmail());
        contributor.setPhone(dto.getPhone());
        contributor.setAddress(dto.getAddress());
        contributor.setFiscalCode(dto.getFiscalCode());
        return contributor;
    }
}
