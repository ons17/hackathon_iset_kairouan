package com.example.hackthon_iset_kr.controller;
import com.example.hackthon_iset_kr.model.Adherent;
import com.example.hackthon_iset_kr.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/adherents")
@CrossOrigin(origins = "*")
public class AdherentController {

        @Autowired
        private AdherentService adherentService;


        @GetMapping
        public ResponseEntity<List<Adherent>> getAllAdherents() {
            List<Adherent> adherents = adherentService.getAllAdherents();
            return new ResponseEntity<>(adherents, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Adherent> getAdherentById(@PathVariable Integer id) {
            Optional<Adherent> adherent = adherentService.getAdherentById(id);
            return adherent.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

    @PostMapping("/create")
    public ResponseEntity<?> createAdherent(@RequestBody Adherent adherent) {
        return new ResponseEntity<>("Access denied: only admin can create adherents", HttpStatus.FORBIDDEN);
    }


    @PutMapping("/{id}")
        public ResponseEntity<?> updateAdherent(@PathVariable Integer id, @RequestBody Adherent adherentDetails) {
            try {
                Adherent updatedAdherent = adherentService.updateAdherent(id, adherentDetails);
                return new ResponseEntity<>(updatedAdherent, HttpStatus.OK);
            } catch (RuntimeException e) {
                Map<String, String> response = new HashMap<>();
                response.put("error", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }


        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
            String email = credentials.get("email");
            String password = credentials.get("password");

            Optional<Adherent> adherent = adherentService.authenticate(email, password);

            if (adherent.isPresent()) {
                return new ResponseEntity<>(adherent.get(), HttpStatus.OK);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Invalid email or password");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        }
    }

