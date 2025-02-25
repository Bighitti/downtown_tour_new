package it.unicam.ids.dciotti.downtowntour.service.impl;

import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.dto.LoginDTO;
import it.unicam.ids.dciotti.downtowntour.entity.*;
import it.unicam.ids.dciotti.downtowntour.exception.*;
import it.unicam.ids.dciotti.downtowntour.repository.*;
import it.unicam.ids.dciotti.downtowntour.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ContentServiceImpl implements ContentService {
    private final ContributorServiceImpl contributorService;
    private final ChallengeContributorRepository challengeContributorRepository;
    private final ChallengeRepository challengeRepository;
    private final ContentRepository contentRepository;
    private final ContributorRepository contributorRepository;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Autowired
    public ContentServiceImpl(
            ContributorServiceImpl contributorService,
            ChallengeContributorRepository challengeContributorRepository,
            ChallengeRepository challengeRepository,
            ContentRepository contentRepository,
            ContributorRepository contributorRepository,
            ReportRepository reportRepository,
            UserRepository userRepository) {
        this.contributorService = contributorService;
        this.challengeContributorRepository = challengeContributorRepository;
        this.challengeRepository = challengeRepository;
        this.contentRepository = contentRepository;
        this.contributorRepository = contributorRepository;
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ContentDTO createContent(Integer contributorId, ContentDTO contentDTO) throws ContributorNotFoundException {
        contentDTO.setId(null);
        contentDTO.setPublication(null);
        ContributorEntity contributorEntity = contributorRepository.findById(contributorId).orElse(null);
        if (contributorEntity == null) throw new ContributorNotFoundException();
        ContentEntity contentEntity = contentEntity(contentDTO);
        contentEntity.setPublication(new Timestamp(System.currentTimeMillis()));
        contentEntity.setIdContributor(contributorEntity.getId());
        contentEntity.setIdCurator(contributorEntity.getCuratorId());
        //challengeId present?
        if (contentDTO.getChallengeId() != null) {
            //challenge exists?
            ChallengeEntity challengeEntity = challengeRepository.findById(contentDTO.getChallengeId()).orElse(null);
            if (challengeEntity != null) {
                //contributor is subscribed to challenge?
                ChallengeContributorEntityPK challengeContributorEntityPK = new ChallengeContributorEntityPK();
                challengeContributorEntityPK.setContributorId(contributorEntity.getId());
                challengeContributorEntityPK.setChallengeId(challengeEntity.getId());
                if (challengeContributorRepository.existsById(challengeContributorEntityPK)) {
                    // yes yes yes
                    contentEntity.setIdChallenge(challengeEntity.getId());
                }
            }
        }
        contentRepository.save(contentEntity);
        return contentDTO(contentEntity);
    }

    @Override
    public ContentDTO retrieveContent(Integer contentId) {
        ContentEntity entity = contentRepository.findById(contentId).orElse(null);
        return contentDTO(entity);
    }

    @Override
    public List<ContentDTO> retrieveContentUnauthorized() {
        List<ContentEntity> entities = contentRepository.findByIdCuratorIsNull();
        List<ContentDTO> dtos = new ArrayList<>();
        for (ContentEntity entity : entities) {
            dtos.add(contentDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<ContentDTO> retrieveContentByContributor(Integer contributorId) {
        List<ContentEntity> entities = contentRepository.findByIdContributor(contributorId);
        List<ContentDTO> dtos = new ArrayList<>();
        for (ContentEntity entity : entities) {
            dtos.add(contentDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<ContentDTO> retrieveContentAuthorizedByCurator(Integer curatorId) {
        List<ContentEntity> entities = contentRepository.findByIdCurator(curatorId);
        List<ContentDTO> dtos = new ArrayList<>();
        for (ContentEntity entity : entities) {
            dtos.add(contentDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<ContentDTO> retrieveReportedContent() {
        List<ReportEntity> reportEntities = reportRepository.findBySolved(false);
        Set<Integer> contentIds = new HashSet<>();
        for (ReportEntity entity : reportEntities) {
            contentIds.add(entity.getContentId());
        }
        List<ContentEntity> contentEntities = contentRepository.findByIdIn(contentIds);
        List<ContentDTO> dtos = new ArrayList<>();
        for (ContentEntity entity : contentEntities) {
            ContentDTO contentDTO = contentDTO(entity);
            dtos.add(contentDTO);
            for (ReportEntity reportEntity : reportEntities) {
                if (reportEntity.getContentId().equals(entity.getId())) {
                    contentDTO.getReports().add(reportEntity.getId());
                }
            }
        }
        return dtos;
    }

    @Override
    public List<ContentDTO> retrieveAllContents() {
        List<ContentEntity> contentEntities = contentRepository.findAll();
        List<ContentDTO> dtos = new ArrayList<>();
        for (ContentEntity entity : contentEntities) {
            dtos.add(contentDTO(entity));
        }
        return dtos;
    }

    @Override
    public void authorize(Integer contentId, LoginDTO curatorLoginDTO) throws ContentNotFoundException, CuratorNotFoundException {
        ContentEntity contentEntity = contentRepository.findById(contentId).orElse(null);
        if (contentEntity == null) throw new ContentNotFoundException();
        UserEntity userEntity = userRepository.findByEmailAndPassword(curatorLoginDTO.getEmail(), curatorLoginDTO.getPassword()).orElse(null);
        if (userEntity == null) throw new CuratorNotFoundException();
        ContributorEntity curatorEntity = contributorRepository.findOneByUserId(userEntity.getId()).orElse(null);
        if (curatorEntity == null || !contributorService.curator(curatorEntity)) throw new CuratorNotFoundException();
        contentEntity.setIdCurator(curatorEntity.getId());
        contentRepository.save(contentEntity);
    }

    @Override
    public void report(Integer contentId, LoginDTO loginDTO) throws ContentNotFoundException, UserNotFoundException {
        ContentEntity contentEntity = contentRepository.findById(contentId).orElse(null);
        if (contentEntity == null) throw new ContentNotFoundException();
        UserEntity userEntity = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).orElse(null);
        if (userEntity == null) throw new UserNotFoundException();
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setContentId(contentEntity.getId());
        reportEntity.setUserId(userEntity.getId());
        reportRepository.save(reportEntity);
    }

    @Override
    @Transactional
    public void deleteContent(Integer contentDTO) {
        ContentEntity contentEntity = contentRepository.findById(contentDTO).orElse(null);
        if (contentEntity == null) return;
        reportRepository.deleteByContentId(contentEntity.getId());
        reportRepository.flush();
        contentRepository.delete(contentEntity);
    }

    private ContentEntity contentEntity(ContentDTO input) {
        if (input == null) return null;
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setId(input.getId());
        contentEntity.setText(input.getText());
        if (input.getPublication() != null) {
            contentEntity.setPublication(new Timestamp(input.getPublication().getTime()));
        }
        return contentEntity;
    }

    private ContentDTO contentDTO(ContentEntity input) {
        if (input == null) return null;
        ContentDTO contentDTO = new ContentDTO();
        contentDTO.setId(input.getId());
        contentDTO.setText(input.getText());
        contentDTO.setPublication(new Date(input.getPublication().getTime()));
        contentDTO.setChallengeId(input.getIdChallenge());
        return contentDTO;
    }
}
