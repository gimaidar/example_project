package com.gimaletdinov.example_project.user.model;

import com.gimaletdinov.example_project.citizenship.model.Citizenship;
import com.gimaletdinov.example_project.document.model.Document;
import com.gimaletdinov.example_project.office.model.Office;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    private Integer version;

    @Column(name = "first_name", length = 25, nullable = false)
    private String first_name;

    @Column(name = "second_name", length = 25)
    private String second_name;

    @Column(name = "middle_name", length = 25)
    private String middle_name;

    @Column(name = "position", nullable = false)
    private int position;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "is_identified")
    private boolean isIdentified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    private Document document;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_id")
    private Citizenship citizenship;
}
