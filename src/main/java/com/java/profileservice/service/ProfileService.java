package com.java.profileservice.service;

import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.model.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileService {

    Profile saveProfile(ProfileDto profileDto, MultipartFile[] multipartFiles) throws Exception;

    void deleteAll();

    String uploadImages(MultipartFile multipartFile) throws IOException;

    Profile getProfileById(Long id);

    }
