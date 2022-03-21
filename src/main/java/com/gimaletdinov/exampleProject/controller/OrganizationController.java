package com.gimaletdinov.exampleProject.controller;

import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationSuccessResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import com.gimaletdinov.exampleProject.model.mapper.OrganizationMapper;
import com.gimaletdinov.exampleProject.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<Organization> getAllOrganization(@Validated @RequestBody OrganizationListRequestDto organizationListRequestDto) {
        return organizationService.getAllOrganization();
    }

    @PostMapping("/update")
    public OrganizationSuccessResponseDto updateOrganization(@Validated @RequestBody OrganizationUpdateRequestDto organizationUpdateRequestDto) {
        organizationService.updateOrganization(organizationUpdateRequestDto); //нужно ли здесь делать эту логику? делать через маппер?
        return new OrganizationSuccessResponseDto();
    }

    @PostMapping("/save")
    public OrganizationSuccessResponseDto saveOrganization(@Validated @RequestBody OrganizationSaveRequestDto organizationSaveRequestDto) {
        organizationService.saveOrganization(organizationMapper.toModel(organizationSaveRequestDto));
        return new OrganizationSuccessResponseDto();
    }
}
