package com.java.profileservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@ApiModel(description = "Image details")
public class Image {

    /**
     * unique image id, auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "${Image.id}")
    private long id;

    /**
     * image link
     */
    @ApiModelProperty(value = "${Image.link}")
    private String imageLink;

    /**
     * profile id
     */
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnore
    @ApiModelProperty(value = "${Image.profile}")
    private Profile profile;

}
