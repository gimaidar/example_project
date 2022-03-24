package com.gimaletdinov.exampleProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserUpdateRequestDto {

    @NotNull
    @Range(min = 1)
    private Integer id;

    private Integer officeId;

    @NotBlank
    private String first_name;

    private String second_name;

    private String middle_name;

    @NotBlank
    private Integer position;

    private String phone;

    private Boolean isIdentified;

    private String docName;

    private String docNumber;

    private LocalDate docDate;

    private String citizenshipName;

    private String citizenshipCode;
}
