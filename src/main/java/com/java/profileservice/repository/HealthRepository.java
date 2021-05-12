package com.java.profileservice.repository;

import com.java.profileservice.model.Health;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository extends JpaRepository<Health, Long> {
}
