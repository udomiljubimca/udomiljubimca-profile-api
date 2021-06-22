package com.java.profileservice.contoller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.dto.FilterDto;
import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.dto.ProfileSearchDto;
import com.java.profileservice.model.Profile;
import com.java.profileservice.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

/**
 * Profile controller for Adopt a pet project
 * Acceptance criterias:
 * 1) save profile
 * 2) get profile by id
 * 3) get all profiles
 * 4) get all profiles by type id
 * 5) get all profiles by city id
 * 6) initial search of profile
 * 7) filter profile
 * 8) delete profile by id
 * 9) update profile
 * 10) get last eigth saved profiles
 */
@RestController
@RequestMapping(value = "/profile")
@Api(tags = {"Profile controller"})
public class ProfileController {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    public ProfileService profileService;


    /**
     * 1) Save profile route uses to save profile
     *
     * @param multipartFiles, json
     * @return Profile
     */
    @PostMapping(value = "/save", consumes =
            {MULTIPART_FORM_DATA_VALUE, APPLICATION_JSON_VALUE})
    @ApiOperation(
            notes = "${operation1.description}",
            value = "${operation1.value}",
            responseContainer = "${operation1.responseContainer}",
            response = Profile.class
    )
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

    /**
     * 2) Get profile by id route uses to get profile by id
     *
     * @param id
     * @return Profile
     */
    @GetMapping("/{id}")
    @ApiOperation(
            notes = "${operation2.description}",
            value = "${operation2.value}",
            responseContainer = "${operation1.responseContainer}",
            response = Profile.class
    )
    public ApiResponse getProfileById(@PathVariable(name = "id") Long id) {
        Profile profile = profileService.getProfileById(id);
        return new ApiResponse(profile);
    }

    /**
     * 3) Get all profiles route uses to get all profiles
     *
     * @return List<Profile>
     */
    @GetMapping("/all")
    @ApiOperation(
            notes = "${operation3.description}",
            value = "${operation3.value}"

    )
    public ApiResponse allProfiles() {
        List<Profile> allProfiles = profileService.getAllProfiles();
        return new ApiResponse(allProfiles);
    }

    /**
     * 4) Get all profiles by id route uses to get all profiles by type id
     *
     * @param id
     * @return List<Profile>
     */
    @GetMapping("/type/{id}")
    @ApiOperation(
            notes = "${operation4.description}",
            value = "${operation4.value}"
    )
    public ApiResponse getAllByTypeId(@PathVariable(name = "id") Long id) {
        List<Profile> list = profileService.getAllByTypeId(id);
        return new ApiResponse(list);
    }

    /**
     * 5) Get all by city id route uses to get all profiles by city id
     *
     * @param id, page
     * @return List<Profile>
     */
    @GetMapping("/city/{id}")
    @ApiOperation(
            notes = "${operation5.description}",
            value = "${operation5.value}"
    )
    public ApiResponse getAllByCityId(@PathVariable(name = "id") Long id,
                                      @RequestParam(name = "page") int page) {
        List<Profile> list = profileService.getAllByCityId(id, page);
        return new ApiResponse(list);
    }

    /**
     * 6) Search profiles route uses to search profiles using profileSearchDto
     *
     * @param profileSearchDto, page
     * @return List<Profile>
     */
    @GetMapping(value = "/initialSearch")
    @ApiOperation(
            notes = "${operation6.description}",
            value = "${operation6.value}"
    )
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

    /**
     * 7) Filter profile route uses to filter profiles using filterDto
     *
     * @param filterDto
     * @return List<Profile>
     */
    @GetMapping(value = "/filter")
    @ApiOperation(
            notes = "${operation7.description}",
            value = "${operation7.value}"
    )
    public ApiResponse filter(@RequestBody FilterDto filterDto) throws Exception {
        if (filterDto == null) {
            throw new Exception("Bad request");
        }

        List<Profile> list =
                profileService.filterProfile(filterDto.getCityId(), filterDto.getTypeId(), filterDto.getGenderIds(),
                        filterDto.getAgeIds(), filterDto.getSizeIds());

        return new ApiResponse(list);
    }

    /**
     * 8) Delete profile route uses to delete using id
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    @ApiOperation(
            notes = "${operation8.description}",
            value = "${operation8.value}"
    )
    public ApiResponse deleteById(@PathVariable(name = "id") Long id) throws Exception {
        profileService.deleteById(id);
        return new ApiResponse("Profile is successfully deleted!");
    }

    /**
     * 9) Update profile route uses to update profile
     *
     * @param id, profileDto
     * @return Profile
     */
    @PutMapping("/{id}")
    @ApiOperation(
            notes = "${operation9.description}",
            value = "${operation9.value}",
            responseContainer = "${operation9.responseContainer}",
            response = Profile.class
    )
    public ApiResponse updateProfile(@PathVariable(name = "id") Long id,
                                     @RequestBody ProfileDto profileDto)
            throws Exception {
        if (profileDto == null) {
            throw new Exception("Bad request!");
        }
        Profile profile = profileService.updateProfile(id, profileDto);
        return new ApiResponse(profile);
    }
    /**
     * 10) Get last eight profiles route uses to get last eight saved profiles sorted by upload date
     *
     * @return List<Profile>
     */

    @GetMapping("/home")
    @ApiOperation(
            notes = "${operation10.description}",
            value = "${operation10.value}"
    )
    public ApiResponse getLastEightProfiles() {
        List<Profile> list = profileService.getLastEightProfiles();
        return new ApiResponse(list);

    }


}
