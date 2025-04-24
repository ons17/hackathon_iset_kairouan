package com.example.hackthon_iset_kr.controller;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.hackthon_iset_kr.model.Admin;
import com.example.hackthon_iset_kr.model.Adherent;
import com.example.hackthon_iset_kr.service.AdminService;
import com.example.hackthon_iset_kr.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdherentService adherentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-adherent")
    public ResponseEntity<?> createAdherent(@RequestBody Adherent adherent) {
        if (adherentService.existsByEmail(adherent.getEmail())) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Email already in use");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        Adherent newAdherent = adherentService.saveAdherent(adherent);
        return new ResponseEntity<>(newAdherent, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestBody Admin admin) {
        Admin registeredAdmin = adminService.register(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Optional<Admin> admin = adminService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return admin.isPresent() ?
                ResponseEntity.ok("Login successful") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> update(@PathVariable("id") Integer id, @RequestBody Admin admin) {
        Optional<Admin> updatedAdmin = adminService.updateAdmin(id, admin);
        return updatedAdmin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public static class LoginRequest {
        private String email;
        private String password;

        // Getters & setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
