package it.unicam.ids.dciotti.downtowntour.repository;

import it.unicam.ids.dciotti.downtowntour.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {
    List<ReportEntity> findBySolved(Boolean solved);

    List<ReportEntity> findByContentId(Integer contentId);

    void deleteByUserId(Integer userId);

    void deleteByContentId(Integer contentId);
}
