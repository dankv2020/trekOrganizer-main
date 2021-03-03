package org.home.trekOrganizer.repository;

import org.home.trekOrganizer.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {

    List<Journey> findByNameContainingIgnoreCase(String query);

    @Modifying
    @Transactional
    Integer deleteByName(String name);
}
