package com.java.profileservice.repository;

import com.java.profileservice.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenderRepository extends JpaRepository<Gender, Long> {


    @Query("select id from Gender")
    List<Long> getAllIds();
}
