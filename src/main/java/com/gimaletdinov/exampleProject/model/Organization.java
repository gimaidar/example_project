package com.gimaletdinov.exampleProject.model;

import javax.persistence.*;

@Entity
@Table(name = "Organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    private Integer version;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "full_name", length = 50, nullable = false)
    private String full_name;

    @Column(name = "inn", length = 12, nullable = false)
    private String inn;

    @Column(name = "kpp", length = 12, nullable = false)
    private String kpp;

    @Column(name = "adress", length = 50, nullable = false)
    private String adress;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "is_active")
    private boolean isActive;


}
