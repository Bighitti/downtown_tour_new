package it.unicam.ids.dciotti.downtowntour.repository;

import it.unicam.ids.dciotti.downtowntour.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<ContentEntity, Integer> {
    boolean existsByIdContributor(Integer contributorId);
    List<ContentEntity> findByIdContributor(Integer contributorId);
    List<ContentEntity> findByIdCurator(Integer contributorId);
    List<ContentEntity> findByIdCuratorIsNull();
    List<ContentEntity> findByIdIn(Collection<Integer> ids);
    void deleteByIdContributor(Integer contributorId);
}
