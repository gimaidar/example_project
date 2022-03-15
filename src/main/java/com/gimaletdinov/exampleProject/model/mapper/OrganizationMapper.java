package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.OrganizationDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    OrganizationDto toDto(Organization organization);
    Organization toModel(OrganizationDto organizationDto);
}
