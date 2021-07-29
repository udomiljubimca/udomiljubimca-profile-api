package com.java.profileservice.repository;

import com.java.profileservice.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findAllByTypeId(Long typeId);

    @Query(nativeQuery = true, value = "select * from profile order by date desc limit 8")
    List<Profile> getLastEightProfiles();

    Page<Profile> findByCityId(Long cityId, Pageable pageable);

    @Query("select p from Profile p where p.city.id = :cityId and p.type.id = :typeId")
    Page<Profile> initialSearch(Long cityId, Long typeId, Pageable pageable);

    @Query("select p from Profile p where p.city.id = :cityId and p.type.id = :typeId and p.gender.id in (:genderIds) and p.age.id in (:ageIds) and p.size.id in (:sizeIds)")
    Page<Profile> filterProfiles(Long cityId,Long typeId, List<Long> genderIds, List<Long> ageIds, List<Long> sizeIds, Pageable pageable);
}
