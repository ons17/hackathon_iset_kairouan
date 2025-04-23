package com.example.hackthon_iset_kr.controller;

import com.example.hackthon_iset_kr.model.Convention;
import com.example.hackthon_iset_kr.service.ConventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conventions")
@CrossOrigin(origins = "*")
public class ConventionController {
    @Autowired
    private ConventionService conventionService;

    @PostMapping("/add")
    public ResponseEntity<Convention> createConvention(@RequestBody Convention convention) {
        return ResponseEntity.ok(conventionService.createConvention(convention));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Convention> updateConvention(@PathVariable Long id, @RequestBody Convention convention) {
        Optional<Convention> updated = conventionService.updateConvention(id, convention);
        return updated.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteConvention(@PathVariable Long id) {
        boolean deleted = conventionService.deleteConvention(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Convention> getAllConventions() {
        return conventionService.getAllConventions();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Convention> getConventionById(@PathVariable Long id) {
        Optional<Convention> convention = conventionService.getConventionById(id);
        return convention.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}