package it.unicam.ids.dciotti.downtowntour.mapper;

import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
import it.unicam.ids.dciotti.downtowntour.model.Contributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributorMapper implements DefaultMapper<ContributorDTO, Contributor, ContributorEntity> {
    private final CuratorMapper curatorMapper;

    @Autowired
    public ContributorMapper(CuratorMapper curatorMapper) {
        this.curatorMapper = curatorMapper;
    }

    @Override
    public ContributorDTO dto(Contributor model) {
        if (model == null) return null;
        ContributorDTO contributorDTO = new ContributorDTO();
        //TODO: fare tutti i set necessari - guardare esempio riga 23-29
        try {
            contributorDTO.setAuthorizedBy(model.getAuthorizedBy().getFiscalCode());
        } catch (NullPointerException ignore) {}
        return contributorDTO;
    }

    @Override
    public Contributor modelFromDto(ContributorDTO dto) {
        if (dto == null) return null;
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

    @Override
    public Contributor modelFromEntity(ContributorEntity entity) {
        if (entity == null) return null;
        if (entity.getCurator()) return curatorMapper.modelFromEntity(entity);
        Contributor contributor = new Contributor();
        //TODO: fare tutti i set necessari - guardare esempio riga 23-29
        contributor.setAuthorizedBy(curatorMapper.modelFromEntity(entity.getAuthorizedBy()));
        return contributor;
    }

    @Override
    public ContributorEntity entity(Contributor model) {
        if (model == null) return null;
        ContributorEntity contributorEntity = new ContributorEntity();
        //TODO: fare tutti i set necessari - guardare esempio riga 23-29
        contributorEntity.setAuthorizedBy(curatorMapper.entity(model.getAuthorizedBy()));
        return contributorEntity;
    }
}
