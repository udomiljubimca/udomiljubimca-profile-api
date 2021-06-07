package com.java.profileservice.service.impl;

import com.java.profileservice.dto.UserInfoDto;
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

        Optional<UserInfo> userInfo = Optional.of(new UserInfo());

        mapFromUserInfoDto(userInfoDto, userInfo);

        userInfoRepository.save(userInfo.get());

        return  userInfo.get();

    }

    private void mapFromUserInfoDto(UserInfoDto userInfoDto, Optional<UserInfo> userInfo) {

        userInfo.get().setUserId(userInfoDto.getUserId());

        Optional<Profile> profile = profileRepository.findById(userInfoDto.getProfileId());

        userInfo.get().setProfile(profile.get());

    }
}
