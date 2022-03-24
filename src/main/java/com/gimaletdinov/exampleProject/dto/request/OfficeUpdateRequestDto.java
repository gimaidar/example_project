package com.gimaletdinov.exampleProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class OfficeUpdateRequestDto {

    @NotEmpty
    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    private String phone;

    private Boolean isActive;
}
