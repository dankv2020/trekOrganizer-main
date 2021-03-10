package org.home.trekOrganizer.repository;

import org.home.trekOrganizer.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {


    Manager findByLogin(String login);

    @Modifying
    @Transactional
    Integer deleteByLogin(String login);
}
