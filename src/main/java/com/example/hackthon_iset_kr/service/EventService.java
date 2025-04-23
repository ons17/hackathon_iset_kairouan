package com.example.hackthon_iset_kr.service;

import com.example.hackthon_iset_kr.model.Event;
import com.example.hackthon_iset_kr.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event saveEvent(Event event) {
        // Vérification que la date de fin est bien après la date de début
        if (event.getDateFin() != null && event.getDateDebut() != null) {
            if (event.getDateFin().isBefore(event.getDateDebut())) {
                throw new IllegalArgumentException("La date de fin doit être après la date de début.");
            }
        }

        // Sauvegarde de l'événement si la validation est réussie
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
