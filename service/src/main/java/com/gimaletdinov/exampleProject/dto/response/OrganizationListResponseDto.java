package com.gimaletdinov.exampleProject.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationListResponseDto {

    private Integer id;

    private String name;

    private Boolean isActive;
}
