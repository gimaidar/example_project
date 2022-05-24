package com.gimaletdinov.exampleProject.dto.kafka;

import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class OrganizationObjectDto {
    private Integer id;
    private OrganizationSaveRequestDto organizationSaveRequestDto;
}
