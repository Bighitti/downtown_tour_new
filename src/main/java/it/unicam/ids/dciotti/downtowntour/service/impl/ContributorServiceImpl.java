package it.unicam.ids.dciotti.downtowntour.service.impl;

import it.unicam.ids.dciotti.downtowntour.dto.*;
import it.unicam.ids.dciotti.downtowntour.entity.ContentEntity;
import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
import it.unicam.ids.dciotti.downtowntour.entity.UserEntity;
import it.unicam.ids.dciotti.downtowntour.exception.*;
import it.unicam.ids.dciotti.downtowntour.repository.*;
import it.unicam.ids.dciotti.downtowntour.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ContributorServiceImpl implements ContributorService {
    private final ContributorRepository contributorRepository;
    private final UserRepository userRepository;
    private final ChallengeContributorRepository challengeContributorRepository;
    private final ContentRepository contentRepository;

    @Autowired
    public ContributorServiceImpl(
            ContributorRepository contributorRepository,
            UserRepository userRepository,
            ChallengeContributorRepository challengeContributorRepository,
            ContentRepository contentRepository) {
        this.contributorRepository = contributorRepository;
        this.userRepository = userRepository;
        this.challengeContributorRepository = challengeContributorRepository;
        this.contentRepository = contentRepository;
    }

    @Override
    public ContributorDTO createContributor(ContributorDTO contributorDTO) throws UserDataRequiredException {
        if (contributorDTO.getUser() == null) throw new UserDataRequiredException();
        UserEntity userEntity = null;
        if (contributorDTO.getUser().getId() != null) {
            userEntity = userRepository.findById(contributorDTO.getUser().getId()).orElse(null);
            if (userEntity == null) contributorDTO.getUser().setId(null);
        }
        if (userEntity == null) {
            userEntity = userEntity(contributorDTO.getUser());
        }
        userRepository.save(userEntity);
        ContributorEntity contributorEntity = contributorEntity(contributorDTO);
        contributorEntity.setUserId(userEntity.getId());
        contributorRepository.save(contributorEntity);
        ContributorDTO output = contributorDTO(contributorEntity);
        output.setUser(userDTO(userEntity));
        return output;
    }

    @Override
    public ContributorDTO retrieveContributor(Integer contributorId) {
        ContributorEntity contributorEntity = contributorRepository.findById(contributorId).orElse(null);
        return fillContributor(contributorEntity);
    }

    @Override
    public List<ContributorDTO> retrieveAllContributors() {
        List<ContributorEntity> contributorEntities = contributorRepository.findAll();
        List<ContributorDTO> dtos = new ArrayList<>();
        for (ContributorEntity contributorEntity : contributorEntities) {
            dtos.add(fillContributor(contributorEntity));
        }
        return dtos;
    }

    @Override
    public ContributorDTO authorizeContributorByCurator(Integer contributorId, LoginDTO loginDTO) throws ContributorNotFoundException, CuratorNotFoundException, CuratorRequiresNoAuthorizationException {
        ContributorEntity contributorEntity;
        try {
            contributorEntity = contributorRepository.findById(contributorId).get();
        } catch (NoSuchElementException ignore) {
            throw new ContributorNotFoundException();
        }
        if (curator(contributorEntity)) throw new CuratorRequiresNoAuthorizationException();
        UserEntity userEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        if (userEntity == null) throw new CuratorNotFoundException();
        ContributorEntity curatorEntity;
        try {
            curatorEntity = contributorRepository.findOneByUserId(userEntity.getId()).get();
            if (!curator(curatorEntity)) throw new NoSuchElementException();
        } catch (NoSuchElementException ignore) {
            throw new CuratorNotFoundException(ignore);
        }
        contributorEntity.setCuratorId(curatorEntity.getId());
        contributorRepository.save(contributorEntity);
        return fillContributor(contributorEntity);
    }

    @Override
    @Transactional
    public void deleteContributor(Integer contributorId) throws ContentExistsException, ContributorNotFoundException {
        ContributorEntity contributorEntity = contributorRepository.findById(contributorId).orElse(null);
        if (contributorEntity == null) throw new ContributorNotFoundException();
        //Contributor.contents.size() == 0
        if (contentRepository.existsByIdContributor(contributorId)) throw new ContentExistsException();
        if (curator(contributorEntity)) {
            for (ContentEntity contentEntity : contentRepository.findByIdCurator(contributorId)) {
                contentEntity.setIdCurator(null);
                contentRepository.save(contentEntity);
            }
            contentRepository.flush();
            List<ContributorEntity> authorized = contributorRepository.findByCuratorId(contributorEntity.getId());
            for (ContributorEntity c : authorized) {
                c.setCuratorId(null);
                contributorRepository.save(c);
            }
            contributorRepository.flush();
        }
        challengeContributorRepository.deleteByIdContributorId(contributorId);
        challengeContributorRepository.flush();
        contributorRepository.deleteById(contributorId);
    }

    private ContributorDTO fillContributor(ContributorEntity contributorEntity) {
        //contributorDTO.id
        ContributorDTO contributorDTO = contributorDTO(contributorEntity);
        if (contributorDTO != null) {
            //contributorDTO.user
            contributorDTO.setUser(userDTO(userRepository.findById(contributorEntity.getUserId()).get()));
            if (contributorEntity.getCuratorId() != null) {
                //contributor.curator
                //contributor.curator.id
                ContributorEntity curatorEntity = contributorRepository.findById(contributorEntity.getCuratorId()).orElse(null);
                contributorDTO.setCurator((CuratorDTO) contributorDTO(curatorEntity));
                if (contributorDTO.getCurator() != null) {
                    //contributor.curator.user
                    contributorDTO.getCurator().setUser(userDTO(userRepository.findById(curatorEntity.getUserId()).get()));
                }
            }
        }
        return contributorDTO;
    }

    public boolean curator(ContributorEntity c) {
        return c.getId() != null && c.getId().equals(c.getCuratorId());
    }

    public UserEntity userEntity(UserDTO input) {
        if (input == null) return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(input.getFirstname());
        userEntity.setLastname(input.getLastname());
        userEntity.setBirthday(new java.sql.Date(input.getBirthday().getTime()));
        userEntity.setEmail(input.getEmail());
        userEntity.setPassword(input.getPassword());
        userEntity.setPhone(input.getPhone());
        userEntity.setAddress(input.getAddress());
        userEntity.setFiscalCode(input.getFiscalCode());
        return userEntity;
    }

    public UserDTO userDTO(UserEntity input) {
        if (input == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(input.getId());
        userDTO.setFirstname(input.getFirstname());
        userDTO.setLastname(input.getLastname());
        userDTO.setBirthday(input.getBirthday());
        userDTO.setEmail(input.getEmail());
        userDTO.setPassword(input.getPassword());
        userDTO.setPhone(input.getPhone());
        userDTO.setAddress(input.getAddress());
        userDTO.setFiscalCode(input.getFiscalCode());
        return userDTO;
    }

    private ContributorDTO contributorDTO(ContributorEntity input) {
        if (input == null) return null;
        ContributorDTO contributorDTO = curator(input) ? new CuratorDTO() : new ContributorDTO();
        contributorDTO.setId(input.getId());
        return contributorDTO;
    }

    private ContributorEntity contributorEntity(ContributorDTO input) {
        if (input == null) return null;
        ContributorEntity contributorEntity = new ContributorEntity();
        contributorEntity.setId(input.getId());
        contributorEntity.setUserId(input.getUser() == null ? null : input.getUser().getId());
        contributorEntity.setCuratorId(input.getCurator() == null ? null : input.getCurator().getId());
        return contributorEntity;
    }
}
