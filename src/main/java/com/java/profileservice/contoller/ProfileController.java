package com.java.profileservice.contoller;


import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    public ProfileService profileService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse saveProfile(@RequestBody ProfileDto profileDto) throws Exception {

        return new ApiResponse(profileService.saveProfile(profileDto));
    }


}
