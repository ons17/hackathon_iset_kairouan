package com.example.hackthon_iset_kr.controller;

import com.example.hackthon_iset_kr.model.Responsable;
import com.example.hackthon_iset_kr.service.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/responsables")
public class ResponsableController {

    private final ResponsableService responsableService;

    @Autowired
    public ResponsableController(ResponsableService responsableService) {
        this.responsableService = responsableService;
    }

    @GetMapping
    public ResponseEntity<List<Responsable>> getAllResponsables() {
        List<Responsable> responsables = responsableService.getAllResponsables();
        return new ResponseEntity<>(responsables, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsable> getResponsableById(@PathVariable Integer id) {
        Optional<Responsable> responsable = responsableService.getResponsableById(id);
        return responsable.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/adherent/{adherentId}")
    public ResponseEntity<Responsable> getResponsableByAdherentId(@PathVariable Long adherentId) {
        Optional<Responsable> responsable = responsableService.getResponsableByAdherentId(adherentId);
        return responsable.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Responsable> createResponsable(@RequestBody Map<String, Object> request) {
        try {
            Long adherentId = Long.valueOf(request.get("adherentId").toString());
            String nomResponsable = (String) request.get("nomResponsable");

            Responsable savedResponsable = responsableService.createResponsable(adherentId, nomResponsable);
            return new ResponseEntity<>(savedResponsable, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsable> updateResponsable(@PathVariable Integer id, @RequestBody Responsable responsable) {
        if (!responsableService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        responsable.setId(id);
        Responsable updatedResponsable = responsableService.updateResponsable(responsable);
        return new ResponseEntity<>(updatedResponsable, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponsable(@PathVariable Integer id) {
        if (!responsableService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        responsableService.deleteResponsable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
