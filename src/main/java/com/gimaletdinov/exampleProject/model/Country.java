package com.gimaletdinov.exampleProject.model;

import javax.persistence.*;

@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    private Integer version;

    @Column(name = "country_name", length = 25, nullable = false)
    private String name;

    @Column(name = "country_code", nullable = false)
    private Integer number;
}
