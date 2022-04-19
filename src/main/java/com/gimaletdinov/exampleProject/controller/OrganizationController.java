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

/**
 *Контроллер для сущности Организация
 */
@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Метод возвращает список организаций, найденных в БД по определенному предикату
     * @return список организаций, найденных в БД по определенному придикату
     */
    @GetMapping("/list")
    public List<OrganizationListResponseDto> getAllOrganizationsByPredicat(@Valid @RequestBody OrganizationListRequestDto organizationListRequestDto) {
        return organizationService.getAllOrganizationsByPredicat(organizationListRequestDto);
    }

    /**
     * Метод возвращает организацию по id
     * @param id id организации
     * @return организация
     */
    @GetMapping("/{id}")
    public OrganizationResponseDto getOrganizationById(@PathVariable Integer id) {
        return organizationService.getOrganizationById(id);
    }

    /**
     * Метод для обновления данных организации
     * @param organizationUpdateRequestDto
     * @return ObjectSuccessResponseDto [result : success]
     */
    @PostMapping("/update")
    public ObjectSuccessResponseDto updateOrganization(@Valid @RequestBody OrganizationUpdateRequestDto organizationUpdateRequestDto) {
        organizationService.updateOrganization(organizationUpdateRequestDto);
        return new ObjectSuccessResponseDto();
    }

    /**
     * Метод для сохранения новой организации
     * @param organizationSaveRequestDto
     * @return ObjectSuccessResponseDto [result : success]
     */
    @PostMapping("/save")
    public ObjectSuccessResponseDto saveOrganization(@Valid @RequestBody OrganizationSaveRequestDto organizationSaveRequestDto) {
        organizationService.saveOrganization(organizationSaveRequestDto);
        return new ObjectSuccessResponseDto();
    }
}
