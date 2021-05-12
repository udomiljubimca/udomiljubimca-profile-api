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

    @Override
    public void save(Health health) {
        healthRepository.save(health);
    }

    @Override
    public void deleteAll() {
        healthRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Health> healthList) {
        healthRepository.saveAll(healthList);
    }
}
