package com.example.hackthon_iset_kr.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "responsables")
public class Responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adherent_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Adherent adherent;


    @Column(nullable = false)
    private String nomResponsable;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public String getNomResponsable() {
        return nomResponsable;
    }

    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
    }
}

