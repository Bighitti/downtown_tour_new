package it.unicam.ids.dciotti.downtowntour.repository;

import it.unicam.ids.dciotti.downtowntour.entity.ChallengeContributorEntity;
import it.unicam.ids.dciotti.downtowntour.entity.ChallengeContributorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeContributorRepository extends JpaRepository<ChallengeContributorEntity, ChallengeContributorEntityPK> {
    List<ChallengeContributorEntity> findByIdChallengeId(Integer challengeId);

    void deleteByIdContributorId(Integer contributorId);

    void deleteByIdChallengeId(Integer challengeId);
}
