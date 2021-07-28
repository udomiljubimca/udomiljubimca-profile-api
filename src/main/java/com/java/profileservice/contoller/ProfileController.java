package com.java.profileservice.contoller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.dto.FilterDto;
import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.dto.ProfileSearchDto;
import com.java.profileservice.exceptions.EntityNotExistsException;
import com.java.profileservice.model.Profile;
import com.java.profileservice.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.rmi.AccessException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

/**
 * Profile controller for Adopt a pet project
 * Acceptance criteria:
 * 1) save profile
 * 2) get profile by id
 * 3) get all profiles
 * 4) get all profiles by type id
 * 5) get all profiles by city id
 * 6) initial search of profile
 * 7) filter profile
 * 8) delete profile by id
 * 9) update profile
 * 10) get last eight saved profiles
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
            @RequestParam(value = "json") String json,
            Principal principal)
            throws Exception {

        ProfileDto profileDto;

        if (Objects.isNull(multipartFiles)) {
            LOG.error("Image is not present!");
            throw new Exception("Images are not preset");
        }
        if (Objects.isNull(json)) {
            throw new Exception("Json are not preset");
        }

        try {
            LOG.info("Start with mapping dto into object ProfileDto.");
            profileDto = new ObjectMapper().readValue(json, ProfileDto.class);
            profileDto.setUserName(principal.getName());
            LOG.info("End with mapping dto into object ProfileDto.");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        LOG.info("Saving and return profile.");
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
        LOG.info("Getting profile from DB {}", "id: " + profile.getId() + ", profile name: " + profile.getProfileName());
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
        LOG.info("Getting all profiles.");
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
        LOG.info("Getting all profiles by typeId.");
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
        LOG.info("Getting all profiles by cityId.");
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
        LOG.info("Started initial search by city and type ids.");
        if (profileSearchDto == null
                || profileSearchDto.getCityId() == 0
                || profileSearchDto.getTypeId() == 0) {
            LOG.error("Some condition didn't passed.");
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

        LOG.info("Started filtering method.");
        if (Objects.isNull(filterDto)) {
            throw new Exception("Bad request");
        }

        List<Profile> list =
                profileService.filterProfile(filterDto.getCityId(), filterDto.getTypeId(), filterDto.getGenderIds(),
                        filterDto.getAgeIds(), filterDto.getSizeIds());
        LOG.info("Filtering passed successfully.");
        if (list.size() == 0) {
            return new ApiResponse("No results based on entered filters.");
        }
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
    public ApiResponse deleteById(@PathVariable(name = "id") Long id, Principal principal) throws Exception {
        LOG.info("Deleting profile by id {}", id);
        if (Objects.isNull(principal)) {
            throw new Exception("Authorization error, you have to be sign in first!");
        }
        String userName = principal.getName();
        profileService.deleteById(id, userName);
        LOG.info("Profile deleted.");
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
                                     @RequestBody ProfileDto profileDto,
                                     Principal principal)
            throws Exception {

        LOG.info("Started update profile endpoint.");

        if (Objects.isNull(profileDto)) {
            throw new Exception("Bad request!");
        }

        Profile profileForCheck = Optional.ofNullable(profileService.getProfileById(id))
                .orElseThrow(() -> new EntityNotExistsException("Profile with id: " + id + " does not exist."));

        if (!principal.getName().equalsIgnoreCase(profileForCheck.getUserName())) {
            throw new AccessException("Access denied! You are not able to change this profile!");
        }
        profileDto.setUserName(principal.getName());
        Profile profile = profileService.updateProfile(id, profileDto);
        LOG.info("Profile updated successfully.");
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
        LOG.info("Getting eight profiles for home page.");
        List<Profile> list = profileService.getLastEightProfiles();
        return new ApiResponse(list);

    }


}
