package com.java.profileservice.repository;

import com.java.profileservice.model.Health;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HealthRepository extends JpaRepository<Health, Long> {

    @Query("select h from Health h where h.id IN (:ids)")
    List<Health> getHealthByIds(List<Long> ids);

    @Query("select id from Health")
    List<Long> getAllIds();

}
