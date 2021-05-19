package com.java.profileservice.contoller;

import com.java.profileservice.config.ApiResponse;
import com.java.profileservice.model.Size;
import com.java.profileservice.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/size")
public class SizeController {

    @Autowired
    public SizeService sizeService;

    @GetMapping("/all")
    public ApiResponse getAllSizes() {
        List<Size> sizeList = sizeService.getAllSizes();
        return new ApiResponse(sizeList);

    }
}
