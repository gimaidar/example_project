package com.gimaletdinov.exampleProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class OrganizationListRequestDto {

    @NotBlank(message = "Name can't be blank")
    @Size( min = 2, max = 25, message = "Name must be between 2 and 25 symbols")
    private String name;

    @Size( min = 1, max = 12, message = "Inn must be between 1 and 12 symbols")
    private String inn;

    private Boolean isActive;
}
