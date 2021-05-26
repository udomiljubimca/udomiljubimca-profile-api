package com.java.profileservice.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        Optional<Profile> profile = Optional.of(new Profile());

        //Metoda proverava imprt da li postoje entiteti i setuje
        saveEntitiesAndCheckIfExists(profileDto, profile);

        //Mapira ProfileDto u Profile, proverava special needs i habits
        mapFromDto(profileDto, profile);

        //setujemo date
        profile.get().setUploadDate(new Date(new java.util.Date().getTime()));

        //cuvamo slike
        List<Image> images = saveAndReturnImages(multipartFiles, profile);
        profile.get().setImages(images);

        profileRepository.save(profile.get());

        return profile.get();
    }

    private void saveEntitiesAndCheckIfExists(ProfileDto profileDto, Optional<Profile> profile) throws Exception {
        Optional<Age> age = ageRepository.findById(profileDto.getAgeId());
        if (age.isPresent()) {
            profile.get().setAge(age.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Gender> gender = genderRepository.findById(profileDto.getGenderId());
        if (gender.isPresent()) {
            profile.get().setGender(gender.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Nature> nature = natureRepository.findById(profileDto.getNatureId());
        if (nature.isPresent()) {
            profile.get().setNature(nature.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Type> type = typeRepository.findById(profileDto.getTypeId());
        if (type.isPresent()) {
            profile.get().setType(type.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<City> city = cityRepository.findById(profileDto.getCityId());
        if (city.isPresent()) {
            profile.get().setCity(city.get());
        } else {
            throw new Exception("Error message");
        }

        Optional<Size> size = sizeRepository.findById(profileDto.getSizeId());
        if (size.isPresent()) {
            profile.get().setSize(size.get());
        } else {
            throw new Exception("Error message");
        }

        if (!profileDto.getHealthIds().isEmpty()) {
            List<Health> healthList =
                    healthRepository.getHealthByIds(profileDto.getHealthIds());
            profile.get().setHealths(healthList);
        }
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
    public Profile updateProfile(Long id, MultipartFile[] multipartFiles, String json) throws Exception {

        ProfileDto profileDto;

        //Check if profile exists
        Optional<Profile> profile = profileRepository.findById(id);
        if (!profile.isPresent()) {
            throw new Exception("Profile does not exists!");
        }
        //Check if images exists in request
        if (multipartFiles.length == 0 && json.equalsIgnoreCase("empty")) {
            throw new Exception("No updates!");
        }
        if (multipartFiles.length == 0) {
            try {
                profileDto = new ObjectMapper().readValue(json, ProfileDto.class);

                checkIfEntityUpdateAndSave(profileDto, profile);

                mapFromDto(profileDto, profile);

            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }
            profileRepository.save(profile.get());
        } else if (json.equalsIgnoreCase("empty")) {

            List<Image> newImages = saveAndReturnImages(multipartFiles, profile);
            profile.get().setImages(newImages);
            profileRepository.save(profile.get());
        } else {
            try {
                profileDto = new ObjectMapper().readValue(json, ProfileDto.class);

                mapFromDto(profileDto, profile);

                checkIfEntityUpdateAndSave(profileDto, profile);

            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }
            List<Image> newImages = saveAndReturnImages(multipartFiles, profile);
            profile.get().setImages(newImages);
            profileRepository.save(profile.get());
        }


        return profile.get();
    }

    private void mapFromDto(ProfileDto profileDto, Optional<Profile> profile) {

        profile.get().setProfileName(profileDto.getProfileName());
        profile.get().setAssociationName(profileDto.getAssociationName());
        profile.get().setVaccinated(profileDto.isVaccinated());
        profile.get().setSpecialHabits(profileDto.isSpecialHabits());
        profile.get().setSpecialHabitsText(profileDto.getSpecialHabitsText());
        profile.get().setDescription(profileDto.getDescription());
        profile.get().setVideoLink(profileDto.getVideoLink());
        profile.get().setGoodWithKids(profileDto.isGoodWithKids());
        profile.get().setGoodWithDogs(profileDto.isGoodWithDogs());
        profile.get().setGoodWithCats(profileDto.isGoodWithCats());
        profile.get().setSpecialNeeds(profileDto.getSpecialNeeds());

        if (!profileDto.isSpecialHabits()) {
            profile.get().setSpecialHabitsText("");
        }

        List<Long> healthIds = Collections.emptyList();
        if (!profile.get().getHealths().isEmpty() &&
                profile.get().getHealths() != null) {
            healthIds = profile.get().getHealths().stream()
                    .map(Health::getId)
                    .collect(Collectors.toList());
        }
        if (profile.get().isSpecialHabits()) {
            profile.get().setSpecialHabitsText(profileDto.getSpecialHabitsText());
        } else {
            profile.get().setSpecialHabitsText("Ljubimac nema posebne navike.");
        }
        if (!healthIds.contains(2L)) {
            profile.get().setSpecialNeeds("Ovaj ljubimac nema posebne potrebe.");
        } else {
            profile.get().setSpecialNeeds(profileDto.getSpecialNeeds());
        }
    }

    private void checkIfEntityUpdateAndSave(ProfileDto profileDto, Optional<Profile> profile) throws Exception {
        if (profile.get().getNature().getId() != profileDto.getNatureId()) {
            Optional<Nature> nature = natureRepository.findById(profileDto.getNatureId());
            if (nature.isPresent()) {
                profile.get().setNature(nature.get());
            } else {
                throw new Exception("Error message");
            }
        }
        if (profile.get().getAge().getId() != profileDto.getNatureId()) {
            Optional<Age> age = ageRepository.findById(profileDto.getAgeId());
            if (age.isPresent()) {
                profile.get().setAge(age.get());
            } else {
                throw new Exception("Error message");
            }
        }
        if (profile.get().getGender().getId() != profileDto.getGenderId()) {
            Optional<Gender> gender = genderRepository.findById(profileDto.getGenderId());
            if (gender.isPresent()) {
                profile.get().setGender(gender.get());
            } else {
                throw new Exception("Error message");
            }
        }
        if (profile.get().getSize().getId() != profileDto.getSizeId()) {
            Optional<Size> size = sizeRepository.findById(profileDto.getSizeId());
            if (size.isPresent()) {
                profile.get().setSize(size.get());
            } else {
                throw new Exception("Error message");
            }
        }
        if (profile.get().getCity().getId() != profileDto.getCityId()) {
            Optional<City> city = cityRepository.findById(profileDto.getCityId());
            if (city.isPresent()) {
                profile.get().setCity(city.get());
            } else {
                throw new Exception("Error message");
            }
        }
        if (profile.get().getType().getId() != profileDto.getTypeId()) {
            Optional<Type> type = typeRepository.findById(profileDto.getTypeId());
            if (type.isPresent()) {
                profile.get().setType(type.get());
            } else {
                throw new Exception("Error message");
            }
        }

        List<Long> healthIds = profile.get().getHealths().stream()
                .map(Health::getId)
                .collect(Collectors.toList());
        // 1
        List<Health> healths = new ArrayList<>();
        if (profile.get().getHealths().size() == profileDto.getHealthIds().size()) {
            for (Long l : healthIds) {
                for (Long j : profileDto.getHealthIds()) {
                    if (l.longValue() != j.longValue()) {
                        healths.add(healthRepository.getOne(j));
                        profile.get().setHealths(healths);
                    }
                }
            }
        } else {
            healths = healthRepository.getHealthByIds(profileDto.getHealthIds());
            profile.get().setHealths(healths);
        }
    }

    private List<Image> saveAndReturnImages(MultipartFile[] multipartFiles, Optional<Profile> profile) {

        if (profile.get().getImages() != null) {
            if (profile.get().getImages().size() > 0) {
                List<Long> imageIds = profile.get().getImages().stream()
                        .map(Image::getId)
                        .collect(Collectors.toList());

                imageService.deleteImagesByIds(imageIds);
            }
        }
        //save new images
        List<Image> images = new ArrayList<>();
        Arrays.asList(multipartFiles).stream().limit(3).forEach(multipartFile -> {
            Image image = new Image();
            try {
                image.setImageLink(uploadImages(multipartFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setProfile(profile.get());
            images.add(image);

        });
        return images;
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
