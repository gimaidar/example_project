package com.gimaletdinov.exampleProject.mapper;

import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.gimaletdinov.exampleProject.helper.CountryDtoTestHelper.getPopulateCountry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CountryMapperImpl.class)
class CountryMapperTest {

    @Autowired
    private CountryMapper countryMapper;

    @Test
    @DisplayName("Тест: toModel (Маппинг из Country -> CountryListResponseDto)")
    void toResponseDto() {
        Country country = getPopulateCountry();
        CountryListResponseDto countryListResponseDto = countryMapper.toResponseDto(country);
        assertAll(
                () -> assertThat(countryListResponseDto.getCode()).isEqualTo(country.getCode()),
                () -> assertThat(countryListResponseDto.getName()).isEqualTo(country.getName())
        );
    }

    @Test
    @DisplayName("Тест: toResponseDtoList (Маппинг из List<Country> ->  List<CountryListResponseDto>)")
    void toResponseDtoList() {
        Country country = getPopulateCountry();
        List<Country> countryList = new ArrayList<>();
        countryList.add(country);

        List<CountryListResponseDto> countryListResponseDtoList = countryMapper.toResponseDtoList(countryList);
        CountryListResponseDto countryListResponseDto = countryListResponseDtoList.get(0);
        assertAll(
                () -> assertThat(countryListResponseDto.getCode()).isEqualTo(country.getCode()),
                () -> assertThat(countryListResponseDto.getName()).isEqualTo(country.getName())
        );
    }
}