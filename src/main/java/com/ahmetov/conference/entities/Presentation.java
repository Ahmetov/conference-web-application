package com.ahmetov.conference.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRESENTATIONS")
public class Presentation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "presentation_id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "pres_time")
    private LocalDateTime presentationTime;

    @ManyToOne(cascade = {CascadeType.MERGE}) //deleted CascadeType.PERSIST AND REMOVE теперь нет detached exception и не удаляется room при удалении презентации
    @JoinColumn(name = "pres_room_id", referencedColumnName = "room_id")
    private Room presentationRoom;

//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "PRESENTATION_LECTORS",
//                joinColumns = @JoinColumn(name = "presentation_id"))
//    private Set<User> lectors;

    @ManyToMany(mappedBy = "presentations", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> listeners = new HashSet<>();

    @PrePersist
    public void addListenersToPresentation(){
        System.out.println("pre persist Presentation");

        presentationRoom.getPresentations().add(this);


        for(User listener : listeners){
            System.out.println("pre persist listeners");
            listener.getPresentations().add(this);
        }
    }

    @PreRemove
    public void removeListenersFromPresentation(){
        System.out.println("pre remove Presentation");

        presentationRoom.getPresentations().remove(this);

        for(User listener : listeners){
            System.out.println("pre remove listeners");
            listener.getPresentations().remove(this);
        }
    }

    public String getFormatedPresentationDateTime(){
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
