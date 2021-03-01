package org.home.trekOrganizer.repository;

import org.home.trekOrganizer.model.Trekker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TrekkerRepository extends JpaRepository<Trekker, Long> {

    List<Trekker> findByFullNameOrEmail(String fullName, String email);

    List<Trekker> findByFullNameOrEmailContaining(String fullName, String email);

    @Modifying
    @Transactional
    @Query("Delete From Trekker where fullName = :fullName")
    Integer deleteByFullName(String fullName);
}
