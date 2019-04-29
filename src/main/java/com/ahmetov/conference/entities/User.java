package com.ahmetov.conference.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_PRESENTATIONS",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "presentation_id")
    )
    private Set<Presentation> presentations = new HashSet<>();
//
//    @ManyToMany(mappedBy = "lectors", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    private Presentation presentationForLector;
//
//    @ManyToMany(mappedBy = "listeners", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    private Presentation presentationForListeners;


    public Set<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(Set<Presentation> presentations) {
        this.presentations = presentations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
