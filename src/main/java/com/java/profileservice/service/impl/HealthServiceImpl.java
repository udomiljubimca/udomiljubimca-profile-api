package com.java.profileservice.service.impl;

import com.java.profileservice.model.Health;
import com.java.profileservice.repository.HealthRepository;
import com.java.profileservice.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    private HealthRepository healthRepository;

    /**
     * Save health into database
     */
    @Override
    public void save(Health health) {
        healthRepository.save(health);
    }

    /**
     * Delete all ages from database
     */
    @Override
    public void deleteAll() {
        healthRepository.deleteAll();
    }

    /**
     * Save all healths into database
     *
     * @param healthList
     */
    @Override
    public void saveAll(List<Health> healthList) {
        healthRepository.saveAll(healthList);
    }

    /**
     * Get all genders from database
     *
     * List<Health>
     */
    @Override
    public List<Health> getAllHealths() {
        List<Health> healthList = healthRepository.findAll();
        return healthList;
    }
}
