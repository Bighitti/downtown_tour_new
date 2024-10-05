//package it.unicam.ids.dciotti.downtowntour.mapper;
//
//import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
//import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
//import it.unicam.ids.dciotti.downtowntour.model.Curator;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CuratorMapper implements DefaultMapper<ContributorDTO, Curator, ContributorEntity> {
//
//    @Override
//    public ContributorDTO dto(Curator model) {
//        if (model == null) return null;
//        ContributorDTO contributorDTO = new ContributorDTO();
//        //TODO: copiare da ContribuotMapper una volta finito
//        //TODO: incluso authorizedBy
//        contributorDTO.setFirstname(model.getFirstname());
//        contributorDTO.setLastname(model.getLastname());
//        contributorDTO.setBirthday(model.getBirthday());
//        contributorDTO.setEmail(model.getEmail());
//        contributorDTO.setPhone(model.getPhone());
//        contributorDTO.setAddress(model.getAddress());
//        contributorDTO.setFiscalCode(model.getFiscalCode());
//        try{
//            contributorDTO.setAuthorizedBy(model.getAuthorizedBy().getFiscalCode());
//        } catch (NullPointerException ignore) {}
//        return contributorDTO;
//    }
//
//    @Override
//    public Curator modelFromDto(ContributorDTO dto) {
//        if (dto == null) return null;
//        Curator curator = new Curator();
//        curator.setFirstname(dto.getFirstname());
//        curator.setLastname(dto.getLastname());
//        curator.setBirthday(dto.getBirthday());
//        curator.setEmail(dto.getEmail());
//        curator.setPhone(dto.getPhone());
//        curator.setAddress(dto.getAddress());
//        curator.setFiscalCode(dto.getFiscalCode());
//        return curator;
//    }
//
//    @Override
//    public Curator modelFromEntity(ContributorEntity entity) {
//        if (entity == null || !entity.getCurator()) return null;
//        Curator Curator = new Curator();
//        //TODO: copiare da ContribuotMapper una volta finito tranne setAuthorizedBy, va ignorato
//        Curator.setFirstname(entity.getFirstname());
//        Curator.setLastname(entity.getLastname());
//        Curator.setBirthday(entity.getBirthday());
//        Curator.setEmail(entity.getEmail());
//        Curator.setPhone(entity.getPhone());
//        Curator.setAddress(entity.getAddress());
//        Curator.setFiscalCode(entity.getFiscalCode());
//        return Curator;
//    }
//
//    @Override
//    public ContributorEntity entity(Curator model) {
//        if (model == null) return null;
//        ContributorEntity contributorEntity = new ContributorEntity();
//        //TODO: copiare da ContribuotMapper una volta finito tranne setAuthorizedBy
//        contributorEntity.setFirstname(model.getFirstname());
//        contributorEntity.setLastname(model.getLastname());
//        contributorEntity.setBirthday(new java.sql.Date(model.getBirthday().getTime()));
//        contributorEntity.setEmail(model.getEmail());
//        contributorEntity.setPhone(model.getPhone());
//        contributorEntity.setAddress(model.getAddress());
//        contributorEntity.setAuthorizedBy(contributorEntity);
//        return contributorEntity;
//    }
//}
