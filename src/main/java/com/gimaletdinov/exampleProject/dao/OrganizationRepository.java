package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Organization;

import java.util.List;


public interface OrganizationRepository  {
    public List<Organization> getAllOrganizationByPredicat(Organization organization);

    public Organization getOrganizationById(int id);

    public void updateOrganization(Organization organization);

    public void saveOrganization(Organization organization);
}
