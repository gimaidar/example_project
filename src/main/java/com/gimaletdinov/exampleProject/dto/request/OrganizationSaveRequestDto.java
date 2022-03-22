package com.gimaletdinov.exampleProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrganizationSaveRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String fullName;

    @NotNull
    private String inn;

    @NotNull
    private String kpp;

    @NotNull
    private String address;

    private String phone;

    private Boolean isActive;
}
