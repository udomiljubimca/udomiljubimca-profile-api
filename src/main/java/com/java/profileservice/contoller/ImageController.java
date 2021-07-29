package com.java.profileservice.contoller;


import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.exceptions.EntityNotExistsException;
import com.java.profileservice.model.Image;
import com.java.profileservice.model.Profile;
import com.java.profileservice.service.ImageService;
import com.java.profileservice.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

/**
 * Image controller for Adopt a pet project
 * Acceptance criterias:
 * 1) delete image
 * 2) save image
 */
@RestController
@RequestMapping(value = "/image")
@Api(tags = {"Image controller"})
public class ImageController {

    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);


    @Autowired
    private ImageService imageService;

    @Autowired
    private ProfileService profileService;

    /**
     * 1) Delete image route uses to delete image
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    @ApiOperation(
            notes = "${operation15.description}",
            value = "${operation15.value}"
    )
    public ApiResponse deleteImage(@PathVariable(name = "id") Long id, Principal principal) throws Exception {

        if (Objects.isNull(principal)) {
            throw new Exception("Authorization error, you have to be sign in first!");
        }
        Image image = imageService.getImageById(id);
        if(!principal.getName().equalsIgnoreCase(image.getProfile().getUserName())){
            throw new Exception("Access denied, you cannot delete this image!");
        }

        LOG.info("Starting deleting image.");
        imageService.deleteImageById(id);
        LOG.info("Image has deleted.");
        return new ApiResponse("Image deleted!");
    }

    @PostMapping("/add")
    @ApiOperation(
            notes = "${operation16.description}",
            value = "${operation16.value}"
    )

    /**
     * 2) Save image route uses to save image
     *
     * @param multipartFiles, profileId
     */
    public ApiResponse saveImage(@RequestParam(value = "files", required = true) MultipartFile[] multipartFiles,
                                 @RequestParam(value = "profileId", required = true) Long profileId,
                                 Principal principal) throws Exception {

        if (Objects.isNull(principal)) {
            throw new Exception("Authorization error, you have to be sign in first!");
        }
        Profile profile = Optional.ofNullable(profileService.getProfileById(profileId)).orElseThrow(
                () -> new EntityNotExistsException("Profile with id: " + profileId + " does not exists."));
        if(!principal.getName().equalsIgnoreCase(profile.getUserName())){
            throw new Exception("You cannot add image for this profile!");
        }
        LOG.info("Starting saving image.");
        imageService.uploadImages(multipartFiles, profileId);
        LOG.info("Image has saved.");
        return new ApiResponse("Images saved.");
    }
}
