package com.gimaletdinov.exampleProject.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationListResponseDto {

    private int id;

    private String name;

    private boolean isActive;
}
