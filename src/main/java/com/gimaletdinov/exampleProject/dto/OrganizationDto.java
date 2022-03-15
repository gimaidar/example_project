package com.gimaletdinov.exampleProject.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationDto {

    private int id;

    private Integer version;

    private String name;

    private String full_name;

    private String inn;

    private String kpp;

    private String adress;

    private String phone;

    private boolean isActive;


}
