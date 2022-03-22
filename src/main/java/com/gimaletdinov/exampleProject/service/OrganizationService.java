package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;

import java.util.List;

public interface OrganizationService {
    public List<OrganizationListResponseDto> getAllOrganizationByPredicat(OrganizationListRequestDto organizationListRequestDto);

    public OrganizationResponseDto getOrganizationById(int id);

    public void updateOrganization(OrganizationUpdateRequestDto organizationUpdateRequestDto);

    public void saveOrganization(OrganizationSaveRequestDto organizationSaveRequestDto);
}
