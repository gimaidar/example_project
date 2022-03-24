package com.gimaletdinov.exampleProject.controller;

import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectSuccessResponseDto;
import com.gimaletdinov.exampleProject.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public List<OrganizationListResponseDto> getAllOrganizationsByPredicat(@Valid @RequestBody OrganizationListRequestDto organizationListRequestDto) {
        return organizationService.getAllOrganizationsByPredicat(organizationListRequestDto);
    }

    @GetMapping("/{id}")
    public OrganizationResponseDto getOrganizationById(@PathVariable Integer id) {
        System.out.println();
        return organizationService.getOrganizationById(id);
    }

    @PostMapping("/update")
    public ObjectSuccessResponseDto updateOrganization(@Valid @RequestBody OrganizationUpdateRequestDto organizationUpdateRequestDto) {
        organizationService.updateOrganization(organizationUpdateRequestDto);
        return new ObjectSuccessResponseDto();
    }

    @PostMapping("/save")
    public ObjectSuccessResponseDto saveOrganization(@Valid @RequestBody OrganizationSaveRequestDto organizationSaveRequestDto) {
        organizationService.saveOrganization(organizationSaveRequestDto);
        return new ObjectSuccessResponseDto();
    }
}
