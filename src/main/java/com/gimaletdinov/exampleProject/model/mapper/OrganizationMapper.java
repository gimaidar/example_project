package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.OrganizationRequestDto;
import com.gimaletdinov.exampleProject.dto.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    @Mapping(source = "adress", target = "address")
    OrganizationResponseDto toResponseDto(Organization organization);

    Organization toModel(OrganizationResponseDto organizationResponseDto);

    Organization toModel(OrganizationRequestDto organizationRequestDto);
}
