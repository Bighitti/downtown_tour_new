package it.unicam.ids.dciotti.downtowntour.repository;

import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContributorRepository extends JpaRepository<ContributorEntity, Integer> {
    Optional<ContributorEntity> findByEmail(String email);
}
