package com.ahmetov.conference.services;

import com.ahmetov.conference.entities.Presentation;
import com.ahmetov.conference.entities.Room;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface PresentationService {
    Presentation findPresentationById(String id);
    void deletePresentationById(String id);
    Collection<Presentation> findAll();
    void save(Presentation presentation);
    Collection<Presentation> findByPresentationRoom(Room room);
}
