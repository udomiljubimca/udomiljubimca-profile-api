package com.java.profileservice.repository;

import com.java.profileservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    //Filtering by type
    List<Profile> findAllByTypeId(Long typeId);
}
