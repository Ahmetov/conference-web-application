package com.ahmetov.conference.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRESENTATIONS")
public class Presentation {

    //TODO не работает каскадное удаление через sql запрос(т.к. аннотации работают ток при вызове hibernate.delete.
    //НАДО добавить constraint или чет  еще)

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "presentation_id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "PRESENTATION_LECTORS",
//                joinColumns = @JoinColumn(name = "presentation_id"))
//    private Set<User> lectors;

    @ManyToMany(mappedBy = "presentations", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> listeners = new HashSet<>();

    @PrePersist
    public void addListenersToPresentation(){
        System.out.println("pre persist listeners");
        for(User listener : listeners){
            System.out.println("pre persist listeners");
            listener.getPresentations().add(this);
        }
    }

    @PreRemove
    public void removeListenersFromPresentation(){
        System.out.println("pre persist listeners");
        for(User listener : listeners){
            System.out.println("pre remove listeners");
            listener.getPresentations().remove(this);
        }
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
