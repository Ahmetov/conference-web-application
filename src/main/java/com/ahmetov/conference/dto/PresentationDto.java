package com.ahmetov.conference.dto;

import com.ahmetov.conference.entities.Room;
import com.ahmetov.conference.entities.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PresentationDto implements Serializable {

    private Long id;

    private String title;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime presentationTime;

    private Room presentationRoom;

    private Set<User> listeners = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPresentationTime() {
        return presentationTime;
    }

    public void setPresentationTime(LocalDateTime presentationTime) {
        this.presentationTime = presentationTime;
    }

    public Room getPresentationRoom() {
        return presentationRoom;
    }

    public void setPresentationRoom(Room presentationRoom) {
        this.presentationRoom = presentationRoom;
    }

    public Set<User> getListeners() {
        return listeners;
    }

    public void setListeners(Set<User> listeners) {
        this.listeners = listeners;
    }
}
