package com.gimaletdinov.exampleProject.dto.request;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrganizationListRequestDto {

    @NotNull
    private String name;

    private String inn;

    private boolean isActive;

}
