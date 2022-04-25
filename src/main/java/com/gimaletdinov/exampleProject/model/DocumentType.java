package com.gimaletdinov.exampleProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

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

    private String code;

    private String name;
}
