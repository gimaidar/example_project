package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    @Mapping(source = "id", target = "code")
    CountryListResponseDto toResponseDto(Country country);

    List<CountryListResponseDto> toResponseDtoList(List<Country> countryList);
}
