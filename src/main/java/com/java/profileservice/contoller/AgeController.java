package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Age;
import com.java.profileservice.service.AgeService;
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
 * Age controller for Adopt a pet project
 * Acceptance criterias:
 * 1)get all ages
 */
@RestController
@RequestMapping(value = "/age")
@Api(tags = {"Age controller"})
public class AgeController {

    @Autowired
    public AgeService ageService;

    /**
     * 1) Get all ages route uses to get all ages
     *
     * @return List<Age>
     */
    @GetMapping("/all")
    @ApiOperation(
            notes = "${operation11.description}",
            value = "${operation11.value}"
    )
    public ApiResponse getAllAges() {
        List<Age> ageList = ageService.getAllAges();

        return new ApiResponse(ageList);
    }
}
