package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    Organization toModel(OrganizationSaveRequestDto organizationSaveRequestDto);

    Organization toModel(OrganizationListRequestDto organizationListRequestDto);

    OrganizationResponseDto toResponseDto(Organization organization);

    void updateModel(OrganizationUpdateRequestDto organizationUpdateRequestDto, @MappingTarget Organization organization);

    List<OrganizationListResponseDto> toResponseDtoList(List<Organization> organizationList);
}
