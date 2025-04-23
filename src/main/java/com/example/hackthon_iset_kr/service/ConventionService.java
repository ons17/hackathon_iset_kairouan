package com.example.hackthon_iset_kr.service;

import com.example.hackthon_iset_kr.model.Convention;
import com.example.hackthon_iset_kr.repository.ConventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConventionService {

    @Autowired
    private ConventionRepository conventionRepository;

    public Convention createConvention(Convention convention) {
        return conventionRepository.save(convention);
    }

    public Optional<Convention> updateConvention(Long id, Convention updatedConvention) {
        return conventionRepository.findById(id).map(convention -> {
            convention.setTitre(updatedConvention.getTitre());
            convention.setDateDebut(updatedConvention.getDateDebut());
            convention.setDateFin(updatedConvention.getDateFin());
            convention.setLieu(updatedConvention.getLieu());
            convention.setMaxParticipants(updatedConvention.getMaxParticipants());
            return conventionRepository.save(convention);
        });
    }

    public boolean deleteConvention(Long id) {
        if (conventionRepository.existsById(id)) {
            conventionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Convention> getAllConventions() {
        return conventionRepository.findAll();
    }

    public Optional<Convention> getConventionById(Long id) {
        return conventionRepository.findById(id);
    }
}
