package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;

import java.util.List;

/**
 * Интерфейс сервиса для сущности Страна
 */
public interface CountryService {

    /**
     * Метод для получения списка всех стран
     * @return список стран
     */
    List<CountryListResponseDto> getAllCountries();

    /**
     * Метод для получения страны по id
     * @param id
     * @return страна
     */
    Country getCountryById(int id);
}
