package it.unicam.ids.dciotti.downtowntour.mapper;

import it.unicam.ids.dciotti.downtowntour.dto.UserDTO;
import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
import it.unicam.ids.dciotti.downtowntour.model.Admin;
import it.unicam.ids.dciotti.downtowntour.model.User;

public class UserMapper {

    public UserDTO dto(User model) {
        if (model == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname(model.getFirstname());
        userDTO.setLastname(model.getLastname());
        userDTO.setBirthday(model.getBirthday());
        userDTO.setEmail(model.getEmail());
        userDTO.setPhone(model.getPhone());
        userDTO.setAddress(model.getAddress());
        userDTO.setFiscalCode(model.getFiscalCode());
        if (model instanceof Admin admin) {
            userDTO.setPrivilegesCSV(admin.getPrivilegesCSV());
        }
        return userDTO;
    }

    public User modelFromDto(UserDTO dto) {
        if (dto == null) return null;
        User user;
        if (dto.getPrivilegesCSV() != null) {
            Admin admin = new Admin();
            admin.setPrivilegesCSV(dto.getPrivilegesCSV());
            user = admin;
        } else {
            user = new User();
        }
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setBirthday(dto.getBirthday());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setFiscalCode(dto.getFiscalCode());
        return user;
    }

    public User modelFromEntity(ContributorEntity entity) {
        if (entity == null) return null;
        User user;
        if (entity.getPrivilegesCSV() != null) {
            Admin admin = new Admin();
            admin.setPrivilegesCSV(entity.getPrivilegesCSV());
            user = admin;
        } else {
            user = new User();
        }
        user.setFirstname(entity.getFirstname());
        user.setLastname(entity.getLastname());
        user.setBirthday(entity.getBirthday());
        user.setEmail(entity.getEmail());
        user.setPhone(entity.getPhone());
        user.setAddress(entity.getAddress());
        user.setFiscalCode(entity.getFiscalCode());
        return user;
    }

    public ContributorEntity entity(User model) {
        if (model == null) return null;
        ContributorEntity ContributorEntity = new ContributorEntity();
        ContributorEntity.setFirstname(model.getFirstname());
        ContributorEntity.setLastname(model.getLastname());
        ContributorEntity.setBirthday(new java.sql.Date(model.getBirthday().getTime()));
        ContributorEntity.setEmail(model.getEmail());
        ContributorEntity.setPhone(model.getPhone());
        ContributorEntity.setAddress(model.getAddress());
        ContributorEntity.setFiscalCode(model.getFiscalCode());
        if (model instanceof Admin admin) {
            ContributorEntity.setPrivilegesCSV(admin.getPrivilegesCSV());
        }
        return ContributorEntity;
    }

    public UserDTO dto(ContributorEntity entity) {
        if (entity == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setFirstname(entity.getFirstname());
        userDTO.setLastname(entity.getLastname());
        userDTO.setBirthday(entity.getBirthday());
        userDTO.setEmail(entity.getEmail());
        userDTO.setPhone(entity.getPhone());
        userDTO.setAddress(entity.getAddress());
        userDTO.setFiscalCode(entity.getFiscalCode());
        userDTO.setPrivilegesCSV(entity.getPrivilegesCSV());
        return userDTO;
    }

    public ContributorEntity entity(UserDTO dto) {
        if (dto == null) return null;
        ContributorEntity ContributorEntity = new ContributorEntity();
        ContributorEntity.setId(dto.getId());
        ContributorEntity.setFirstname(dto.getFirstname());
        ContributorEntity.setLastname(dto.getLastname());
        ContributorEntity.setBirthday(new java.sql.Date(dto.getBirthday().getTime()));
        ContributorEntity.setEmail(dto.getEmail());
        ContributorEntity.setPhone(dto.getPhone());
        ContributorEntity.setAddress(dto.getAddress());
        ContributorEntity.setFiscalCode(dto.getFiscalCode());
        ContributorEntity.setPrivilegesCSV(dto.getPrivilegesCSV());
        return ContributorEntity;
    }
}
