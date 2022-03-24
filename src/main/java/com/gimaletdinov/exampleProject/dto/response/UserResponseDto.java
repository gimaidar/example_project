package com.gimaletdinov.exampleProject.dto.response;

import com.gimaletdinov.exampleProject.model.Citizenship;
import com.gimaletdinov.exampleProject.model.Document;
import com.gimaletdinov.exampleProject.model.Office;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserResponseDto {

    @NotNull
    @Range(min = 1)
    private Integer officeId;

    private String first_name;

    private String second_name;

    private String middle_name;

    private Integer position;

    private String phone;

    private Boolean isIdentified;

    private String docName;

    private String docNumber;

    private LocalDate docDate;

    private String citizenshipName;

    private String citizenshipCode;
}
