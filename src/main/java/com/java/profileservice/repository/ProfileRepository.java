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

    @Query("select p from Profile p where (p.city.id = :cityId or p.city.id <> :cityId) and (p.gender.id in (:genderIds) or p.gender.id not in (:genderIds)) and (p.age.id in (:ageIds) or p.age.id not in (:ageIds)) and (p.size.id in (:sizeIds) or p.size.id not in (:sizeIds))")
    List<Profile> filterProfile(Long cityId, List<Long> genderIds, List<Long> ageIds, List<Long> sizeIds);
}
