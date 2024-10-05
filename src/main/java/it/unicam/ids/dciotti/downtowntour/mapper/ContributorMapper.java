package it.unicam.ids.dciotti.downtowntour.mapper;

import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import it.unicam.ids.dciotti.downtowntour.dto.CuratorDTO;
import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
import it.unicam.ids.dciotti.downtowntour.model.Contributor;
import it.unicam.ids.dciotti.downtowntour.model.Curator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContributorMapper {
    public ContributorDTO dto(Contributor model) {
        if (model == null) return null;
        ContributorDTO contributorDTO = new ContributorDTO();
        //TODO: fare tutti i set necessari - guardare esempio riga 23-29
        contributorDTO.setFirstname(model.getFirstname());
        contributorDTO.setLastname(model.getLastname());
        contributorDTO.setBirthday(model.getBirthday());
        contributorDTO.setEmail(model.getEmail());
        contributorDTO.setPhone(model.getPhone());
        contributorDTO.setAddress(model.getAddress());
        contributorDTO.setFiscalCode(model.getFiscalCode());
        try {
            contributorDTO.setAuthorizedBy((CuratorDTO) dto(model.getAuthorizedBy()));
        } catch (NullPointerException ignore) {}
        return contributorDTO;
    }

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

    public Contributor modelFromEntity(ContributorEntity entity) {
        if (entity == null) return null;
        Contributor contributor = entity.getAuthorizedBy() == entity ? new Curator() : new Contributor();
        //if (entity.getCurator()) return curatorMapper.modelFromEntity(entity);
        //Contributor contributor = new Contributor();
        contributor.setFirstname(entity.getFirstname());
        contributor.setLastname(entity.getLastname());
        contributor.setBirthday(entity.getBirthday());
        contributor.setEmail(entity.getEmail());
        contributor.setPhone(entity.getPhone());
        contributor.setAddress(entity.getAddress());
        contributor.setFiscalCode(entity.getFiscalCode());
        if (!(contributor instanceof Curator) && entity.getAuthorizedBy() != null) {
            contributor.setAuthorizedBy((Curator) modelFromEntity(entity.getAuthorizedBy()));
        }
        return contributor;
    }

    public ContributorEntity entity(Contributor model) {
        if (model == null) return null;
        ContributorEntity contributorEntity = new ContributorEntity();
        contributorEntity.setFirstname(model.getFirstname());
        contributorEntity.setLastname(model.getLastname());
        contributorEntity.setBirthday(new java.sql.Date(model.getBirthday().getTime()));
        contributorEntity.setEmail(model.getEmail());
        contributorEntity.setPhone(model.getPhone());
        contributorEntity.setAddress(model.getAddress());
        contributorEntity.setFiscalCode(model.getFiscalCode());
        if (!(model instanceof Curator)) {
            if (model.getAuthorizedBy() != null) {
                contributorEntity.setAuthorizedBy(entity(model.getAuthorizedBy()));
            }
        } else {
            contributorEntity.setAuthorizedBy(contributorEntity);
        }
        return contributorEntity;
    }

    public ContributorDTO dto(ContributorEntity entity) {
        ContributorDTO contributorDTO = dto(modelFromEntity(entity));
        contributorDTO.setId(entity.getId());
        return contributorDTO;
    }

    public List<ContributorDTO> dto(List<ContributorEntity> contributorEntities) {
        if(contributorEntities != null) {
            List<ContributorDTO> output = new ArrayList<>();
            for (int i = 0; i < contributorEntities.size(); i++) {
                output.add(dto(contributorEntities.get(i)));
            }
            return output;
        } else {
            return null;
        }
    }
}
