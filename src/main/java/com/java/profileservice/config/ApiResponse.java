package com.java.profileservice.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@Builder
@AllArgsConstructor
@ApiModel(description = "Api Response details")
public class ApiResponse {

    /**
     * response status
     */
    @ApiModelProperty(value = "${ApiResponse.status}")
    private Integer status;

    /**
     * response data
     */
    @ApiModelProperty(value = "${ApiResponse.data}")
    private Object data;

    public ApiResponse() {
        this.status = HttpStatus.OK.value();
    }

    public ApiResponse(Object data) {
        this.status = HttpStatus.OK.value();
        this.data = data;
    }


}
