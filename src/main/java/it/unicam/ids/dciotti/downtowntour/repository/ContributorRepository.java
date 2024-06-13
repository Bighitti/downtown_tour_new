package it.unicam.ids.dciotti.downtowntour.repository;

import it.unicam.ids.dciotti.downtowntour.entity.ContributorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributorRepository extends JpaRepository<ContributorEntity, Integer> {
}
