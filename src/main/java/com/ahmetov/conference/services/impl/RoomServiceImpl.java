package com.ahmetov.conference.services.impl;

import com.ahmetov.conference.entities.Room;
import com.ahmetov.conference.repository.RoomRepository;
import com.ahmetov.conference.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;



    @Override
    public Room findRoomById(String id) {
        try {
            Long parsedId = Long.parseLong(id);
            return roomRepository.findRoomById(parsedId);
        } catch (NumberFormatException ex){
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
