package com.example.hackthon_iset_kr.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

  @ManyToOne
  @JoinColumn(name = "activity_id", nullable = false)
  private Activity activity;

  public Event() {}

  public Event(Long id, String name, LocalDateTime dateDebut, LocalDateTime dateFin,
               Integer maxPlace, List<Adherent> adherants, Activity activity) {
    this.id = id;
    this.name = name;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
    this.maxPlace = maxPlace;
    this.adherants = adherants;
    this.activity = activity;
  }

  // Getters et setters

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public LocalDateTime getDateDebut() { return dateDebut; }

  public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }

  public LocalDateTime getDateFin() { return dateFin; }

  public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }

  public Integer getMaxPlace() { return maxPlace; }

  public void setMaxPlace(Integer maxPlace) { this.maxPlace = maxPlace; }

  public List<Adherent> getAdherants() { return adherants; }

  public void setAdherants(List<Adherent> adherants) { this.adherants = adherants; }

  public Activity getActivity() { return activity; }

  public void setActivity(Activity activity) { this.activity = activity; }



}
