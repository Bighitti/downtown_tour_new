package it.unicam.ids.dciotti.downtowntour.repository;

import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContributorRepository extends JpaRepository<ContributorEntity, Integer> {
    List<ContributorEntity> findByCuratorId(Integer curatorId);

    Optional<ContributorEntity> findOneByUserId(Integer userId);
}
