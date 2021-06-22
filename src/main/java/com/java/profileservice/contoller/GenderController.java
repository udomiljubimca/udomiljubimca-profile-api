package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Gender;
import com.java.profileservice.service.GenderService;
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
 * Gender controller for Adopt a pet project
 * Acceptance criterias:
 * 1)get all genders
 */
@RestController
@RequestMapping(value = "/gender")
@Api(tags = {"Gender controller"})
public class GenderController {

    @Autowired
    private GenderService genderService;

    /**
     * 1) Get all genders route uses to get all genders
     *
     * @return List<Gender>
     */
    @GetMapping("/all")
    @ApiOperation(
            notes = "${operation13.description}",
            value = "${operation13.value}"
    )
    public ApiResponse getAllGenders() {
        List<Gender> genderList = genderService.getAllGenders();

        return new ApiResponse(genderList);
    }
}
