package com.java.profileservice.repository;

import com.java.profileservice.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {

}
