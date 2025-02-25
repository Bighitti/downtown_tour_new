package it.unicam.ids.dciotti.downtowntour.repository;

import it.unicam.ids.dciotti.downtowntour.entity.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Integer> {
    void deleteByEntertainerId(Integer entertainerId);
}
