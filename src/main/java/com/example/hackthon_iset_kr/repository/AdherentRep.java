package com.example.hackthon_iset_kr.repository;
import com.example.hackthon_iset_kr.model.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

    @Repository
    public interface AdherentRep extends JpaRepository<Adherent, Integer> {

        Optional<Adherent> findByEmail(String email);

        boolean existsByEmail(String email);

    }

