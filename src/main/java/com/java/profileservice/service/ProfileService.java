package com.java.profileservice.service;

import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProfileService {

    Profile saveProfile(ProfileDto profileDto, MultipartFile[] multipartFiles) throws Exception;

    void deleteAll() throws Exception;

    void deleteById(Long id, String userName) throws Exception;

    Profile getProfileById(Long id);

    List<Profile> getAllProfiles();

    List<Profile> getAllByTypeId(Long typeId);

    Page<Profile> getAllByCityId(Long cityId, Pageable pageable);

    Profile updateProfile(Long id, ProfileDto profileDto) throws Exception;

    void saveProfile(Profile profile);

    List<Profile> getLastEightProfiles();

    Page<Profile> profileInitialSearch(long cityId, long typeId, Pageable pageable);

    Page<Profile> filterProfiles(Long cityId,Long typeId, List<Long> genderIds, List<Long> ageIds, List<Long> sizeIds, Pageable pageable);


}
