package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Office;

import java.util.List;

/**
 * Интерфейс репозитория для сущности Офис
 */
public interface OfficeRepository {

    /**
     * Метод возвращает список офисов, найденных в БД по определенному предикату
     * @param office
     * @return список офисов
     */
    List<Office> getAllOfficesByPredicat(Office office);

    /**
     * Метод возвращает офис по id
     * @param id
     * @return офис
     */
    Office getOfficeById(int id);

    /**
     * Метод для обновления данных офиса
     * @param office
     */
    void updateOffice(Office office);

    /**
     * Метод для сохранения нового офиса
     * @param office
     */
    void saveOffice(Office office);
}
