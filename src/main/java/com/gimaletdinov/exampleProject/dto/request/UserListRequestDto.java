package com.gimaletdinov.exampleProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserListRequestDto {

    @NotNull
    @Range(min = 1)
    private Integer officeId;

    private String firstName;

    private String secondName;

    private String middleName;

    private Integer position;

    private Integer docCode;

    private Integer countryCode;
}
