package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Organization;
import java.util.List;

/**
 * Интерфейс репозитория для сущности Организация
 */
public interface OrganizationRepository {

    /**
     * Метод возвращает список организаций, найденных в БД по определенному предикату
     * @return список организаций, найденных в БД по определенному придикату
     */
    List<Organization> getAllOrganizationsByPredicat(Organization organization);

    /**
     * Метод возвращает организацию по id
     * @param id id организации
     * @return организация
     */
    Organization getOrganizationById(int id);

    /**
     * Метод для обновления данных организации
     * @param organization
     */
    void updateOrganization(Organization organization);

    /**
     * Метод для сохранения новой организации
     * @param organization
     */
    void saveOrganization(Organization organization);
}
