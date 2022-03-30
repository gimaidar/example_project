package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Organization;
import java.util.List;

public interface OrganizationRepository {

    List<Organization> getAllOrganizationsByPredicat(Organization organization);

    Organization getOrganizationById(int id);

    void updateOrganization(Organization organization);

    void saveOrganization(Organization organization);
}
