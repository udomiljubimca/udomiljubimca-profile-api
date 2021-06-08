package com.java.profileservice.service;

import com.java.profileservice.model.Image;
import com.java.profileservice.model.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    void saveAll(List<Image> imageList);

    List<Image> getAll();

    void deleteImageById(Long imageId) throws Exception;

    void deleteImagesByIds(List<Long> ids);

    void uploadImages(MultipartFile[] multipartFiles, Long profileId);

    List<Image> saveAndReturnImages(MultipartFile[] multipartFiles, Optional<Profile> profile);

}
