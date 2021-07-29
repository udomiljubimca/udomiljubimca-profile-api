package com.java.profileservice.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@ApiModel(description = "City details")
public class City {

    /**
     * unique city id, auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "${City.id}")
    private long id;

    /**
     * city name
     */
    @ApiModelProperty(value = "${City.cityName}")
    private String cityName;

    /**
     * city zip code
     */
    @ApiModelProperty(value = "${City.cityZipcode}")
    private String cityZipcode;

    public City(String cityName) {
        this.cityName = cityName;
    }
}
