package com.java.profileservice.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.java.profileservice.exceptions.EntityNotExistsException;
import com.java.profileservice.exceptions.ExistsEntityException;
import com.java.profileservice.model.Image;
import com.java.profileservice.model.Profile;
import com.java.profileservice.repository.ImageRepository;
import com.java.profileservice.service.ImageService;
import com.java.profileservice.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger LOG = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProfileService profileService;

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String cloudApiKey;

    @Value("${cloudinary.api_secret}")
    private String cloudApiSecret;

    @Override
    public void saveAll(List<Image> imageList) {
        imageRepository.saveAll(imageList);
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    /**
     * Delete image by id
     *
     * @param imageId - id form database
     */
    @Override
    public void deleteImageById(Long imageId) throws Exception {

        LOG.info("Starting deleting image by id {}", imageId);

        Optional<Image> image = imageRepository.findById(imageId);

        if (!image.isPresent()) {
            throw new EntityNotExistsException("Image with id: " + imageId + " does not exist.");
        }

        //first delete from Cloudinary
        try {
            LOG.info("Deleting form cloudinary.");
            deleteImageFromCloudinary(image.get().getImageLink());
            LOG.info("Deleted form cloudinary.");
        } catch (Exception e) {
            throw new Exception("Can not delete this image!");
        }
        //second delete from database
        imageRepository.deleteById(imageId);
        LOG.info("Deleted from database.");

    }

    /**
     * Delete images by list of ids
     *
     * @param ids - ids
     */
    @Override
    public void deleteImagesByIds(List<Long> ids) {
        imageRepository.deleteByIds(ids);
    }

    /**
     * Upload images, Method should upload images on Cloudinary for particular Profile.
     *
     * @param multipartFiles - images
     * @param profileId      - profileId
     */
    @Override
    public void uploadImages(MultipartFile[] multipartFiles, Long profileId) {

        Optional<Profile> profile = Optional.ofNullable(profileService.getProfileById(profileId));

        if (!profile.isPresent()) {
            throw new EntityNotExistsException("Profile with id: " + profile + " does not exist.");
        }
        if ((profile.get().getImages().size() + multipartFiles.length) > 3) {
            throw new ExistsEntityException("Maximum numbers of images is 3, please first delete some before add new.");
        }

        List<Image> images = new ArrayList<>();
        Arrays.asList(multipartFiles).stream()
                .forEach(multipartFile -> {
                    Image image = new Image();
                    try {
                        image.setImageLink(uploadOnCloudinary(multipartFile));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    image.setProfile(profile.get());
                    images.add(image);
                });

        List<Image> existImages = profile.get().getImages();
        existImages.addAll(images);
        profile.get().setImages(existImages);
        profileService.saveProfile(profile.get());
    }

    /**
     * Save and return images for initial save. So we grab images from request and save here.
     *
     * @param multipartFiles - images
     * @param profile        - profile
     * @return List<Image>
     */
    @Override
    public List<Image> saveAndReturnImages(MultipartFile[] multipartFiles, Optional<Profile> profile) {

        List<Image> images = new ArrayList<>();
        Arrays.asList(multipartFiles).stream().limit(3).forEach(multipartFile -> {
            Image image = new Image();
            try {
                image.setImageLink(uploadOnCloudinary(multipartFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setProfile(profile.get());
            images.add(image);

        });
        return images;
    }

    @Override
    public Image getImageById(Long id) {
        Optional<Image> image = Optional.ofNullable(imageRepository.findById(id).orElseThrow(
                () -> new EntityNotExistsException("Image with id: " + id + " does not exists!")
        ));
        return image.get();
    }

    /**
     * Upload image on cloudinary and return particular url for Image
     *
     * @param multipartFile - image
     * @return String (image url)
     */
    private String uploadOnCloudinary(MultipartFile multipartFile) throws IOException {

        File file = this.convertMultipartFileToFile(multipartFile);

        Cloudinary cloudinary =
                new Cloudinary("cloudinary://" + cloudApiKey + ":" + cloudApiSecret + "@" + cloudName);

        Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        String url = (String) result.get("secure_url");

        file.delete();

        return url;
    }

    /**
     * Delete image from Cloudinary
     *
     * @param imageLink
     */
    private void deleteImageFromCloudinary(String imageLink) throws Exception {

        Cloudinary cloudinary = new Cloudinary(
                "cloudinary://" + cloudApiKey + ":" + cloudApiSecret + "@" + cloudName);

        cloudinary.api().deleteResources(new ArrayList<>(
                        Arrays.asList(imageLink.substring(66, 86))),
                ObjectUtils.emptyMap());
    }

    /**
     * Convert multipart file to file
     *
     * @param multipartFile
     */
    //Convert MultipartFile in File
    public File convertMultipartFileToFile(MultipartFile multipartFile) {

        File convFile = new File(multipartFile.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();

        } catch (IOException e) {

        }

        return convFile;
    }
}
