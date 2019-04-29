package com.ahmetov.conference.entities;

import java.time.LocalDateTime;
import java.util.Set;

public class Scedule {

    private LocalDateTime presentationTime;
    private Room room;

    private Set<Presentation> presentations;
}
