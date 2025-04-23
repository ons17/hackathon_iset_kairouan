package com.example.hackthon_iset_kr.controller;

import com.example.hackthon_iset_kr.model.Admin;
import com.example.hackthon_iset_kr.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestBody Admin admin) {
        Admin registeredAdmin = adminService.register(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Optional<Admin> admin = adminService.authenticate(email, password);
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
}
