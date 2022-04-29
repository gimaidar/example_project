package com.gimaletdinov.exampleProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Сущность Офис
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    @NotBlank
    //@Size(max = 25)
    private String name;

    @NotBlank
    //@Size(max = 50)
    private String address;

    //@Size(max = 11)
    private String phone;

    @NotNull
    private Boolean isActive;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    private Organization organization;
}
