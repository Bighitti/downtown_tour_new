package it.unicam.ids.dciotti.downtowntour.service.impl;

import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.dto.UserDTO;
import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
import it.unicam.ids.dciotti.downtowntour.exception.InvalidCredentialsException;
import it.unicam.ids.dciotti.downtowntour.exception.UserNotFoundException;
import it.unicam.ids.dciotti.downtowntour.mapper.UserMapper;
import it.unicam.ids.dciotti.downtowntour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.unicam.ids.dciotti.downtowntour.repository.ContributorRepository;
import it.unicam.ids.dciotti.downtowntour.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {
    private final UserMapper userMapper;
    private final ContributorRepository userRepository;

    @Autowired
    public UserServiceimpl(
            UserMapper userMapper,
            ContributorRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.modelFromDto(userDTO);
        ContributorEntity contributorEntity = userMapper.entity(user);
        contributorEntity.setPassword(userDTO.getPassword());
        userRepository.save(contributorEntity);
        return userMapper.dto(userMapper.modelFromEntity(contributorEntity));
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws InvalidCredentialsException {
        ContributorEntity ContributorEntity = userRepository.findByEmail(loginDTO.getEmail()).orElse(null);
        if (ContributorEntity == null || !ContributorEntity.getPassword().equals(loginDTO.getPassword())) {
            throw new InvalidCredentialsException();
        }
        return userMapper.dto(ContributorEntity);
    }

    @Override
    public UserDTO retrieveUser(Integer id) {
        return userMapper.dto(
                userMapper.modelFromEntity(
                        userRepository.findById(id).orElse(null)));
    }

    @Override
    public List<UserDTO> retrieveAllUsers() {
        List<ContributorEntity> userEntities = userRepository.findAll();
        List<UserDTO> output = new ArrayList<>();
        for (ContributorEntity ContributorEntity : userEntities) {
            output.add(
                    userMapper.dto(
                            userMapper.modelFromEntity(
                                    ContributorEntity)));
        }
        return output;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(Integer id, UserDTO userDTO) throws UserNotFoundException {
        Optional<ContributorEntity> optionalContributorEntity = userRepository.findById(id);
        if (optionalContributorEntity.isPresent()) {
            ContributorEntity contributorEntity = optionalContributorEntity.get();
            contributorEntity.setFirstname(userDTO.getFirstname());
            contributorEntity.setLastname(userDTO.getLastname());
            contributorEntity.setBirthday(new java.sql.Date(userDTO.getBirthday().getTime()));
            contributorEntity.setEmail(userDTO.getEmail());
            contributorEntity.setPassword(userDTO.getPassword());
            contributorEntity.setPhone(userDTO.getPhone());
            contributorEntity.setAddress(userDTO.getAddress());
            contributorEntity.setFiscalCode(userDTO.getFiscalCode());
            contributorEntity.setPrivilegesCSV(userDTO.getPrivilegesCSV());
            userRepository.save(contributorEntity);
            return userMapper.dto(userMapper.modelFromEntity(contributorEntity));
        }
        throw new UserNotFoundException();
    }
}