package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrganizationService {

    List<OrganizationListResponseDto> getAllOrganizationsByPredicat(OrganizationListRequestDto organizationListRequestDto);

    OrganizationResponseDto getOrganizationById(int id);

    void updateOrganization(OrganizationUpdateRequestDto organizationUpdateRequestDto);

    void saveOrganization(OrganizationSaveRequestDto organizationSaveRequestDto);

    @Transactional
    Organization getOrganizationByIdFromRepository(int id);
}
