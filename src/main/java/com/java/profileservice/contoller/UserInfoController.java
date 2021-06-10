package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.dto.UserInfoDto;
import com.java.profileservice.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@Api(tags = {"User info controller"})
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping(value = "/like")
    @ApiOperation(
            notes = "${operation20.description}",
            value = "${operation20.value}"
    )
    public ApiResponse saveLikes(@RequestBody UserInfoDto userInfoDto) throws Exception {

        if (userInfoDto == null
                || userInfoDto.getUserId() == null
                || userInfoDto.getProfileId() == null) {
            throw new Exception("Bad request!");
        }

        return new ApiResponse(userInfoService.save(userInfoDto));
    }
}
