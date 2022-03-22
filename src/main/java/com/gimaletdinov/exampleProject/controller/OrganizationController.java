package com.gimaletdinov.exampleProject.controller;

import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationSuccessResponseDto;
import com.gimaletdinov.exampleProject.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/list")
    public List<OrganizationListResponseDto> getAllOrganizationByPredicat(@Validated @RequestBody OrganizationListRequestDto organizationListRequestDto) {
        return organizationService.getAllOrganizationByPredicat(organizationListRequestDto);
    }

    @GetMapping("/{id}")
    public OrganizationResponseDto getOrganizationById(@PathVariable int id) {
        return organizationService.getOrganizationById(id);
    }

    @PostMapping("/update")
    public OrganizationSuccessResponseDto updateOrganization(@Validated @RequestBody OrganizationUpdateRequestDto organizationUpdateRequestDto) {
        organizationService.updateOrganization(organizationUpdateRequestDto);
        return new OrganizationSuccessResponseDto();
    }

    @PostMapping("/save")
    public OrganizationSuccessResponseDto saveOrganization(@Validated @RequestBody OrganizationSaveRequestDto organizationSaveRequestDto) {
        organizationService.saveOrganization(organizationSaveRequestDto);
        return new OrganizationSuccessResponseDto();
    }
}
