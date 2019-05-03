package com.ahmetov.conference.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Long id;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "presentationRoom", fetch = FetchType.EAGER)
    private Set<Presentation> presentations = new HashSet<>();

    @PreRemove
    public void removePresentationsFromRooms() {
        presentations.clear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(Set<Presentation> presentations) {
        this.presentations = presentations;
    }

    @Override
    public String toString() {
        return this.location;
    }
}
