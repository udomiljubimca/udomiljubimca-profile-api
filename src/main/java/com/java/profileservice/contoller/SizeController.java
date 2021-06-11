package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Size;
import com.java.profileservice.service.SizeService;
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
 * Size controller for Adopt a pet project
 * Acceptance criterias:
 * 1)get all sizes
 */
@RestController
@RequestMapping("/size")
@Api(tags = {"Size controller"})
public class SizeController {

    @Autowired
    public SizeService sizeService;

    /**
     * 1) Get all sizes route uses to get all sizes
     *
     * @return List<Size>
     */
    @GetMapping("/all")
    @ApiOperation(
            notes = "${operation18.description}",
            value = "${operation18.value}"
    )
    public ApiResponse getAllSizes() {
        List<Size> sizeList = sizeService.getAllSizes();
        return new ApiResponse(sizeList);

    }
}
