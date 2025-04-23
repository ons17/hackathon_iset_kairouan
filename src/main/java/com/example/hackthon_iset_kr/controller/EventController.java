package com.example.hackthon_iset_kr.controller;

import com.example.hackthon_iset_kr.model.Event;
import com.example.hackthon_iset_kr.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        try {
            Event createdEvent = eventService.saveEvent(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
        } catch (IllegalArgumentException ex) {
            // Utilisation d'un Map pour un message d'erreur JSON
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erreur : " + ex.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        return eventService.getEventById(id).map(event -> {
            event.setName(updatedEvent.getName());
            event.setDateDebut(updatedEvent.getDateDebut());
            event.setDateFin(updatedEvent.getDateFin());
            event.setMaxPlace(updatedEvent.getMaxPlace());
            event.setAdherants(updatedEvent.getAdherants());
            event.setActivity(updatedEvent.getActivity());
            return ResponseEntity.ok(eventService.saveEvent(event));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (eventService.getEventById(id).isPresent()) {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
