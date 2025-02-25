package it.unicam.ids.dciotti.downtowntour.repository;

import it.unicam.ids.dciotti.downtowntour.entity.TouristEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface TouristRepository extends JpaRepository<TouristEntity, Integer> {
        Optional<TouristEntity> findOneByUuid(String uuid);

        Optional<TouristEntity> findOneByUserId(Integer userId);

        void deleteByUserId(Integer userId);
    }