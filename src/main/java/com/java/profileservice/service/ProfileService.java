package com.java.profileservice.service;

import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.model.Profile;
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

    List<Profile> getAllByCityId(Long cityId, int page);

    Profile updateProfile(Long id, ProfileDto profileDto) throws Exception;

    List<Profile> profileSearch(long cityId, long typeId, int page);

    List<Profile> filterProfile(Long cityId, Long typeId, List<Long> genderIds, List<Long> ageIds, List<Long> sizeIds);

    void saveProfile(Profile profile);

    List<Profile> getLastEightProfiles();

}
