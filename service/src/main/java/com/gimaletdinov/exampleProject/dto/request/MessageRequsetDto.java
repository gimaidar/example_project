package com.gimaletdinov.exampleProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageRequsetDto {

    private Integer id;

    private String message;
}
