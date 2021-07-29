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
@ApiModel(description = "Size details")
public class Size {

    /**
     * unique size id, auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "${Size.id}")
    private long id;

    /**
     * size name
     */
    @ApiModelProperty(value = "${Size.name}")
    private String sizeName;

    public Size(String sizeName) {
        this.sizeName = sizeName;
    }
}
