package com.java.profileservice.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.java.profileservice.dto.ProfileDto;
import com.java.profileservice.model.*;
import com.java.profileservice.repository.*;
import com.java.profileservice.service.ImageService;
import com.java.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AgeRepository ageRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private NatureRepository natureRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private HealthRepository healthRepository;

    @Autowired
    private ImageService imageService;

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String cloudApiKey;

    @Value("${cloudinary.api_secret}")
    private String cloudApiSecret;

    public Profile saveProfile(ProfileDto profileDto, MultipartFile[] multipartFiles) throws Exception {

        Profile profile = new Profile();

        profile.setProfileName(profileDto.getProfileName());
        profile.setAssociationName(profileDto.getAssociationName());
        profile.setVaccinated(profileDto.isVaccinated());
        profile.setSpecialHabits(profileDto.isSpecialHabits());
        profile.setSpecialHabitsText(profileDto.getSpecialHabitsText());
        profile.setDescription(profileDto.getDescription());
        profile.setVideoLink(profileDto.getVideoLink());
        profile.setGoodWithKids(profileDto.isGoodWithKids());
        profile.setGoodWithDogs(profileDto.isGoodWithDogs());
        profile.setGoodWithCats(profileDto.isGoodWithCats());
        profile.setUploadDate(new Date(new java.util.Date().getTime()));


        if (profileDto.getSpecialNeeds() == null || profileDto.getSpecialNeeds().equalsIgnoreCase("")) {
            profile.setSpecialNeeds("Nisu unesene posebne potrebe.");
        } else {
            profile.setSpecialNeeds(profileDto.getSpecialNeeds());
        }

        Optional<Age> age = ageRepository.findById(profileDto.getAgeId());
        if (age.isPresent()) {
            profile.setAge(age.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Gender> gender = genderRepository.findById(profileDto.getGenderId());
        if (gender.isPresent()) {
            profile.setGender(gender.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Nature> nature = natureRepository.findById(profileDto.getNatureId());
        if (nature.isPresent()) {
            profile.setNature(nature.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Type> type = typeRepository.findById(profileDto.getTypeId());
        if (type.isPresent()) {
            profile.setType(type.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<City> city = cityRepository.findById(profileDto.getCityId());
        if (city.isPresent()) {
            profile.setCity(city.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Size> size = sizeRepository.findById(profileDto.getSizeId());
        if (size.isPresent()) {
            profile.setSize(size.get());
        } else {
            throw new Exception("Error message");
        }

        List<Health> healthList = healthRepository.getHealthByIds(profileDto.getHealthIds());
        profile.setHealths(healthList);

        List<Image> images = new ArrayList<>();
        Arrays.asList(multipartFiles).stream().limit(3).forEach(multipartFile -> {
            Image image = new Image();
            try {
                image.setImageLink(uploadImages(multipartFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setProfile(profile);
            images.add(image);

        });
        imageService.saveAll(images);
        profile.setImages(images);

        profileRepository.save(profile);

        return profile;
    }

    @Override
    public void deleteAll() throws Exception {

        List<Image> images = imageService.getAll();

        for (Image url : images) {
            Cloudinary cloudinary = new Cloudinary(
                    "cloudinary://" + cloudApiKey + ":" + cloudApiSecret + "@" + cloudName);
            cloudinary.api().deleteResources(new ArrayList<>(
                            Arrays.asList(url.getImageLink().substring(66, 86))),
                    ObjectUtils.emptyMap());
        }

        profileRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) throws Exception {

        Optional<Profile> profile = profileRepository.findById(id);
        if (profile.isPresent()) {
            Cloudinary cloudinary = new Cloudinary(
                    "cloudinary://" + cloudApiKey + ":" + cloudApiSecret + "@" + cloudName);
            List<String> urls = profile.get().getImages()
                    .stream().map(Image::getImageLink)
                    .collect(Collectors.toList());

            for (String url : urls) {
                cloudinary.api().deleteResources(new ArrayList<>(
                                Arrays.asList(url.substring(66, 86))),
                        ObjectUtils.emptyMap());
            }
        }
        profileRepository.deleteById(id);
    }


    @Override
    public String uploadImages(MultipartFile multipartFile) throws IOException {

        File file = this.convertMultipartFileToFile(multipartFile);

        Cloudinary cloudinary =
                new Cloudinary("cloudinary://" + cloudApiKey + ":" + cloudApiSecret + "@" + cloudName);

        Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        String url = (String) result.get("secure_url");

        file.delete();

        return url;
    }

    @Override
    public Profile getProfileById(Long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        return profile.get();
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public List<Profile> getAllByTypeId(Long typeId) {

        Optional<Type> type = typeRepository.findById(typeId);

        if (type.isPresent()) {
            return profileRepository.findAllByTypeId(typeId);
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Profile> getAllByCityId(Long cityId, int page) {

        Optional<City> city = cityRepository.findById(cityId);
        Pageable pageable = PageRequest.of(page, 2);

        if (city.isPresent()) {
            return profileRepository.findAllByCityId(cityId, pageable);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Profile> profileSearch(Long cityId, Long typeId, int page) {
        Optional<City> city = cityRepository.findById(cityId);
        Pageable pageable = PageRequest.of(page, 16);

        if(city.isPresent()) {
            return profileRepository.searchProfile(cityId, typeId, pageable);
        }else {
            return new ArrayList<>();
        }
    }

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
