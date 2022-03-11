package com.gimaletdinov.exampleProject.model;

import javax.persistence.*;

@Entity
@Table(name = "Citizenship")
public class Citizenship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    private Integer version;

    @Column(name = "citizenship_name", length = 25, nullable = false)
    private String name;

    @Column(name = "citizenship_code", nullable = false)
    private Integer number;
}
