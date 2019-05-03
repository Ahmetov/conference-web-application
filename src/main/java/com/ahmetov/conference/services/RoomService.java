package com.ahmetov.conference.services;

import com.ahmetov.conference.entities.Room;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface RoomService {
    Room findRoomById(String id);

    void deleteRoomById(String id);

    Collection<Room> findAllRooms();
}
