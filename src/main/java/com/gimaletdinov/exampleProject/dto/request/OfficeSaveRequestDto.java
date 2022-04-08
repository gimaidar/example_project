package com.gimaletdinov.exampleProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OfficeSaveRequestDto {

    @NotNull
    @Range(min = 1)
    private Integer orgId;

    private String name;

    private String address;

    private String phone;

    private Boolean isActive;
}
