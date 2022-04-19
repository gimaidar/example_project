package com.gimaletdinov.exampleProject.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserResponseDto {

    @NotNull
    @Range(min = 1)
    private Integer officeId;

    private String firstName;

    private String secondName;

    private String middleName;

    private Integer position;

    private String phone;

    private Boolean isIdentified;

    private String docName;

    private Integer docNumber;

    private LocalDate docDate;

    private String countryName;

    private Integer countryCode;
}
