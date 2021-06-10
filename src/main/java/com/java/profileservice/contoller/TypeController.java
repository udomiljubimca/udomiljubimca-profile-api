package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Type;
import com.java.profileservice.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/type")
@Api(tags = {"Type controller"})
public class TypeController {

    @Autowired
    public TypeService typeService;

    @GetMapping("/all")
    @ApiOperation(
            notes = "${operation19.description}",
            value = "${operation19.value}"
    )
    public ApiResponse getAllTypes() {
        List<Type> typeList = typeService.getAllTypes();
        return new ApiResponse(typeList);
    }
}
