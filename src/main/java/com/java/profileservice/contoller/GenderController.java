package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Gender;
import com.java.profileservice.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/gender")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @GetMapping("/all")
    public ApiResponse getAllGenders() {
        List<Gender> genderList = genderService.getAllGenders();

        return new ApiResponse(genderList);
    }
}
