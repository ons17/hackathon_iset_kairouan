package com.example.hackthon_iset_kr.repository;

import com.example.hackthon_iset_kr.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);
}
