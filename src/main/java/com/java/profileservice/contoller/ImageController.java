package com.java.profileservice.contoller;


import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @DeleteMapping("/{id}")
    public ApiResponse deleteImage(@PathVariable(name = "id") Long id) throws Exception {

        imageService.deleteImageById(id);
        return new ApiResponse("Image deleted!");
    }

    @PostMapping("/add")
    public ApiResponse saveImage(@RequestParam(value = "files", required = true) MultipartFile[] multipartFiles,
                                 @RequestParam(value = "profileId", required = true) Long profileId){
        imageService.uploadImages(multipartFiles, profileId);

        return new ApiResponse("Images saved.");
    }
}
