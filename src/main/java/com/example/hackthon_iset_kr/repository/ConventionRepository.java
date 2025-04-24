package com.example.hackthon_iset_kr.repository;

import com.example.hackthon_iset_kr.model.Convention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConventionRepository extends JpaRepository<Convention, Long> {
    // Tu peux ajouter des méthodes personnalisées ici si besoin
}
