package com.gimaletdinov.exampleProject.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name = "Office")
@Data
@NoArgsConstructor
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
