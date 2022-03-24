package com.gimaletdinov.exampleProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserListRequestDto {

    @NotNull
    @Range(min = 1)
    private Integer officeId;

    private String first_name;

    private String second_name;

    private String middle_name;

    private Integer position;

    private Integer docCode;

    private Integer citizenshipCode;
}
