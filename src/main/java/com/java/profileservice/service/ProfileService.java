package com.java.profileservice.service;

import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.model.Profile;

public interface ProfileService {

    Profile saveProfile(ProfileDto profileDto) throws Exception;
}
