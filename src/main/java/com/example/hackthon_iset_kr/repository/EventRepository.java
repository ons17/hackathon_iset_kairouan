package com.example.hackthon_iset_kr.repository;

import com.example.hackthon_iset_kr.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
