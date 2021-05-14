package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Health;
import com.java.profileservice.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @GetMapping("/all")
    public ApiResponse getAllHealths() {
        List<Health> healthList = healthService.getAllHealths();

        return new ApiResponse(healthList);
    }

}
