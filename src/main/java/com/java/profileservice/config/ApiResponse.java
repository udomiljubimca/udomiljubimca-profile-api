package com.java.profileservice.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@Builder
@AllArgsConstructor
public class ApiResponse {

    /**
     * response status
     */
    private Integer status;

    /**
     * response data
     */
    private Object data;

    public ApiResponse() {
        this.status = HttpStatus.OK.value();
    }

    public ApiResponse(Object data) {
        this.status = HttpStatus.OK.value();
        this.data = data;
    }


}
