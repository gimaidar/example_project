package com.gimaletdinov.exampleProject.mapper;

import com.gimaletdinov.exampleProject.model.Country;
import org.mapstruct.Mapper;
import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;

import java.util.List;

/**
 * Маппер для сущности Страна
 */
@Mapper(componentModel = "spring")
public interface CountryMapper {

    /**
     * Метод для маппинга Country в формат ответа CountryListResponseDto
     * @param country
     * @return CountryListResponseDto
     */
    CountryListResponseDto toResponseDto(Country country);

    /**
     * Метод для маппинга списка Country в формат ответа список CountryListResponseDto
     * @param countryList
     * @return список CountryListResponseDto
     */
    List<CountryListResponseDto> toResponseDtoList(List<Country> countryList);
}
