package com.java.profileservice.repository;

import com.java.profileservice.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Long> {

    @Query("select id from Size")
    List<Long> getAllIds();
}
