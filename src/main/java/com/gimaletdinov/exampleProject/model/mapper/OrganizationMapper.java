package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    OrganizationListResponseDto toListResponseDto(Organization organization);

    OrganizationResponseDto toResponseDto(Organization organization);

    Organization toModel(OrganizationListRequestDto organizationListRequestDto);

    Organization toModel(OrganizationUpdateRequestDto organizationUpdateRequestDto);

    Organization toModel(OrganizationSaveRequestDto organizationSaveRequestDto);
}
