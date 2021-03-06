package com.ahmetov.conference.repository;

import com.ahmetov.conference.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findRoomById(Long id);

    void deleteRoomById(Long id);
}
