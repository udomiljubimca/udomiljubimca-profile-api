package com.java.profileservice.repository;

import com.java.profileservice.model.Age;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgeRepository extends JpaRepository<Age,Long>{

    @Query("select id from Age")
    List<Long> getAllIds();

}
