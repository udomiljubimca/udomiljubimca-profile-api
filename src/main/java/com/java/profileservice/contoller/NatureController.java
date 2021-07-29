package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Nature;
import com.java.profileservice.service.NatureService;
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
 * Nature controller for Adopt a pet project
 * Acceptance criterias:
 * 1)get all natures
 */
@RestController
@RequestMapping(value = "/nature")
@Api(tags = {"Nature controller"})
public class NatureController {

    @Autowired
    private NatureService natureService;

    /**
     * 1) Get all natures route uses to get all natures
     *
     * @return List<Nature>
     */
    @GetMapping("/all")
    @ApiOperation(
            notes = "${operation17.description}",
            value = "${operation17.value}"
    )
    public ApiResponse getAllNatures() {
        List<Nature> natureList = natureService.getAllNatures();

        return new ApiResponse(natureList);
    }
}
