package com.java.profileservice.contoller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.model.Image;
import com.java.profileservice.model.Profile;
import com.java.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    public ProfileService profileService;

    @PostMapping(value = "/save", consumes = {MULTIPART_FORM_DATA_VALUE,
                                                    APPLICATION_JSON_VALUE})
    public ApiResponse saveProfile(
            @RequestParam(value = "files", required = false) MultipartFile[] multipartFiles,
            @RequestParam(value = "json") String json)
            throws Exception {

        ProfileDto profileDto;

        if (multipartFiles == null) {
            throw new Exception("Images are not preset");
        }
        if (json == null) {
            throw new Exception("Json are not preset");
        }

        try {
            profileDto = new ObjectMapper().readValue(json, ProfileDto.class);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        return new ApiResponse(profileService.saveProfile(profileDto, multipartFiles));
    }

    @GetMapping("/{id}")
    public ApiResponse getProfileById(@PathVariable(name = "id") Long id) {
        Profile profile = profileService.getProfileById(id);
        return new ApiResponse(profile);
    }

    @GetMapping("/all")
    public ApiResponse allProfiles() {
        List<Profile> allProfiles = profileService.getAllProfiles();
        return new ApiResponse(allProfiles);
    }


}
