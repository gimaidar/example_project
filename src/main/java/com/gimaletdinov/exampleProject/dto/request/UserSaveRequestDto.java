package com.gimaletdinov.exampleProject.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserSaveRequestDto {

    @NotNull
    @Range(min = 1)
    private Integer officeId;

    @NotBlank
    private String first_name;

    private String second_name;

    private String middle_name;

    @NotNull
    @Range(min = 1)
    private Integer position;

    private String phone;

    private Boolean isIdentified;

    private String docName;

    private String docNumber;

    private LocalDate docDate;

    private String citizenshipName;

    private String citizenshipCode;
}
