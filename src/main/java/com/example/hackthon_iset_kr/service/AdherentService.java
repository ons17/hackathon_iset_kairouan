package com.example.hackthon_iset_kr.service;
import com.example.hackthon_iset_kr.model.Adherent;
import com.example.hackthon_iset_kr.repository.AdherentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdherentService {

        @Autowired
        private AdherentRep adherentRepository;

        public List<Adherent> getAllAdherents() {
            return adherentRepository.findAll();
        }

        public Optional<Adherent> getAdherentById(Integer id) {
            return adherentRepository.findById(id);
        }

        public Optional<Adherent> getAdherentByEmail(String email) {
            return adherentRepository.findByEmail(email);
        }

        public Adherent saveAdherent(Adherent adherent) {
            return adherentRepository.save(adherent);
        }


        public boolean existsByEmail(String email) {
            return adherentRepository.existsByEmail(email);
        }


        public Adherent updateAdherent(Integer id, Adherent adherentDetails) {
            Adherent adherent = adherentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Adherent not found with id: " + id));

            adherent.setFirstName(adherentDetails.getFirstName());
            adherent.setLastName(adherentDetails.getLastName());
            adherent.setEmail(adherentDetails.getEmail());
            adherent.setPhone(adherentDetails.getPhone());

            // Only update password if it's provided
            if (adherentDetails.getPassword() != null && !adherentDetails.getPassword().isEmpty()) {
                adherent.setPassword(adherentDetails.getPassword());
            }

            return adherentRepository.save(adherent);
        }

    public Optional<Adherent> authenticate(String email, String password) {
        return adherentRepository.findByEmailAndPassword(email, password);
    }
}

