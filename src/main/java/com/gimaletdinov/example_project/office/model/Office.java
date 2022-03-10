package com.gimaletdinov.example_project.office.model;

import com.gimaletdinov.example_project.organization.model.Organization;

import javax.persistence.*;

@Entity
@Table(name = "Office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    private Integer version;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "adress", length = 50, nullable = false)
    private String adress;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;


}
