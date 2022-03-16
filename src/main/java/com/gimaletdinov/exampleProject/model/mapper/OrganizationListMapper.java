package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.OrganizationRequestDto;
import com.gimaletdinov.exampleProject.dto.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrganizationMapper.class)
public interface OrganizationListMapper {

    List<Organization> toModelList(List<OrganizationResponseDto> organizationResponseDtoList);

    List<Organization> toModelList(List<OrganizationRequestDto> organizationRequestDtoList);

    List<OrganizationResponseDto> toDtoList(List<Organization> organizationList);

}
