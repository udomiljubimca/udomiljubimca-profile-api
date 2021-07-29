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
@ApiModel(description = "Nature details")
public class Nature {

    /**
     * unique nature id, auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "${Nature.id}")
    private long id;

    /**
     * nature name
     */
    @ApiModelProperty(value = "${Nature.name}")
    private String natureName;

    public Nature(String natureName) {
        this.natureName = natureName;
    }
}

