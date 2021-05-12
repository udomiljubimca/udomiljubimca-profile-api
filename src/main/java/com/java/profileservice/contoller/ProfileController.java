package com.java.profileservice.contoller;


import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Profile;
import com.java.profileservice.service.impl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    public ProfileServiceImpl profileService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse saveProfile(@RequestBody Profile profile){

        profileService.saveProfile(profile);

        return new ApiResponse(profile);
    }


}
