package it.unicam.ids.dciotti.downtowntour.service.impl;

import it.unicam.ids.dciotti.downtowntour.dto.ChallengeDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.entity.*;
import it.unicam.ids.dciotti.downtowntour.exception.AdminPrivilegeException;
import it.unicam.ids.dciotti.downtowntour.exception.ChallengeNotFoundException;
import it.unicam.ids.dciotti.downtowntour.exception.UserNotFoundException;
import it.unicam.ids.dciotti.downtowntour.repository.*;
import it.unicam.ids.dciotti.downtowntour.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    private final AdminServiceImpl adminService;
    private final ChallengeContributorRepository challengeContributorRepository;
    private final ChallengeRepository challengeRepository;
    private final ContributorRepository contributorRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChallengeServiceImpl(
            AdminServiceImpl adminService,
            ChallengeContributorRepository challengeContributorRepository,
            ChallengeRepository challengeRepository,
            ContributorRepository contributorRepository,
            UserRepository userRepository) {
        this.adminService = adminService;
        this.challengeContributorRepository = challengeContributorRepository;
        this.challengeRepository = challengeRepository;
        this.contributorRepository = contributorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ChallengeDTO retrieveChallenge(Integer challengeId) {
        ChallengeEntity challengeEntity = challengeRepository.findById(challengeId).orElse(null);
        return adminService.fillChallenge(challengeEntity);
    }

    @Override
    public List<ChallengeDTO> retrieveAllChallenges() {
        List<ChallengeEntity> challengeEntities = challengeRepository.findAll();
        List<ChallengeDTO> dtos = new ArrayList<>();
        for (ChallengeEntity challengeEntity : challengeEntities) {
            dtos.add(adminService.fillChallenge(challengeEntity));
        }
        return dtos;
    }

    /**
     * @return false if subscribe is not possible
     */
    @Override
    public Boolean subscribeToChallenge(Integer challengeId, LoginDTO loginDTO) {
        //challenge exist?
        ChallengeEntity challengeEntity = challengeRepository.findById(challengeId).orElse(null);
        if (challengeEntity == null) return false;
        //user exist?
        UserEntity userEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        if (userEntity == null) return false;
        //user is a contributor?
        ContributorEntity contributorEntity = contributorRepository.findOneByUserId(userEntity.getId()).orElse(null);
        if (contributorEntity == null) return false;
        //getAllSubscription for challengeId
        List<ChallengeContributorEntity> subscribed = challengeContributorRepository.findByIdChallengeId(challengeEntity.getId());
        //maxPlayers >= subscription?
        if (subscribed.size() >= challengeEntity.getMaxPlayers()) return false;
        //contributor already subscribed?
        for (ChallengeContributorEntity cce : subscribed) {
            if (cce.getId().getContributorId() == contributorEntity.getId()) return false;
        }
        ChallengeContributorEntity challengeContributorEntity = new ChallengeContributorEntity();
        ChallengeContributorEntityPK challengeContributorEntityPK = new ChallengeContributorEntityPK();
        challengeContributorEntity.setId(challengeContributorEntityPK);
        challengeContributorEntityPK.setChallengeId(challengeEntity.getId());
        challengeContributorEntityPK.setContributorId(contributorEntity.getId());
        challengeContributorRepository.save(challengeContributorEntity);
        return true;
    }

    @Override
    @Transactional
    public void deleteChallenge(Integer challengeId, LoginDTO loginDTO) throws AdminPrivilegeException, ChallengeNotFoundException, UserNotFoundException {
        UserEntity userEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        if (userEntity == null) {
            throw new UserNotFoundException();
        }
        ChallengeEntity challengeEntity = challengeRepository.findById(challengeId).orElse(null);
        if (challengeEntity == null) {
            throw new ChallengeNotFoundException();
        }
        if (adminService.isModerator(userEntity) || challengeEntity.getEntertainerId().equals(userEntity.getId())) {
            challengeContributorRepository.deleteByIdChallengeId(challengeEntity.getId());
            challengeContributorRepository.flush();
            challengeRepository.delete(challengeEntity);
        } else {
            throw new AdminPrivilegeException();
        }
    }
}
