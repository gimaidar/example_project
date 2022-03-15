package com.gimaletdinov.exampleProject.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "citizenship")
@Data
@NoArgsConstructor
public class Citizenship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    private Integer version;


    @NonNull
    @Column(name = "citizenship_name", length = 25, nullable = false)
    private String name;

    @Column(name = "citizenship_code", nullable = false)
    private Integer number;
}
