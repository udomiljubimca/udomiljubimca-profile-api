package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Health;
import com.java.profileservice.service.HealthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Health controller for Adopt a pet project
 * Acceptance criterias:
 * 1)get all healths
 */
@RestController
@RequestMapping(value = "/health")
@Api(tags = {"Health controller"})
public class HealthController {

    @Autowired
    private HealthService healthService;

    /**
     * 1) Get all healths route uses to get all healths
     *
     * @return List<Health>
     */
    @GetMapping("/all")
    @ApiOperation(
            notes = "${operation14.description}",
            value = "${operation14.value}"
    )
    public ApiResponse getAllHealths() {
        List<Health> healthList = healthService.getAllHealths();

        return new ApiResponse(healthList);
    }

}
