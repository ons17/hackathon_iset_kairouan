package com.example.hackthon_iset_kr.repository;

import com.example.hackthon_iset_kr.model.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Integer> {
    Optional<Responsable> findByAdherentId(Long adherentId);
}