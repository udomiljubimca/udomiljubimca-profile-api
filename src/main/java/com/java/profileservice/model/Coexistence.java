package com.java.profileservice.model;

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
public class Coexistence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String coexistenceName;

    public Coexistence(String coexistenceName) {
        this.coexistenceName = coexistenceName;
    }
}
