package com.gimaletdinov.exampleProject.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
public class OfficeListRequestDto {

    @NotNull
    @Range(min = 1)
    private Integer orgId;

    private String name;

    private String phone;

    private Boolean isActive;
}
