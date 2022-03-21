package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring", uses = OrganizationMapper.class)
public interface OrganizationListMapper {

    List<Organization> toModelList(List<OrganizationListRequestDto> organizationListRequestDtoList);

    List<OrganizationListResponseDto> toResponseDtoList(List<Organization> organizationList);
}
