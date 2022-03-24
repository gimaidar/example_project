package com.gimaletdinov.exampleProject.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserListResponseDto {

    private Integer id;

    private String first_name;

    private String second_name;

    private String middle_name;

    private Integer position;
}
