package com.example.hackthon_iset_kr.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

        @Column(nullable = false)
        private String name;

        @Column(name = "date_debut")
        private LocalDateTime dateDebut;

        @Column(name = "date_fin")
        private LocalDateTime dateFin;

        @Column(name = "max_place")
        private Integer maxPlace;

        @ManyToMany
        @JoinTable(
                name = "event_adherant",
                joinColumns = @JoinColumn(name = "event_id"),
                inverseJoinColumns = @JoinColumn(name = "adherant_id")
        )
        private List<Adherent> adherants = new ArrayList<>();

        @ManyToMany
        @JoinTable(
                name = "event_activity",
                joinColumns = @JoinColumn(name = "event_id"),
                inverseJoinColumns = @JoinColumn(name = "activity_id")
        )
        private List<Activity> activities = new ArrayList<>();
}

