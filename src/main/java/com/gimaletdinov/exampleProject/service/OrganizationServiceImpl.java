package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.OrganizationRepository;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements  OrganizationService{


    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<Organization> getAllOrganization() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization getOrganizationById(int id) {
        return organizationRepository.findById(id).get();
    }

    @Override
    public void updateOrganization(OrganizationUpdateRequestDto organizationUpdateRequestDto) {
        Organization organization = organizationRepository.findById(organizationUpdateRequestDto.getId()).get();
        organization.setName(organizationUpdateRequestDto.getName());
        organizationRepository.save(organization);
    }

    @Override
    public void saveOrganization(Organization organization) {
        organizationRepository.save(organization);
    }
}
