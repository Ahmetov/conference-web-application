package com.ahmetov.conference.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


//TODO ДОДЕЛАТЬ, А ТО ВЛОЖЕННЫЙ th:each НЕ РАБОТАЕТ
@Entity
@Table(name = "SCEDULE")
public class Scedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scedule_id", nullable = false)
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime presentationTime;
//
//    private Room room;
//
//    private Set<Presentation> presentations;
}
