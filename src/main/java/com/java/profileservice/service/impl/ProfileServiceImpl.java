package com.java.profileservice.service.impl;

import com.java.profileservice.model.Profile;
import com.java.profileservice.repository.ProfileRepository;
import com.java.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public void saveProfile(Profile profile){

//        City city = cityRepository.getOne(profileDto.getCityId());
//
//        profile.setCity(city);

        profileRepository.save(profile);
    }
}
