package com.gimaletdinov.exampleProject.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDate;

@Entity
@Table(name = "Document")
@Data
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    private Integer version;

    @Column(name = "doc_name", length = 25, nullable = false)
    private String name;

    @Column(name = "doc_number", nullable = false)
    private Integer number;

    @Column(name = "doc_date", nullable = false)
    private LocalDate date;
}
