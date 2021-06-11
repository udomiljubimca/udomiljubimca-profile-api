package com.java.profileservice.contoller;


import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private ImageService imageService;

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
    public ApiResponse deleteImage(@PathVariable(name = "id") Long id) throws Exception {

        imageService.deleteImageById(id);
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
                                 @RequestParam(value = "profileId", required = true) Long profileId){
        imageService.uploadImages(multipartFiles, profileId);

        return new ApiResponse("Images saved.");
    }
}
