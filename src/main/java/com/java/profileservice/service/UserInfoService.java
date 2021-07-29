package com.java.profileservice.service;

import com.java.profileservice.dto.UserInfoDto;
import com.java.profileservice.model.UserInfo;

public interface UserInfoService {

    UserInfo save(UserInfoDto userInfoDto);
}
