package com.example.hackthon_iset_kr.controller;

import com.example.hackthon_iset_kr.model.Activity;
import com.example.hackthon_iset_kr.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Optional<Activity> activity = activityService.getActivityById(id);
        return activity.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity savedActivity = activityService.saveActivity(activity);
        return new ResponseEntity<>(savedActivity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        if (!activityService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        activity.setId(id);
        Activity updatedActivity = activityService.updateActivity(activity);
        return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        if (!activityService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        activityService.deleteActivity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


