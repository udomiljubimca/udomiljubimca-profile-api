package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Nature;
import com.java.profileservice.service.NatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/nature")
public class NatureController {

    @Autowired
    private NatureService natureService;

    @GetMapping("/all")
    public ApiResponse getAllNatures() {
        List<Nature> natureList = natureService.getAllNatures();

        return new ApiResponse(natureList);
    }
}
