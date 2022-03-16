package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.OrganizationDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    @Mapping(source = "adress", target = "address")
    OrganizationDto toDto(Organization organization);

    Organization toModel(OrganizationDto organizationDto);
}
