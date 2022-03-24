package com.gimaletdinov.exampleProject.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OfficeResponseDto {

    private Integer id;

    private String name;

    private String address;

    private String phone;

    private Boolean isActive;
}
