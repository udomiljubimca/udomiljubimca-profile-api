package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Age;
import com.java.profileservice.service.AgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/age")
public class AgeController {

    @Autowired
    public AgeService ageService;

    @GetMapping("/all")
    public ApiResponse getAllAges() {
        List<Age> ageList = ageService.getAllAges();
        
        if(ageList == null) {
        }

        return new ApiResponse(ageList);
    }
}
