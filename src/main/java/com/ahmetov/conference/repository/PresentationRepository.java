package com.ahmetov.conference.repository;

import com.ahmetov.conference.entities.Presentation;
import com.ahmetov.conference.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Repository
@Transactional
public interface PresentationRepository extends JpaRepository<Presentation, Long> {

    Presentation findPresentationById(Long id);
    void deletePresentationById(Long id);
    Collection<Presentation> findByPresentationRoom(Room presentationRoom);
}
