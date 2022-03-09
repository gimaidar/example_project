package com.gimaletdinov.example_project.user.model;

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

    @Column(name = "first_name", length = 15, nullable = false)
    private String first_name;

    @Column(name = "second_name", length = 50, nullable = false)
    private String second_name;

    @Column(name = "middle_name", length = 50, nullable = false)
    private String middle_name;

    @Column(name = "position", nullable = false)
    private int position;

    @Column(name = "phone", length = 11)
    private String phone;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", position=" + position +
                ", phone='" + phone + '\'' +
                '}';
    }
}
