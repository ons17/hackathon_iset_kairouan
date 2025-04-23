package com.example.hackthon_iset_kr.repository;

import com.example.hackthon_iset_kr.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
