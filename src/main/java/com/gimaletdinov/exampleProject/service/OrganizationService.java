package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.model.Organization;
import java.util.List;

public interface OrganizationService {
    public List<Organization> getAllOrganization();

    public Organization getOrganizationById(int id);

    public void updateOrganization(OrganizationUpdateRequestDto organizationUpdateRequestDto);

    public void saveOrganization(Organization organization);
}
