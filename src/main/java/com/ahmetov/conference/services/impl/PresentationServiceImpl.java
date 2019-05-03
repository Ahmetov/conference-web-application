package com.ahmetov.conference.services.impl;

import com.ahmetov.conference.entities.Presentation;
import com.ahmetov.conference.entities.Room;
import com.ahmetov.conference.repository.PresentationRepository;
import com.ahmetov.conference.services.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PresentationServiceImpl implements PresentationService {
    @Autowired
    private PresentationRepository presentationRepository;

    @Override
    public Presentation findPresentationById(String id) {
        Long parsedId = Long.parseLong(id);
        return presentationRepository.findPresentationById(parsedId);
    }

    @Override
    public void deletePresentationById(String id) {
        Long parsedId = Long.parseLong(id);
        presentationRepository.deletePresentationById(parsedId);
    }

    @Override
    public Collection<Presentation> findAll() {
        return presentationRepository.findAll();
    }

    @Override
    public void save(Presentation presentation) {
        System.out.println(presentation.getId());
        System.out.println(presentation.getPresentationRoom().getId());
        presentationRepository.save(presentation);

    }

    @Override
    public Collection<Presentation> findByPresentationRoom(Room room) {
        return presentationRepository.findByPresentationRoom(room);
    }
}
