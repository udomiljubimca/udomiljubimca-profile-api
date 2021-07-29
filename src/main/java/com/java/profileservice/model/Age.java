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
@ApiModel(description = "Age details")
public class Age {

    /**
     * unique age id, auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "${Age.id}")
    private long id;

    /**
     * age name
     */
    @ApiModelProperty(value = "${Age.ageName}")
    private String ageName;

    public Age(String ageName) {
        this.ageName = ageName;
    }
}
