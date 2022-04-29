package com.gimaletdinov.exampleProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OfficeUpdateRequestDto {

    @NotNull
    @Range(min = 1)
    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    private String phone;

    private Boolean isActive;
}
