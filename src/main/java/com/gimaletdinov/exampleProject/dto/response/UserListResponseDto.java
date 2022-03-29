package com.gimaletdinov.exampleProject.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserListResponseDto {

    private Integer id;

    private String firstName;

    private String secondName;

    private String middleName;

    private Integer position;
}
