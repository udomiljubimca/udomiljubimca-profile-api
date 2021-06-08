package com.java.profileservice.repository;

import com.java.profileservice.model.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    //Filtering by type
    List<Profile> findAllByTypeId(Long typeId);

    //Find by cityId
    List<Profile> findAllByCityId(Long cityId, Pageable pageable);

    @Query("select p from Profile p where p.city.id = :cityId and p.type.id = :typeId")
    List<Profile> searchProfile(Long cityId, Long typeId, Pageable pageable);

    @Query("select p from Profile p where p.city.id = :cityId and p.type.id = :typeId and p.gender.id in (:genderIds) and p.age.id in (:ageIds) and p.size.id in (:sizeIds)")
    List<Profile> filterProfile(Long cityId,Long typeId, List<Long> genderIds, List<Long> ageIds, List<Long> sizeIds);

    @Query(nativeQuery = true, value = "select * from profile order by date desc limit 8")
    List<Profile> getLastEightProfiles();
}
