package com.java.profileservice.service;

import com.java.profileservice.model.Health;

import java.util.List;

public interface HealthService {

    void save(Health health);

    void deleteAll();

    void saveAll(List<Health> healthList);
}
