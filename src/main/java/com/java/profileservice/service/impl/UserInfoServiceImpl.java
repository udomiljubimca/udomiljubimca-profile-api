package com.java.profileservice.service.impl;

import com.java.profileservice.dto.UserInfoDto;
import com.java.profileservice.exceptions.ExistsEntityException;
import com.java.profileservice.model.Profile;
import com.java.profileservice.model.UserInfo;
import com.java.profileservice.repository.ProfileRepository;
import com.java.profileservice.repository.UserInfoRepository;
import com.java.profileservice.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public UserInfo save(UserInfoDto userInfoDto) {

        UserInfo userInfo = mapFromUserInfoDto(userInfoDto);

        userInfoRepository.save(userInfo);

        return userInfo;

    }

    private UserInfo mapFromUserInfoDto(UserInfoDto userInfoDto) {

        UserInfo userInfo = new UserInfo();

        Optional<Profile> profile = profileRepository.findById(userInfoDto.getProfileId());

        if (profile.isPresent()) {
            userInfo.setProfile(profile.get());
            userInfo.setUserId(userInfoDto.getUserId());
        } else {
            throw new ExistsEntityException("Profile with id " + userInfoDto.getUserId() + " does not exist.");
        }

        return userInfo;
    }
}
