package com.example.hackthon_iset_kr.service;

import com.example.hackthon_iset_kr.model.Adherent;
import com.example.hackthon_iset_kr.model.Responsable;
import com.example.hackthon_iset_kr.repository.AdherentRep;
import com.example.hackthon_iset_kr.repository.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsableService {

    private final ResponsableRepository responsableRepository;
    private final AdherentRep adherentRepository;

    @Autowired
    public ResponsableService(ResponsableRepository responsableRepository, AdherentRep adherentRepository) {
        this.responsableRepository = responsableRepository;
        this.adherentRepository = adherentRepository;
    }

    // 🔹 GET ALL
    public List<Responsable> getAllResponsables() {
        return responsableRepository.findAll();
    }

    // 🔹 GET BY ID
    public Optional<Responsable> getResponsableById(Integer id) {
        return responsableRepository.findById(id);
    }

    // 🔹 GET BY Adherent ID
    public Optional<Responsable> getResponsableByAdherentId(Long adherentId) {
        return responsableRepository.findByAdherentId(adherentId);
    }

    // 🔹 CREATE Responsable (avec vérification Adherent existant)
    public Responsable createResponsable(Long adherentId, String nomResponsable) {
        Optional<Adherent> adherentOptional = adherentRepository.findById(Math.toIntExact(adherentId));
        if (adherentOptional.isEmpty()) {
            throw new RuntimeException("Adherent with ID " + adherentId + " not found");
        }
        Responsable responsable = new Responsable();
        responsable.setAdherent(adherentOptional.get());
        responsable.setNomResponsable(nomResponsable);
        responsableRepository.save(responsable);

        return responsableRepository.save(responsable);
    }

    // 🔹 UPDATE
    public Responsable updateResponsable(Responsable responsable) {
        return responsableRepository.save(responsable);
    }

    // 🔹 DELETE
    public void deleteResponsable(Integer id) {
        responsableRepository.deleteById(id);
    }

    // 🔹 CHECK existence
    public boolean existsById(Integer id) {
        return responsableRepository.existsById(id);
    }
}
