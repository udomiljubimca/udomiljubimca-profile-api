package com.java.profileservice.contoller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.dto.FilterDto;
import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.dto.ProfileSearchDto;
import com.java.profileservice.model.Profile;
import com.java.profileservice.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    public ProfileService profileService;

    @PostMapping(value = "/save", consumes =
            {MULTIPART_FORM_DATA_VALUE, APPLICATION_JSON_VALUE})
    public ApiResponse saveProfile(
            @RequestParam(value = "files", required = false) MultipartFile[] multipartFiles,
            @RequestParam(value = "json") String json)
            throws Exception {

        ProfileDto profileDto;

        if (multipartFiles == null) {
            LOG.error("Image is not present!");
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

    @GetMapping("/type/{id}")
    public ApiResponse getAllByTypeId(@PathVariable(name = "id") Long id) {
        List<Profile> list = profileService.getAllByTypeId(id);
        return new ApiResponse(list);
    }

    @GetMapping("/city/{id}")
    public ApiResponse getAllByCityId(@PathVariable(name = "id") Long id,
                                      @RequestParam(name = "page") int page) {
        List<Profile> list = profileService.getAllByCityId(id, page);
        return new ApiResponse(list);
    }

    @GetMapping(value = "/initialSearch")
    public ApiResponse search(@RequestBody ProfileSearchDto profileSearchDto,
                              @RequestParam(name = "page") int page) throws Exception {

        if (profileSearchDto == null
                || profileSearchDto.getCityId() == 0
                || profileSearchDto.getTypeId() == 0) {
            throw new Exception("Bad request!");
        }
        List<Profile> list =
                profileService.profileSearch(profileSearchDto.getCityId(), profileSearchDto.getTypeId(), page);
        return new ApiResponse(list);
    }

    @GetMapping(value = "/filter")
    public ApiResponse filter(@RequestBody FilterDto filterDto) throws Exception {
        if (filterDto == null) {
            throw new Exception("Bad request");
        }

        List<Profile> list =
                profileService.filterProfile(filterDto.getCityId(), filterDto.getGenderIds(),
                        filterDto.getAgeIds(), filterDto.getSizeIds());

        return new ApiResponse(list);
    }


    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable(name = "id") Long id) throws Exception {
        profileService.deleteById(id);
        return new ApiResponse("Profile is successfully deleted!");
    }

    @PutMapping("/{id}")
    public ApiResponse updateProfile(@PathVariable(name = "id") Long id,
                                     @RequestParam(value = "files", required = false) MultipartFile[] multipartFiles,
                                     @RequestParam(value = "json", required = false) String json)
            throws Exception {
        if (multipartFiles == null) {
            multipartFiles = new MultipartFile[0];
        }
        if (json == null || json.equalsIgnoreCase("")) {
            json = "empty";
        }
        Profile profile = profileService.updateProfile(id, multipartFiles, json);
        return new ApiResponse(profile);
    }


}
