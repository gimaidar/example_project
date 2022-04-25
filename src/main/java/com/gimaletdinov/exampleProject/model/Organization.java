package com.gimaletdinov.exampleProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Сущность Организация
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    private String name;

    private String fullName;

    private String inn;

    private String kpp;

    private String address;

    private String phone;

    private Boolean isActive;
}
