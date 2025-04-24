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

    public Convention saveConvention(Convention convention) {
        return conventionRepository.save(convention);
    }

    public List<Convention> getAllConventions() {
        return conventionRepository.findAll();
    }

    public Convention getConventionById(Long id) {
        return conventionRepository.findById(id).orElse(null);
    }

    public Convention updateConvention(Long id, Convention updated) {
        Optional<Convention> optional = conventionRepository.findById(id);
        if (optional.isPresent()) {
            Convention existing = optional.get();
            existing.setName(updated.getName());
            existing.setDescription(updated.getDescription());
            existing.setDate(updated.getDate());
            existing.setAdherents(updated.getAdherents());
            existing.setActivities(updated.getActivities());
            return conventionRepository.save(existing);
        }
        return null;
    }

    public void deleteConvention(Long id) {
        conventionRepository.deleteById(id);
    }
}
