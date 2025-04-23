package com.example.hackthon_iset_kr.service;

import com.example.hackthon_iset_kr.model.Admin;
import com.example.hackthon_iset_kr.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Admin> authenticate(String email, String rawPassword) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (passwordEncoder.matches(rawPassword, admin.getPassword())) {
                return Optional.of(admin);
            }
        }
        return Optional.empty();
    }

    public Admin register(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
    public Optional<Admin> updateAdmin(Integer id, Admin admin) {
        Optional<Admin> adminOpt = adminRepository.findById(id);
        if (adminOpt.isPresent()) {
            Admin existingAdmin = adminOpt.get();

            existingAdmin.setEmail(admin.getEmail());
            existingAdmin.setFirst_name(admin.getFirst_name());


            if (admin.getPassword() != null && !admin.getPassword().isEmpty()) {
                existingAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }

            return Optional.of(adminRepository.save(existingAdmin));
        }
        return Optional.empty();
    }
}
