package it.unicam.ids.dciotti.downtowntour.service.impl;

import it.unicam.ids.dciotti.downtowntour.dto.*;
import it.unicam.ids.dciotti.downtowntour.entity.TouristEntity;
import it.unicam.ids.dciotti.downtowntour.entity.UserEntity;
import it.unicam.ids.dciotti.downtowntour.exception.TouristNotFoundException;
import it.unicam.ids.dciotti.downtowntour.exception.UserDataRequiredException;
import it.unicam.ids.dciotti.downtowntour.exception.UserNotFoundException;
import it.unicam.ids.dciotti.downtowntour.repository.ReportRepository;
import it.unicam.ids.dciotti.downtowntour.repository.TouristRepository;
import it.unicam.ids.dciotti.downtowntour.repository.UserRepository;
import it.unicam.ids.dciotti.downtowntour.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TouristServiceimpl implements TouristService {
    private final ContributorServiceImpl contributorService;
    private final ReportRepository reportRepository;
    private final TouristRepository touristRepository;
    private final UserRepository userRepository;

    @Autowired
    public TouristServiceimpl(
            ContributorServiceImpl contributorService,
            ReportRepository reportRepository,
            TouristRepository touristRepository,
            UserRepository userRepository) {
        this.contributorService = contributorService;
        this.reportRepository = reportRepository;
        this.touristRepository = touristRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TouristDTO createTourist(CoordinatesDTO coordinatesDTO) {
        TouristEntity touristEntity = new TouristEntity();
        touristEntity.setUuid(uuid());
        touristEntity.setLastCoordX(coordinatesDTO.getLon());
        touristEntity.setLastCoordY(coordinatesDTO.getLat());
        touristRepository.save(touristEntity);
        return touristDTO(touristEntity);
    }

    @Override
    public TouristDTO retrieveTourist(UUID uuid) {
        TouristEntity touristEntity = touristRepository.findOneByUuid(uuid.toString()).orElse(null);
        return touristDTO(touristEntity);
    }

    @Override
    public TouristDTO loginTourist(LoginDTO loginDTO) {
        UserEntity userEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        if (userEntity == null) return null;
        TouristEntity touristEntity = touristRepository.findOneByUserId(userEntity.getId()).orElse(null);
        if (touristEntity == null) return null;
        touristEntity.setUuid(uuid());
        touristRepository.save(touristEntity);
        TouristDTO touristDTO = touristDTO(touristEntity);
        touristDTO.setUser(userDTO(userEntity));
        return touristDTO;
    }

    @Override
    public TouristDTO updateCoordinates(UUID uuid, CoordinatesDTO coordinatesDTO) throws TouristNotFoundException {
        TouristEntity touristEntity = touristRepository.findOneByUuid(uuid.toString()).orElse(null);
        if (touristEntity == null) throw new TouristNotFoundException();
        touristEntity.setLastCoordX(coordinatesDTO.getLon());
        touristEntity.setLastCoordY(coordinatesDTO.getLat());
        touristRepository.save(touristEntity);
        return touristDTO(touristEntity);
    }

    @Override
    public TouristDTO upgradeTourist(UUID uuid, UserDTO userDTO) throws TouristNotFoundException, UserDataRequiredException {
        TouristEntity touristEntity = touristRepository.findOneByUuid(uuid.toString()).orElse(null);
        if (touristEntity == null) throw new TouristNotFoundException();
        UserEntity userEntity = userEntity(userDTO);
        try {
            userRepository.save(userEntity);
        } catch (Exception ex) {
            throw new UserDataRequiredException(ex);
        }
        touristEntity.setUserId(userEntity.getId());
        touristRepository.save(touristEntity);
        TouristDTO touristDTO = touristDTO(touristEntity);
        touristDTO.setUser(userDTO(userEntity));
        return touristDTO;
    }

    @Override
    @Transactional
    public void deleteTourist(LoginDTO loginDTO) throws TouristNotFoundException, UserNotFoundException {
        UserEntity userEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        if (userEntity == null) throw new UserNotFoundException();
        TouristEntity touristEntity = touristRepository.findOneByUserId(userEntity.getId()).orElse(null);
        if (touristEntity == null) throw new TouristNotFoundException();
        reportRepository.deleteByUserId(userEntity.getId());
        reportRepository.flush();
        touristRepository.delete(touristEntity);
    }

    private String uuid() {
        return UUID.randomUUID().toString();
    }

    public UserEntity userEntity(UserDTO input) {
        return contributorService.userEntity(input);
    }

    public UserDTO userDTO(UserEntity input) {
        return contributorService.userDTO(input);
    }

    private TouristDTO touristDTO(TouristEntity input) {
        if (input == null) return null;
        TouristDTO touristDTO = new TouristDTO();
        touristDTO.setCookie(UUID.fromString(input.getUuid()));
        touristDTO.setActualCoordinates(new CoordinatesDTO(input.getLastCoordY(), input.getLastCoordX()));
        return touristDTO;
    }
}