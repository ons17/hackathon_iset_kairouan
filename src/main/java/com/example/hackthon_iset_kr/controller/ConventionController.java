package com.example.hackthon_iset_kr.controller;

import com.example.hackthon_iset_kr.model.Convention;
import com.example.hackthon_iset_kr.service.ConventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conventions")
@CrossOrigin(origins = "*") // Optionnel : à adapter pour CORS
public class ConventionController {

    @Autowired
    private ConventionService conventionService;

    @PostMapping("/add")
    public Convention createConvention(@RequestBody Convention convention) {
        return conventionService.saveConvention(convention);
    }

    @GetMapping
    public List<Convention> getAllConventions() {
        return conventionService.getAllConventions();
    }

    @GetMapping("/{id}")
    public Convention getConventionById(@PathVariable Long id) {
        return conventionService.getConventionById(id);
    }

    @PutMapping("/{id}")
    public Convention updateConvention(@PathVariable Long id, @RequestBody Convention convention) {
        return conventionService.updateConvention(id, convention);
    }

    @DeleteMapping("/{id}")
    public void deleteConvention(@PathVariable Long id) {
        conventionService.deleteConvention(id);
    }
}
