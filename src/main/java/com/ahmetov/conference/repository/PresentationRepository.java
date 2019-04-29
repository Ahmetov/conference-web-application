package com.ahmetov.conference.repository;

import com.ahmetov.conference.entities.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PresentationRepository extends JpaRepository<Presentation, Long> {

    Presentation findPresentationById(Long id);
    void deletePresentationById(Long id);
}
