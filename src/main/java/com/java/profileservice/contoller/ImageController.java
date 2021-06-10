package com.java.profileservice.contoller;


import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/image")
@Api(tags = {"Image controller"})
public class ImageController {

    @Autowired
    private ImageService imageService;

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
    public ApiResponse saveImage(@RequestParam(value = "files", required = true) MultipartFile[] multipartFiles,
                                 @RequestParam(value = "profileId", required = true) Long profileId){
        imageService.uploadImages(multipartFiles, profileId);

        return new ApiResponse("Images saved.");
    }
}
