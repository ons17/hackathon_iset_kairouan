package com.example.hackthon_iset_kr.service;

import com.example.hackthon_iset_kr.model.Activity;
import com.example.hackthon_iset_kr.model.Adherent;
import com.example.hackthon_iset_kr.model.Responsable;
import com.example.hackthon_iset_kr.repository.ActivityRepository;
import com.example.hackthon_iset_kr.repository.AdherentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ResponsableService responsableService;
    private final AdherentRep adherentRepository;

    @Autowired
    public ActivityService(
            ActivityRepository activityRepository,
            ResponsableService responsableService,
            AdherentRep adherentRepository) {
        this.activityRepository = activityRepository;
        this.responsableService = responsableService;
        this.adherentRepository = adherentRepository;
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    @Transactional
    public Activity saveActivity(Activity activity) {
        if (activity.getAdherentId() != null) {
            Optional<Adherent> adherentOptional = adherentRepository.findById(activity.getAdherentId().intValue());
            if (adherentOptional.isPresent()) {
                Adherent adherent = adherentOptional.get();
                Optional<Responsable> existingResponsable = responsableService.getResponsableByAdherentId(activity.getAdherentId());

                Responsable responsable;
                if (existingResponsable.isPresent()) {
                    responsable = existingResponsable.get();
                } else {
                    String nomResponsable = adherent.getFirstName() + " " + adherent.getLastName();
                    responsable = responsableService.createResponsable(activity.getAdherentId(), nomResponsable);
                }

                activity.setResponsable(responsable);
            }
        }

        return activityRepository.save(activity);
    }

    public Activity updateActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return activityRepository.existsById(id);
    }
}
