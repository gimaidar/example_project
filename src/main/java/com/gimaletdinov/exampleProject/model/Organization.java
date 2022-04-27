package com.gimaletdinov.exampleProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Сущность Организация
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    @NotBlank
    @Size(max = 25)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String fullName;

    @NotBlank
    @Size(min = 12, max = 12)
    private String inn;

    @NotBlank
    @Size(min = 12, max = 12)
    private String kpp;

    @NotBlank
    @Size(max = 50)
    private String address;

    @Size(max = 11)
    private String phone;

    @NotNull
    private Boolean isActive;
}
