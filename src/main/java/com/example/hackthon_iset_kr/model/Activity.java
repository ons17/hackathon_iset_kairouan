package com.example.hackthon_iset_kr.model;

import jakarta.persistence.*;
@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @OneToOne
    @JoinColumn(name = "id_responsable")
    private Responsable responsable;

    @Transient
    private Long adherentId;

    public Activity(Long id, String name, String description, Responsable responsable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.responsable = responsable;
    }

    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public Long getAdherentId() {
        return adherentId;
    }

    public void setAdherentId(Long adherentId) {
        this.adherentId = adherentId;
    }
}
