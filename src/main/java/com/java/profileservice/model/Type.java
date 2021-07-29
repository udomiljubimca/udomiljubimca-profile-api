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
@ApiModel(description = "Type details")
public class Type {

    /**
     * unique type id, auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "${Type.id}")
    private long id;

    /**
     * type name
     */
    @ApiModelProperty(value = "${Type.name}")
    private String typeName;

    public Type(String typeName) {
        this.typeName = typeName;
    }
}
