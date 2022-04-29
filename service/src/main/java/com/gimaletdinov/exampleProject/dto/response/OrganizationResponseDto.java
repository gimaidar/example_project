package com.gimaletdinov.exampleProject.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationResponseDto {

    private Integer id;

    private String name;

    private String fullName;

    private String inn;

    private String kpp;

    private String address;

    private String phone;

    private Boolean isActive;
}
