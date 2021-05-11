package com.java.profileservice.repository;

import com.java.profileservice.model.Nature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NatureRepository extends JpaRepository<Nature, Long> {
}
