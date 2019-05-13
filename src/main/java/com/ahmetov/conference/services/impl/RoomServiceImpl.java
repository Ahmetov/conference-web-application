package com.ahmetov.conference.services.impl;

import com.ahmetov.conference.entities.Room;
import com.ahmetov.conference.repository.RoomRepository;
import com.ahmetov.conference.services.RoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoomServiceImpl implements RoomService {
    private static final Logger logger = Logger.getLogger("loggs");

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room findRoomById(String id) {
        try {
            Long parsedId = Long.parseLong(id);
            return roomRepository.findRoomById(parsedId);
        } catch (NumberFormatException ex) {
            logger.error(ex);
            return null;
        }
    }

    @Override
    public void deleteRoomById(String id) {
        Long parsedId = Long.parseLong(id);
        roomRepository.deleteRoomById(parsedId);
    }

    @Override
    public Collection<Room> findAllRooms() {
        return roomRepository.findAll();
    }
}
