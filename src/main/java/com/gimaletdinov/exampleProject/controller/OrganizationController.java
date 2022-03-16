package com.gimaletdinov.exampleProject.controller;

import com.gimaletdinov.exampleProject.dto.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import com.gimaletdinov.exampleProject.model.mapper.OrganizationMapper;
import com.gimaletdinov.exampleProject.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    private final OrganizationMapper organizationMapper;

    @Autowired
    public OrganizationController(OrganizationService organizationService, OrganizationMapper organizationMapper) {
        this.organizationService = organizationService;
        this.organizationMapper = organizationMapper;
    }

    @GetMapping("/list")
    public List<Organization> getAllOrganization(){
        return organizationService.getAllOrganization();
    }

    @GetMapping("/{id}")
    public OrganizationResponseDto getOrganizationById(@PathVariable int id){
        Organization organization = organizationService.getOrganizationById(id);
        return organizationMapper.toResponseDto(organization);
    }

}
