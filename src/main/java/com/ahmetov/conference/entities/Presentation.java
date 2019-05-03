package com.ahmetov.conference.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRESENTATIONS")
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "presentation_id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "pres_time", unique = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime presentationTime;

    @ManyToOne()
    @JoinColumn(name = "pres_room_id", referencedColumnName = "room_id")
    private Room presentationRoom;

    @ManyToMany(mappedBy = "presentations", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> listeners = new HashSet<>();

    @PrePersist
    public void addListenersToPresentation() {
        presentationRoom.getPresentations().add(this);
        for (User listener : listeners) {
            listener.getPresentations().add(this);
        }
    }

    @PreRemove
    public void removeListenersFromPresentation() {
        presentationRoom.getPresentations().remove(this);
        for (User listener : listeners) {
            listener.getPresentations().remove(this);
        }
    }

    public String getFormatedPresentationDateTime() {
        if (presentationTime != null)
            return presentationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return null;
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

    public Set<User> getListeners() {
        return listeners;
    }

    public void setListeners(Set<User> listeners) {
        this.listeners = listeners;
    }


}
