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

/**
 * Маппер для сущности Организация
 */
@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    /**
     * Метод для маппинга запроса - OrganizationSaveRequestDto в Organization
     * @param organizationSaveRequestDto
     * @return Organization
     */
    Organization toModel(OrganizationSaveRequestDto organizationSaveRequestDto);

    /**
     * Метод для маппинга запроса - OrganizationListRequestDto в Organization
     * @param organizationListRequestDto
     * @return Organization
     */
    Organization toModel(OrganizationListRequestDto organizationListRequestDto);

    /**
     * Метод для маппинга Organization в формат ответа OrganizationResponseDto
     * @param organization
     * @return OrganizationResponseDto
     */
    OrganizationResponseDto toResponseDto(Organization organization);

    /**
     * Метод для маппинга (обновления) Organization из запроса - OrganizationUpdateRequestDto
     * @param organizationUpdateRequestDto
     * @param organization
     */
    void updateModel(OrganizationUpdateRequestDto organizationUpdateRequestDto, @MappingTarget Organization organization);

    /**
     * Метод для маппинга спсика Organization в формат ответа список OrganizationListResponseDto
     * @param organizationList
     * @return спсиок OrganizationListResponseDto
     */
    List<OrganizationListResponseDto> toResponseDtoList(List<Organization> organizationList);
}
