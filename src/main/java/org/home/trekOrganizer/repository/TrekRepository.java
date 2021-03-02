package org.home.trekOrganizer.repository;

import org.home.trekOrganizer.model.Trek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TrekRepository extends JpaRepository<Trek, Long> {

    List<Trek> findByNameOrDescriptionContaining(String name, String description);

    @Modifying
    @Transactional
    Long deleteByName(String name);
}
