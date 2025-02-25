package it.unicam.ids.dciotti.downtowntour.service.impl;

import it.unicam.ids.dciotti.downtowntour.dto.ChallengeDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.dto.UserDTO;
import it.unicam.ids.dciotti.downtowntour.entity.*;
import it.unicam.ids.dciotti.downtowntour.exception.*;
import it.unicam.ids.dciotti.downtowntour.repository.*;
import it.unicam.ids.dciotti.downtowntour.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final ContributorServiceImpl contributorService;
    private final ChallengeContributorRepository challengeContributorRepository;
    private final ChallengeRepository challengeRepository;
    private final ContentRepository contentRepository;
    private final ContributorRepository contributorRepository;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final TouristRepository touristRepository;

    @Autowired
    public AdminServiceImpl(
            ContributorServiceImpl contributorService,
            ChallengeContributorRepository challengeContributorRepository,
            ChallengeRepository challengeRepository,
            ContentRepository contentRepository,
            ContributorRepository contributorRepository,
            UserRepository userRepository,
            ReportRepository reportRepository,
            TouristRepository touristRepository) {
        this.contributorService = contributorService;
        this.challengeContributorRepository = challengeContributorRepository;
        this.challengeRepository = challengeRepository;
        this.contentRepository = contentRepository;
        this.contributorRepository = contributorRepository;
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
        this.touristRepository = touristRepository;
    }

    @Override
    public void createUser(UserDTO userDTO) throws AdminCreationFailException {
        UserEntity userEntity = userEntity(userDTO);
        try {
            userRepository.save(userEntity);
        } catch (Exception ex) {
            throw new AdminCreationFailException(ex);
        }
    }

    @Override
    public UserDTO login(LoginDTO loginDTO) {
        UserEntity userEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        return userDTO(userEntity);
    }

    @Override
    public void reportSolve(Integer reportId, LoginDTO loginDTO) throws AdminPrivilegeException, ReportNotFoundException, UserNotFoundException {
        UserEntity userEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        if (userEntity == null) {
            throw new UserNotFoundException();
        }
        if (!isModerator(userEntity)) {
            throw new AdminPrivilegeException();
        }
        ReportEntity reportEntity = reportRepository.findById(reportId).orElse(null);
        if (reportEntity == null) throw new ReportNotFoundException();
        reportEntity.setSolved(true);
        reportRepository.save(reportEntity);
    }

    @Override
    public ChallengeDTO challengeStart(ChallengeDTO challengeDTO) throws AdminPrivilegeException, UserNotFoundException {
        UserEntity userEntity = userRepository.findByEmailAndPassword(challengeDTO.getLogin().getEmail(), challengeDTO.getLogin().getPassword()).orElse(null);
        if (userEntity == null) {
            throw new UserNotFoundException();
        }
        if (!isEntertainer(userEntity)) {
            throw new AdminPrivilegeException();
        }
        ChallengeEntity challengeEntity = challengeEntity(challengeDTO);
        challengeEntity.setId(null);
        challengeEntity.setEntertainerId(userEntity.getId());
        challengeRepository.save(challengeEntity);
        return fillChallenge(challengeEntity);
    }

    @Override
    public void promoteAdmin(Integer userId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException, UserNotFoundException {
        promote(userId, loginDTO, "ADMIN");
    }

    @Override
    public void promoteEntertainer(Integer userId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException, UserNotFoundException {
        promote(userId, loginDTO, "ENTERTAINER");
    }

    @Override
    public void promoteModerator(Integer userId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException, UserNotFoundException {
        promote(userId, loginDTO, "MODERATOR");
    }

    private void promote(Integer userToPromoteId, LoginDTO adminLoginDTO, String promote) throws AdminNotFoundException, AdminPrivilegeException, UserNotFoundException {
        //L'admin si identifica
        UserEntity adminEntity = userRepository.findByEmailAndPassword(adminLoginDTO.getEmail(), adminLoginDTO.getPassword()).orElse(null);
        if (adminEntity == null) throw new AdminNotFoundException();
        if (!isAdmin(adminEntity)) throw new AdminPrivilegeException();
        UserEntity userEntity = userRepository.findById(userToPromoteId).orElse(null);
        if (userEntity == null) throw new UserNotFoundException();
        if (userEntity.getPrivilegeCSV() == null) userEntity.setPrivilegeCSV("");
        if (userEntity.getPrivilegeCSV().contains(promote)) return;
        userEntity.setPrivilegeCSV(userEntity.getPrivilegeCSV() + promote);
        userRepository.save(userEntity);
    }

    @Override
    public void promoteFromContributorToCurator(Integer contributorId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException, UserNotFoundException {
        UserEntity adminEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        if (adminEntity == null) throw new AdminNotFoundException();
        if (!isModerator(adminEntity)) throw new AdminPrivilegeException();
        ContributorEntity contributorEntity = contributorRepository.findById(contributorId).orElse(null);
        if (contributorEntity == null) throw new UserNotFoundException();
        contributorEntity.setCuratorId(contributorEntity.getId());
        contributorRepository.save(contributorEntity);
    }

    @Override
    public void demoteFromCuratorToContributor(Integer contributorId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException, UserNotFoundException {
        UserEntity adminEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        if (adminEntity == null) throw new AdminNotFoundException();
        if (!isModerator(adminEntity)) throw new AdminPrivilegeException();
        ContributorEntity curatorEntity = contributorRepository.findById(contributorId).orElse(null);
        if (curatorEntity == null) throw new UserNotFoundException();
        if (contributorService.curator(curatorEntity)) {
            List<ContentEntity> contents = contentRepository.findByIdCurator(curatorEntity.getId());
            for (ContentEntity content : contents) {
                content.setIdCurator(null);
            }
            List<ContributorEntity> contributors = contributorRepository.findByCuratorId(curatorEntity.getId());
            for (ContributorEntity contributor : contributors) {
                contributor.setCuratorId(null);
                contributorRepository.save(contributor);
            }
        }
    }

    @Override
    public void unauthorizeContent(Integer contentId, LoginDTO loginDTO) throws AdminNotFoundException, AdminPrivilegeException, ContentNotFoundException {
        UserEntity adminEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        if (adminEntity == null) throw new AdminNotFoundException();
        if (!isModerator(adminEntity)) throw new AdminPrivilegeException();
        ContentEntity contentEntity = contentRepository.findById(contentId).orElse(null);
        if (contentEntity == null) throw new ContentNotFoundException();
        contentEntity.setIdCurator(null);
        contentRepository.save(contentEntity);
    }

    @Override
    @Transactional
    public void deleteAdmin(LoginDTO loginDTO) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        if (userEntity == null) {
            throw new UserNotFoundException();
        }
        reportRepository.deleteByUserId(userEntity.getId());
        reportRepository.flush();
        touristRepository.deleteByUserId(userEntity.getId());
        touristRepository.flush();
        ContributorEntity contributorEntity = contributorRepository.findOneByUserId(userEntity.getId()).orElse(null);
        for (ContentEntity content : contentRepository.findByIdContributor(contributorEntity.getId())) {
            reportRepository.deleteByContentId(content.getId());
            contentRepository.delete(content);
        }
        reportRepository.flush();
        contentRepository.flush();
        if (contributorEntity != null) {
            try {
                contributorService.deleteContributor(contributorEntity.getId());
            } catch (ContentExistsException | ContributorNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        contributorRepository.flush();
        challengeRepository.deleteByEntertainerId(userEntity.getId());
        challengeRepository.flush();
        userRepository.delete(userEntity);
    }

    public boolean isAdmin(UserEntity userEntity) {
        return userEntity.getPrivilegeCSV() != null && userEntity.getPrivilegeCSV().toUpperCase().contains("ADMIN");
    }

    public boolean isEntertainer(UserEntity userEntity) {
        return userEntity.getPrivilegeCSV() != null && userEntity.getPrivilegeCSV().toUpperCase().contains("ENTERTAINER");
    }

    public boolean isModerator(UserEntity userEntity) {
        return userEntity.getPrivilegeCSV() != null && userEntity.getPrivilegeCSV().toUpperCase().contains("MODERATOR");
    }

    public ChallengeDTO fillChallenge(ChallengeEntity challengeEntity) {
        ChallengeDTO challengeDTO = new ChallengeDTO();
        challengeDTO.setId(challengeEntity.getId());
        challengeDTO.setName(challengeEntity.getName());
        challengeDTO.setDescription(challengeEntity.getDescription());
        challengeDTO.setMaxPlayers(challengeEntity.getMaxPlayers());
        challengeDTO.setStart(new Date(challengeEntity.getStart().getTime()));
        challengeDTO.setEnd(new Date(challengeEntity.getEnd().getTime()));
        challengeDTO.setOpen(challengeEntity.getOpen());
        challengeDTO.setContributors(new ArrayList<>());
        List<ChallengeContributorEntity> subscribed = challengeContributorRepository.findByIdChallengeId(challengeEntity.getId());
        for (ChallengeContributorEntity cce : subscribed) {
            challengeDTO.getContributors().add(contributorService.retrieveContributor(cce.getId().getContributorId()));
        }
        challengeDTO.setEntertainer(userDTO(userRepository.findById(challengeEntity.getEntertainerId()).orElse(null)));
        return challengeDTO;
    }

    private ChallengeEntity challengeEntity(ChallengeDTO challengeDTO) {
        ChallengeEntity challengeEntity = new ChallengeEntity();
        challengeEntity.setId(challengeDTO.getId());
        challengeEntity.setName(challengeDTO.getName());
        challengeEntity.setDescription(challengeDTO.getDescription());
        challengeEntity.setMaxPlayers(challengeDTO.getMaxPlayers());
        challengeEntity.setStart(new Timestamp(challengeDTO.getStart().getTime()));
        challengeEntity.setEnd(new Timestamp(challengeDTO.getEnd().getTime()));
        challengeEntity.setOpen(challengeDTO.isOpen());
        if (challengeDTO.getEntertainer() != null) {
            challengeEntity.setEntertainerId(challengeDTO.getEntertainer().getId());
        }
        return challengeEntity;
    }

    private UserEntity userEntity(UserDTO userDTO) {
        return contributorService.userEntity(userDTO);
    }

    private UserDTO userDTO(UserEntity userEntity) {
        UserDTO userDTO = contributorService.userDTO(userEntity);
        userDTO.setPrivilegesCSV(userEntity.getPrivilegeCSV());
        return userDTO;
    }
}
