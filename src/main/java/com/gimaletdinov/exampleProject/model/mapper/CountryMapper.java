package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    @Mapping(source = "id", target = "code")
    CountryListResponseDto toResponseDto(Country country);

    /**
     * Метод для маппинга списка Country в формат ответа список CountryListResponseDto
     * @param countryList
     * @return список CountryListResponseDto
     */
    List<CountryListResponseDto> toResponseDtoList(List<Country> countryList);
}
