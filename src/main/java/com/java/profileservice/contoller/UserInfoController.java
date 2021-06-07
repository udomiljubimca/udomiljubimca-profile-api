package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.dto.UserInfoDto;
import com.java.profileservice.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping(value = "/likes")
    public ApiResponse saveLikes(@RequestBody UserInfoDto userInfoDto) {
        return new ApiResponse(userInfoService.save(userInfoDto));
    }
}
