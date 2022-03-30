package com.gimaletdinov.exampleProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class OrganizationUpdateRequestDto {

    @NotNull
    @Range(min = 1)
    private Integer id;

    @NotBlank(message = "Name can't be blank")
    @Size( min = 2, max = 25, message = "Name must be between 2 and 25 symbols")
    private String name;

    @NotBlank(message = "Fullname can't be blank")
    @Size( min = 2, max = 50, message = "Fullname must be between 2 and 50 symbols")
    private String fullName;

    @NotBlank(message = "Inn can't be blank")
    @Size( min = 1, max = 12, message = "Inn must be between 1 and 12 symbols")
    private String inn;

    @NotBlank(message = "Kpp can't be blank")
    @Size( min = 1, max = 12, message = "Kpp must be between 1 and 12 symbols")
    private String kpp;

    @NotBlank(message = "Address can't be blank")
    @Size( min = 2, max = 50, message = "Address must be between 2 and 50 symbols")
    private String address;

    private String phone;

    private Boolean isActive;
}
