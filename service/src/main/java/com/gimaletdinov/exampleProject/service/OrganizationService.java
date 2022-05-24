package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;

import java.util.List;

/**
 * Интерфейс сервиса для сущности Офис
 */
public interface OrganizationService {

    /**
     * Метод возвращает список организаций, найденных в БД по определенному предикату
     * @param organizationListRequestDto
     * @return список организаций
     */
    List<OrganizationListResponseDto> getAllOrganizationsByPredicat(OrganizationListRequestDto organizationListRequestDto);

    /**
     * Метод возвращает организацию по id и преобразует в формат ответа
     * @param id id организации
     * @return организация
     */
    OrganizationResponseDto getOrganizationById(int id);

    /**
     * Метод для обновления данных организации
     * @param organizationUpdateRequestDto
     */
    void updateOrganization(OrganizationUpdateRequestDto organizationUpdateRequestDto);

    /**
     * Метод для сохранения новой организации
     * @param organizationSaveRequestDto
     */
    Organization saveOrganization(OrganizationSaveRequestDto organizationSaveRequestDto);

    /**
     * Метод возвращает организацию по id
     * @param id id организации
     * @return организация
     */
    Organization getOrganizationByIdFromRepository(Integer id);
}
