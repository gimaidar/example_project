package com.gimaletdinov.exampleProject.dto.request;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
