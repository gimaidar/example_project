package com.gimaletdinov.exampleProject.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Сущность Тип документа
 */
@Entity
@Data
@NoArgsConstructor
public class DocumentType {

    @Id
    private Integer id;

    private String code;

    private String name;
}
