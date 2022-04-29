package com.gimaletdinov.exampleProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Сущность Тип документа
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class DocumentType {

    @Id
    private Integer id;

    @NotBlank
    //@Size(max = 3)
    private String code;

    @NotBlank
    //@Size(max = 25)
    private String name;
}
