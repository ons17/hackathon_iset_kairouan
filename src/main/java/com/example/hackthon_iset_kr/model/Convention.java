package com.example.hackthon_iset_kr.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conventions")
public class Convention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    // Relation Many-to-Many avec les adhérents
    @ManyToMany
    @JoinTable(
            name = "convention_adherent",
            joinColumns = @JoinColumn(name = "convention_id"),
            inverseJoinColumns = @JoinColumn(name = "adherent_id")
    )
    private List<Adherent> adherents = new ArrayList<>();

    // Relation Many-to-Many avec les activités
    @ManyToMany
    @JoinTable(
            name = "convention_activity",
            joinColumns = @JoinColumn(name = "convention_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<Activity> activities = new ArrayList<>();

    // Constructeurs
    public Convention() {}

    public Convention(String name, String description, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public List<Adherent> getAdherents() { return adherents; }
    public void setAdherents(List<Adherent> adherents) { this.adherents = adherents; }

    public List<Activity> getActivities() { return activities; }
    public void setActivities(List<Activity> activities) { this.activities = activities; }
}
